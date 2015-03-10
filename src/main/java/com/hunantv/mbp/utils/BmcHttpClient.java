/************************************************************************************
 * @File name   :      BossHttpClient.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月14日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年11月14日 下午4:42:39
 ************************************************************************************/
package com.hunantv.mbp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.BmcUrlEncodeUtil;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.entity.admin.User;

/**
 * HTTP请求响应处理
 * @author XuYanbo
 */
public class BmcHttpClient {
	
	private static final Logger logger = LoggerFactory.getLogger(BmcHttpClient.class);
	private static PoolingHttpClientConnectionManager cm = null;
	
	/**
	 * 初始化HTTP连接池
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @return
	 */
	static {
		if(cm==null){
			logger.debug("***** Initialize http client connection pool *****");
			cm = new PoolingHttpClientConnectionManager();

			// setMaxTotal(max): Set the maximum number of total open connections.
			cm.setMaxTotal(Integer.parseInt(ApplicationConfigs.HTTP_CLIENT_CONFIG[0]));

			// setDefaultMaxPerRoute(max): Set the maximum number of concurrent connections per route, which is 2 by default.
			cm.setDefaultMaxPerRoute(Integer.parseInt(ApplicationConfigs.HTTP_CLIENT_CONFIG[1]));

			// setMaxPerRoute(max): Set the total number of concurrent connections to a specific route, which is 2 by default.
			HttpHost payServiceHost = new HttpHost(ApplicationConfigs.PAYMENT_SERVER_URI);
			HttpHost bossServiceHost = new HttpHost(ApplicationConfigs.BOSS_SERVER_URI);
			int maxPerRoute = Integer.parseInt(ApplicationConfigs.HTTP_CLIENT_CONFIG[2]);
			cm.setMaxPerRoute(new HttpRoute(payServiceHost), maxPerRoute);
			cm.setMaxPerRoute(new HttpRoute(bossServiceHost), maxPerRoute);
			
			// setting socket timeout to 5 seconds
			int timeout = Integer.parseInt(ApplicationConfigs.HTTP_CLIENT_CONFIG[3]);
			cm.setSocketConfig(payServiceHost, SocketConfig.custom().setSoTimeout(timeout).build());
			cm.setSocketConfig(bossServiceHost, SocketConfig.custom().setSoTimeout(timeout).build());
		}
		
	}
	
	/**
	 * 释放HTTP Connection连接池
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 */
	public static void releaseHttpClientConnection(){
		if(cm!=null){
			cm.close();
			cm.shutdown();
		}
	}
	
	/**
	 * 获取不加签名的请求URL
	 * @Author：XuYanbo
	 * @Date：2015年1月14日
	 * @param uri
	 * @param params
	 * @return
	 */
	private static String generateCommonUrl(String uri, String params){

		StringBuffer requestUri = new StringBuffer(uri);
		if(StringUtils.isNotBlank(params)){
			String param = params.substring(1);	//去掉第一个&
			requestUri.append("?").append(BmcUrlEncodeUtil.encodeURIComponent(param));
		}
		
		logger.debug("***** Request URI: [" + requestUri + "] *****");
		return requestUri.toString();
	}
	
	/**
	 * 获取添加签名后的请求URL
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param uri
	 * @param params
	 * @return
	 */
	private static String generateSignUrl(String uri, String params){
		StringBuffer requestUri = new StringBuffer(uri);
		
		if(StringUtils.isNotBlank(params)){
			String param = params.substring(1);	//去掉第一个&
			requestUri.append("?").append(param);
			requestUri.append("&sign=" + DigestUtils.md5Hex(param + ApplicationConfigs.HTTP_KEY));
		} else {
			requestUri.append("?sign=" + DigestUtils.md5Hex(ApplicationConfigs.HTTP_KEY));
		}
		
		//取得操作用户ID
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
		
		if(user!=null){
			requestUri.append("&userid=").append(user.getUserId());
		}

		logger.debug("***** Request URI: [" + requestUri + "] *****");
		return requestUri.toString();
	}
	
	/**
	 * 构建Get请求,返回响应信息
	 * secure: Y-带签名, N-不带签名
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param uri
	 * @param params
	 * @return
	 * @throws BmcException
	 */
	public static String getForResponse(String uri, String params, boolean secure) throws BmcException {
		String responseStr = "";
		CloseableHttpResponse response = null;
		HttpGet get = null;
		try {
			CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
			get = new HttpGet(secure ? generateSignUrl(uri, params) : generateCommonUrl(uri, params));
			response = client.execute(get);
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new BmcException(e);
		} finally {
			try {
				if(response!=null) response.close();
				if(get!=null) get.releaseConnection();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		return responseStr;
	}
	
	/**
	 * 构建Post请求,返回响应信息(带签名机制)
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param uri
	 * @param fields
	 * @return
	 * @throws BmcUpdateException
	 */
	public static String postForResponse(String uri, List<NameValuePair> fields, boolean secure) throws BmcUpdateException {
		CloseableHttpResponse response = null;
		HttpPost post = null;
		String responseStr = null;
		try {
			CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
			post = new HttpPost(uri);
			
			logger.debug("***** Post Request URI: [" + uri + "] *****");
			
			if(secure){
				StringBuffer params = new StringBuffer();
				for(NameValuePair p: fields){
					params.append("&");
					params.append(p.getName());
					params.append("=");
					params.append(p.getValue());
				}
				String signKey = DigestUtils.md5Hex(params.substring(1) + ApplicationConfigs.HTTP_KEY);
				fields.add(new BasicNameValuePair("sign", signKey));
			}
			
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(fields, Consts.UTF_8);
			post.setEntity(formEntity);

			response = client.execute(post);
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new BmcUpdateException(e);
		} finally {
			try {
				if(response!=null) response.close();
				if(post!=null) post.releaseConnection();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		return responseStr;
	}
	
	/**
	 * 发送GET请求结果集List(指定Class)
	 * @Author：XuYanbo
	 * @Date：2014年11月22日
	 * @param uri
	 * @param params
	 * @param cls
	 * @return
	 * @throws BmcException
	 */
	public static <U> HttpResult<U> sendGetForList(String uri, String params, Class<U> cls) throws BmcException{
		String responseStr = getForResponse(uri, params, true);
		HttpResult<U> result = parseJsonMapToList(responseStr, cls);
		return result;
	}

	/**
	 * 发送GET请求结果集List(不带签名机制)
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param uri
	 * @param params
	 * @return
	 * @throws BmcException 
	 */
	public static <U> HttpResult<U> sendGetForListWithoutSignature(String uri, String params, Class<U> cls) throws BmcException{
		String responseStr = getForResponse(uri, params, false);
		HttpResult<U> result = parseJsonToList(responseStr, cls);
		return result;
	}
	
	/**
	 * 发送GET请求Boss结果集List(不带签名机制的分页接口)
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @param uri
	 * @param params
	 * @return
	 * @throws BmcException 
	 */
	public static <U> HttpResult<U> sendGetForPageWithoutSignature(String uri, String params, Class<U> cls) throws BmcException{
		String responseStr = getForResponse(uri, params, false);
		HttpResult<U> result = parseJsonToPage(responseStr, cls);
		return result;
	}
	
	/**
	 * 发送GET请求获取单一对象
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param uri
	 * @param params
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static <U> U sendGetForObject(String uri, String params, Class<U> cls, boolean secure) throws BmcException{
		String responseStr = getForResponse(uri, params, secure);
		U result = parseJsonToObject(responseStr, cls);
		if(result==null){
			logger.error("***** No Data Found. [uri=" + uri + ";params=" + params + "]");
		}
		return result;
	}
	
	/**
	 * GET, 不构造对象, 直接返回到前端处理, 应对复杂响应内容
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param uri
	 * @param params
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static JSONObject sendGetForJsonObject(String uri, String params, boolean secure) throws BmcException{
		String responseStr = getForResponse(uri, params, secure);
		JSONObject result = parseJsonToObject(responseStr);
		return result;
	}
	
	/**
	 * GET ARRAY
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param uri
	 * @param params
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static JSONArray sendGetForJsonArray(String uri, String params, boolean secure) throws BmcException{
		String responseStr = getForResponse(uri, params, secure);
		JSONArray result = parseJsonToArray(responseStr);
		return result;
	}
	
	/**
	 * POST, 不构造对象, 直接返回到前端处理, 应对复杂响应内容
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param uri
	 * @param params
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static JSONObject sendPostForJsonObject(String uri, List<NameValuePair> fields, boolean secure) throws BmcException{
		String responseStr = postForResponse(uri, fields, secure);
		JSONObject result = JSONObject.parseObject(responseStr);
		return result;
	}
	
	/**
	 * 发送GET请求
	 * @Author：XuYanbo
	 * @Date：2014年12月25日
	 * @param uri
	 * @param params
	 * @return
	 * @throws BmcException 
	 */
	public static String sendGet(String uri) throws BmcException {
		String responseStr = "";
		CloseableHttpResponse response = null;
		HttpGet get = null;
		try {
			CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
			get = new HttpGet(uri);
			response = client.execute(get);
			responseStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			throw new BmcException(e);
		} finally {
			try {
				if(response!=null) response.close();
				if(get!=null) get.releaseConnection();
			} catch (IOException ioe){
				ioe.printStackTrace();
			}
		}
		String result = parseJsonToBasicResult(responseStr);
		return result;
	}
	
	/**
	 * 发送POST请求
	 * @Author：XuYanbo
	 * @Date：2014年12月25日
	 * @param uri
	 * @param fields
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static String sendPost(String uri, List<NameValuePair> fields, boolean secure) throws BmcException {
		String responseStr = postForResponse(uri, fields, secure);
		String result = parseJsonToBasicResult(responseStr);
		return result;
	}
	
	/**
	 * 发送POST请求(带返回值)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param uri
	 * @param fields
	 * @return
	 * @throws BmcException 
	 */
	public static <U> U sendPostForObject(String uri, List<NameValuePair> fields, Class<U> cls, boolean secure) throws BmcException {
		String responseStr = postForResponse(uri, fields, secure);
		U result = parseJsonToObject(responseStr, cls);
		return result;
	}
	
	/**
	 * 返回的JSON构建系统可识别的List Result(通过反射转化成Object)
	 * 返回的Data中带数组索引: 'data':['0':{...},'1':{...}]
	 * @Author：XuYanbo
	 * @Date：2014年11月12日
	 * @param resultStr
	 * @param cls
	 * @return
	 * @throws BmcException 
	 */
	public static <U> HttpResult<U> parseJsonMapToList(String resultStr, Class<U> cls) throws BmcException {
		
		JSONObject jsonObj = getResultFromResponse(resultStr);
		HttpResult<U> result = new HttpResult<U>();
		
		String tt = jsonObj.getString(HttpServiceConstants.RESULT_TOTALCOUNT);
		result.setTotalCount(null==tt ? 0 : Integer.parseInt(tt));
		
		Map<String, U> resMap = JSON.parseObject(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString(), new TypeReference<Map<String, U>>(){});
		if(resMap!=null){
			Iterator<Entry<String, U>> it = resMap.entrySet().iterator();
			List<U> datas = new ArrayList<U>();
			while(it.hasNext()){
				String objString = it.next().getValue().toString();
				datas.add(JSONObject.parseObject(objString, cls));
			}
			result.setData(datas);	
		}

		return result;
	}

	/**
	 * 返回的JSON构建系统可识别的List Result(通过反射转化成Object)
	 * 返回的Data中不带数组索引: 'data':[{...},{...}]
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param resultStr
	 * @return
	 * @throws Exception
	 */
	public static <U> HttpResult<U> parseJsonToList(String resultStr, Class<U> cls) throws BmcException {
		
		JSONObject jsonObj = getResultFromResponse(resultStr);
		HttpResult<U> result = new HttpResult<U>();
		List<U> res = JSON.parseArray(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString(), cls);
		result.setData(res);

		return result;
	}
	
	/**
	 * 返回的JSON构建系统可识别的List Result(通过反射转化成Object)
	 * 返回的Data中不带数组索引: 'data':{'rows':[{...},{...}]}
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param resultStr
	 * @return
	 * @throws Exception
	 */
	public static <U> HttpResult<U> parseJsonToPage(String resultStr, Class<U> cls) throws BmcException {
		
		JSONObject jsonObj = getResultFromResponse(resultStr);
		HttpResult<U> result = new HttpResult<U>();
		JSONObject data = JSON.parseObject(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString());
		String tt = data.getString(HttpServiceConstants.RESULT_TOTAL);
		result.setTotalCount(null==tt ? 0 : Integer.parseInt(tt));
		List<U> res = JSON.parseArray(data.getString(HttpServiceConstants.RESULT_PAGE_ROWS), cls);
		result.setData(res);

		return result;
	}

	/**
	 * 根据接口返回的JSON构建系统可识别的Object Result
	 * @Author：XuYanbo
	 * @Date：2014年11月12日
	 * @param resultStr
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <U> U parseJsonToObject(String resultStr, Class<U> cls) throws BmcException {
		logger.debug("***** response: ["+resultStr + "] *****");
		JSONObject jsonObj = getResultFromResponse(resultStr);
		Object tmpObject = jsonObj.get(HttpServiceConstants.RESULT_DATA);
		return tmpObject==null ? null : JSONObject.parseObject(tmpObject.toString(), cls);
	}
	
	/**
	 * 根据接口返回的JSONObject
	 * @Author：XuYanbo
	 * @Date：2015年2月4日
	 * @param resultStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject parseJsonToObject(String resultStr) throws BmcException {
		JSONObject jsonObj = getResultFromResponse(resultStr);
		JSONObject resultObject = JSONObject.parseObject(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString());
		return resultObject;
	}
	
	/**
	 * 根据接口返回的JSONArray
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param resultStr
	 * @return
	 * @throws Exception
	 */
	public static JSONArray parseJsonToArray(String resultStr) throws BmcException {
		JSONObject jsonObj = getResultFromResponse(resultStr);
		JSONArray array = JSONObject.parseArray(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString());
		return array;
	}

	/**
	 * Post接受返回结果
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param resultStr
	 * @return
	 * @throws Exception
	 */
	public static String parseJsonToBasicResult(String resultStr) throws BmcException {
		logger.debug("***** response: ["+resultStr + "] *****");
		JSONObject jsonObj = getResultFromResponse(resultStr);
		int resultCode = jsonObj.getInteger(HttpServiceConstants.RESULT_CODE);
		return HttpServiceConstants.RESULT_CODE_SUCCESS == resultCode ? BmcConstants.YES : BmcConstants.NO;
	}
	
	/**
	 * 提取统一处理部分，返回JSON对象
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param resultStr
	 * @return
	 * @throws BmcException
	 */
	public static JSONObject getResultFromResponse(String resultStr) throws BmcException {
		if(StringUtils.isBlank(resultStr)){
			throw new BmcException(HttpServiceConstants.RESULT_ERROR_NULL);
		}
		
		JSONObject jsonObj = JSON.parseObject(resultStr);
		int resultCode = jsonObj.getInteger(HttpServiceConstants.RESULT_CODE);
		String message = jsonObj.getString(HttpServiceConstants.RESULT_MESSAGE);
		
		if(HttpServiceConstants.RESULT_CODE_SUCCESS != resultCode){
			throw new BmcException(message);
		}
		return jsonObj;
	}
	
	/**
	 * For Develop, Local HTTP Server Test
	 * @Author：XuYanbo
	 * @Date：2015年2月2日
	 * @return
	 * @throws BmcException 
	 */
	public static <U> HttpResult<U> sendGetForListTest(Class<U> cls) throws BmcException{
		String responseStr = BmcLocalServerForTest.getResponseContent();
		HttpResult<U> result = parseJsonToList(responseStr, cls);
		return result;
	}
}
