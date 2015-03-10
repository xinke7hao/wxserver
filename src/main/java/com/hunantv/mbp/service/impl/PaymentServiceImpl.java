/************************************************************************************
 * @File name   :      PaymentServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月12日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午11:17:21
 ************************************************************************************/
package com.hunantv.mbp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.command.AppleQueryCommand;
import com.hunantv.mbp.command.MonitorCommand;
import com.hunantv.mbp.command.PaymentOrderCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.commons.MbpHttpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.DictionaryMapper;
import com.hunantv.mbp.dto.Blacklist;
import com.hunantv.mbp.dto.HttpDataId;
import com.hunantv.mbp.dto.PaymentApi;
import com.hunantv.mbp.dto.PaymentChannel;
import com.hunantv.mbp.dto.PaymentMonitor;
import com.hunantv.mbp.dto.PaymentOrder;
import com.hunantv.mbp.dto.PaymentPlatform;
import com.hunantv.mbp.dto.PaymentStatis;
import com.hunantv.mbp.dto.SmsAsyncNotify;
import com.hunantv.mbp.entity.admin.SystemData;
import com.hunantv.mbp.service.PaymentService;
import com.hunantv.mbp.utils.BmcHttpClient;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Resource
	private DictionaryMapper dictionaryMapper;
	
	/**
	 * 查询支付流水列表
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param cmd
	 * @param pageInfo
	 * @return
	 * @throws IOException 
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_ORDER, type=BmcConstants.LOG_TYPE_QUERY, desc="查询支付流水")
	public HttpResult<PaymentOrder> getAllPaymentOrders(PaymentOrderCommand cmd, PageInfo<PaymentOrder> pageInfo) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/all");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			
			//添加按字典顺序,方便签名验证
			if(StringUtils.isNotBlank(cmd.getAccount())){
				params.append("&account="+cmd.getAccount());
			}
			if(StringUtils.isNotBlank(cmd.getBusinessOrderId())){
				params.append("&business_order_id="+cmd.getBusinessOrderId());
			}
			if(StringUtils.isNotBlank(cmd.getChannelId())){
				params.append("&channel_id="+cmd.getChannelId());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day="+cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getMobile())){
				params.append("&mobile="+cmd.getMobile());
			}
			if(StringUtils.isNotBlank(cmd.getOrderId())){
				params.append("&order_id="+cmd.getOrderId());
			}
			params.append("&page_size="+pageInfo.getPageSize());
			if(StringUtils.isNotBlank(cmd.getProductId())){
				params.append("&product_id="+cmd.getProductId());
			}
			params.append("&start="+pageInfo.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day="+cmd.getStartDay());
			}
		}

		HttpResult<PaymentOrder> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentOrder.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 查询单条支付流水列表
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param orderId
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_ORDER, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取支付流水")
	public PaymentOrder getPaymentOrderById(String orderId) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/item", "&order_id="+orderId, PaymentOrder.class, true);
	}
	
	/**
	 * 根据ID删除单条支付流水
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param id
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_ORDER, type=BmcConstants.LOG_TYPE_DELETE, desc="根据ID删除支付流水")
	public String deletePaymentOrderById(String id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("order_id", id));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/del", fields, true);
	}

	/**
	 * 更新支付流水信息
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param payment
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_ORDER, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新支付流水")
	public String savePaymentOrder(PaymentOrder payment) throws BmcException {

		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		
		if(StringUtils.isNotBlank(payment.getNotify_time())){
			fields.add(new BasicNameValuePair("notify_time", payment.getNotify_time()));
		}
		fields.add(new BasicNameValuePair("order_id", payment.getId()));
		if(StringUtils.isNotBlank(payment.getRecon_code())){
			fields.add(new BasicNameValuePair("recon_flag", payment.getRecon_code()));
		}
		if(StringUtils.isNotBlank(payment.getRecon_time())){
			fields.add(new BasicNameValuePair("recon_time", payment.getRecon_time()));
		}
		if(StringUtils.isNotBlank(payment.getStatus())){
			fields.add(new BasicNameValuePair("status", payment.getStatus()));
		}
		
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/update", fields, true);
	}

	/**
	 * 支付流水补单
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param id
	 * @param type
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_ORDER_FIX, type=BmcConstants.LOG_TYPE_UPDATE, desc="支付流水一键补单")
	public String fixPaymentOrder(String id, String type) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("order_id", id));
		fields.add(new BasicNameValuePair("type", type));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/operation", fields, true);
	}

	/**
	 * 获取业务平台列表
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_QUERY, desc="获取业务平台列表")
	public HttpResult<PaymentPlatform> getAllPaymentPlatforms(PageInfo<PaymentPlatform> page, String platformIds) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/platform/all");
		StringBuffer params = new StringBuffer();
		params.append("&page_size=" + page.getPageSize());
		
		if(StringUtils.isNotBlank(platformIds)){
			params.append("&platform_id=" + platformIds);
		}
		
		params.append("&start=" + page.getStartRow());
		HttpResult<PaymentPlatform> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentPlatform.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 获取支付渠道列表
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付渠道列表")
	public HttpResult<PaymentChannel> getAllPaymentChannels(PageInfo<PaymentChannel> page, String channelIds) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/channel/all");
		StringBuffer params = new StringBuffer();
		
		if(StringUtils.isNotBlank(channelIds)){
			params.append("&channel_id=" + channelIds);
		}
		
		params.append("&page_size=" + page.getPageSize());
		params.append("&start=" + page.getStartRow());
		HttpResult<PaymentChannel> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentChannel.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 保存支付渠道(创建/修改共用)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channel
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_UPDATE, desc="(创建/更新)支付渠道")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String savePaymentChannel(PaymentChannel channel) throws BmcException {
		
		boolean isCreate = channel.getChannel_id() == null;
		
		//先处理本地支付渠道字典
		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, channel.getChannel_id());
		data.setDataCode(channel.getChannel_code());
		data.setDataName(channel.getChannel_alias());
		data.setDataStatus(HttpServiceConstants.PAY_CHANNEL_STATUS_VALID.equals(channel.getIs_available()) ? BmcConstants.YES : BmcConstants.NO);
		if(!isCreate) {
			dictionaryMapper.update(data);
		}

		//再处理支付接口(处理失败时本地逻辑回滚)
		String method = null;
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(channel.getAgent_name())){
			fields.add(new BasicNameValuePair("agent_name", channel.getAgent_name()));
		}
		if(StringUtils.isNotBlank(channel.getApp_id())){
			fields.add(new BasicNameValuePair("app_id", channel.getApp_id()));
		}
		if(StringUtils.isNotBlank(channel.getApp_secret())){
			fields.add(new BasicNameValuePair("app_secret", channel.getApp_secret()));
		}
		if(StringUtils.isNotBlank(channel.getApp_signkey())){
			fields.add(new BasicNameValuePair("app_signkey", channel.getApp_signkey()));
		}
		if(StringUtils.isNotBlank(channel.getChannel_alias())){
			fields.add(new BasicNameValuePair("channel_alias", channel.getChannel_alias()));
		}
		if(StringUtils.isNotBlank(channel.getChannel_code())){
			fields.add(new BasicNameValuePair("channel_code", channel.getChannel_code()));
		}
		if(!isCreate){
			method = "update";
			fields.add(new BasicNameValuePair("channel_id", channel.getChannel_id()+""));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(channel.getChannel_name())){
			fields.add(new BasicNameValuePair("channel_name", channel.getChannel_name()));
		}
		if(StringUtils.isNotBlank(channel.getChannel_type())){
			fields.add(new BasicNameValuePair("channel_type", channel.getChannel_type()));
		}
		if(StringUtils.isNotBlank(channel.getCper())){
			fields.add(new BasicNameValuePair("cper", channel.getCper()));
		}
		if(StringUtils.isNotBlank(channel.getIs_available())){
			fields.add(new BasicNameValuePair("is_available", channel.getIs_available()));
		}
		if(StringUtils.isNotBlank(channel.getKey())){
			fields.add(new BasicNameValuePair("key", channel.getKey()));
		}
		if(StringUtils.isNotBlank(channel.getNotify_url())){
			fields.add(new BasicNameValuePair("notify_url", channel.getNotify_url()));
		}
		if(StringUtils.isNotBlank(channel.getOrder_rate())){
			fields.add(new BasicNameValuePair("order_rate", channel.getOrder_rate()));
		}
		if(StringUtils.isNotBlank(channel.getPay_url())){
			fields.add(new BasicNameValuePair("pay_url", channel.getPay_url()));
		}
		if(StringUtils.isNotBlank(channel.getPer())){
			fields.add(new BasicNameValuePair("per", channel.getPer()));
		}
		if(StringUtils.isNotBlank(channel.getQuery_url())){
			fields.add(new BasicNameValuePair("query_url", channel.getQuery_url()));
		}
		if(StringUtils.isNotBlank(channel.getReturn_url())){
			fields.add(new BasicNameValuePair("return_url", channel.getReturn_url()));
		}
		if(StringUtils.isNotBlank(channel.getSecret())){
			fields.add(new BasicNameValuePair("secret", channel.getSecret()));
		}
		if(StringUtils.isNotBlank(channel.getSeller())){
			fields.add(new BasicNameValuePair("seller", channel.getSeller()));
		}
		if(StringUtils.isNotBlank(channel.getSeller_name())){
			fields.add(new BasicNameValuePair("seller_name", channel.getSeller_name()));
		}
		
		HttpDataId result = BmcHttpClient.sendPostForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/channel/" + method, fields, HttpDataId.class, true);
 
		//创建时需要补全接口返回的DataId
		if(isCreate){
			if(result!=null && StringUtils.isNotBlank(result.getId())){
				data.setDataId(Integer.parseInt(result.getId()));
				dictionaryMapper.insert(data);
				
				//在操作者session中添加该平台的数据权限
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				@SuppressWarnings("unchecked")
				Map<String, String> dataMap = (Map<String, String>)request.getSession().getAttribute(BmcConstants.USER_GROUP_DATA);
				String channelIds = dataMap==null ? "" : dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL);
				if(StringUtils.isNotBlank(channelIds)){
					dataMap.put(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, channelIds + "," + data.getDataId());
				} else {
					dataMap.put(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, data.getDataId()+"");
				}
			} else {
				throw new BmcUpdateException("*** (Creating Payment Channel) No DataId Returned ***");
			}
		}
		
		return BmcConstants.YES;
	}
	
	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channelId
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_DELETE, desc="删除支付渠道")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String deletePaymentChannel(String channelId) throws BmcException {
		
		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, Integer.parseInt(channelId));
		dictionaryMapper.delete(data);
		dictionaryMapper.deleteGroupData(data);
		
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("channel_id", channelId));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/channel/del", fields, true);
	}
	
	/**
	 * 修改支付渠道状态(关闭/开启)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channelId
	 * @param operation
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_UPDATE, desc="修改支付渠道状态")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String changePaymentChannelStatus(String channelId, int operation) throws BmcException {

		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, Integer.parseInt(channelId));
		data.setDataStatus(HttpServiceConstants.PAY_OPERATION_OPEN==operation ? BmcConstants.YES : BmcConstants.NO);
		dictionaryMapper.updateStatus(data);
		
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("channel_id", channelId));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/channel/" + (HttpServiceConstants.PAY_OPERATION_OPEN == operation ? "open" : "close"), fields, true);
	}

	/**
	 * 根据ID提取支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param channelId
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取支付渠道")
	public PaymentChannel getPaymentChannelById(String channelId) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/channel/item", "&channel_id=" + channelId, PaymentChannel.class, true);
	}
	
	/**
	 * 保存业务平台信息(创建/修改共用)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platform
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_UPDATE, desc="(创建/更新)业务平台")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String savePaymentPlatform(PaymentPlatform platform) throws BmcException {
		
		boolean isCreate = platform.getPlatform_id() == null;
		
		//先处理本地业务平台字典
		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, platform.getPlatform_id());
		data.setDataName(platform.getPlatform_name());
		data.setDataStatus(HttpServiceConstants.PAY_PLATFORM_STATUS_VALID.equals(platform.getStatus()) ? BmcConstants.YES : BmcConstants.NO);
		if(!isCreate) {
			dictionaryMapper.update(data);
		}
		
		//再处理支付接口(处理失败时本地逻辑回滚)
		String method = null;
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(platform.getContact())){
			fields.add(new BasicNameValuePair("contact", platform.getContact()));
		}
		if(StringUtils.isNotBlank(platform.getExt_data())){
			fields.add(new BasicNameValuePair("ext_data", platform.getExt_data()));
		}
		
		if(!isCreate){
			method = "update";
			fields.add(new BasicNameValuePair("platform_id", platform.getPlatform_id()+""));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(platform.getPlatform_name())){
			fields.add(new BasicNameValuePair("platform_name", platform.getPlatform_name()));
		}
		if(StringUtils.isNotBlank(platform.getRemark())){
			fields.add(new BasicNameValuePair("remark", platform.getRemark()));
		}
		if(StringUtils.isNotBlank(platform.getSecret())){
			fields.add(new BasicNameValuePair("secret", platform.getSecret()));
		}
		if(StringUtils.isNotBlank(platform.getStatus())){
			fields.add(new BasicNameValuePair("status", platform.getStatus()));
		}
		
		HttpDataId result = BmcHttpClient.sendPostForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/platform/" + method, fields, HttpDataId.class, true);

		//如果是创建需要补全DataId
		if(isCreate){
			if(result!=null && StringUtils.isNotBlank(result.getId())){
				data.setDataId(Integer.parseInt(result.getId()));
				dictionaryMapper.insert(data);
				
				//在操作者session中添加该平台的数据权限
				HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				@SuppressWarnings("unchecked")
				Map<String, String> dataMap = (Map<String, String>)request.getSession().getAttribute(BmcConstants.USER_GROUP_DATA);
				String platformIds = dataMap==null ? "" : dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM);
				if(StringUtils.isNotBlank(platformIds)){
					dataMap.put(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, platformIds + "," + data.getDataId());
				} else {
					dataMap.put(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, data.getDataId()+"");
				}
				
			} else {
				throw new BmcUpdateException("*** (Creating Payment Platform) No DataId Returned ***");
			}
		}

		return BmcConstants.YES;
	}
	
	/**
	 * 删除业务平台信息
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platformId
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_DELETE, desc="删除业务平台")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String deletePaymentPlatform(String platformId) throws BmcException {
		
		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, Integer.parseInt(platformId));
		dictionaryMapper.delete(data);
		dictionaryMapper.deleteGroupData(data);
		
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("platform_id", platformId));
		
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/platform/del", fields, true);
	}
	
	/**
	 * 修改业务平台状态(开启/关闭)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platformId
	 * @param operation
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_UPDATE, desc="修改业务平台状态")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public String changePaymentPlatformStatus(String platformId, int operation) throws BmcException {
		
		SystemData data = new SystemData(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, Integer.parseInt(platformId));
		data.setDataStatus(HttpServiceConstants.PAY_OPERATION_OPEN==operation ? BmcConstants.YES : BmcConstants.NO);
		dictionaryMapper.updateStatus(data);
		
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("platform_id", platformId));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/platform/" + (HttpServiceConstants.PAY_OPERATION_OPEN == operation ? "open" : "close"), fields, true);
	}

	/**
	 * 根据ID提取业务平台
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param platformId
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取业务平台")
	public PaymentPlatform getPaymentPlatformById(String platformId) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/platform/item", "&platform_id=" + platformId, PaymentPlatform.class, true);
	}
	
	/**
	 * 根据ID获取黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_BLACKLIST, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID查询黑名单")
	public Blacklist getBlacklistById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/blacklist/item", "&id=" + id, Blacklist.class, true);
	}

	/**
	 * 获取黑名单列表
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param cmd
	 * @param pageInfo
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_BLACKLIST, type=BmcConstants.LOG_TYPE_QUERY, desc="获取黑名单列表")
	public HttpResult<Blacklist> getAllBlacklists(Blacklist cmd, PageInfo<Blacklist> pageInfo) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/blacklist/all");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			
			//添加按字典顺序,方便签名验证
			if(StringUtils.isNotBlank(cmd.getChannel_id())){
				params.append("&channel_id="+cmd.getChannel_id());
			}
			if(StringUtils.isNotBlank(cmd.getItem())){
				params.append("&item="+cmd.getItem());
			}
			params.append("&page_size="+pageInfo.getPageSize());
			if(StringUtils.isNotBlank(cmd.getPlatform_id())){
				params.append("&platform_id="+cmd.getPlatform_id());
			}
			params.append("&start="+pageInfo.getStartRow());
			if(StringUtils.isNotBlank(cmd.getType())){
				params.append("&type="+cmd.getType());
			}
		}

		HttpResult<Blacklist> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), Blacklist.class);
		Collections.sort(result.getData());
		return result;
	}

	/**
	 * 删除黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_BLACKLIST, type=BmcConstants.LOG_TYPE_DELETE, desc="删除黑名单")
	public String deleteBlacklist(String id) throws BmcException {

		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("id", id));
		
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/blacklist/del", fields, true);
	}

	/**
	 * 新增/修改黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param blacklist
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_BLACKLIST, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存黑名单")
	public String saveBlacklist(Blacklist blacklist) throws BmcException {
		
		//再处理支付接口(处理失败时本地逻辑回滚)
		String method = null;
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(blacklist.getChannel_id())){
			fields.add(new BasicNameValuePair("channel_id", blacklist.getChannel_id()));
		}
		
		if(StringUtils.isNotBlank(blacklist.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", blacklist.getId()));
		} else {
			method = "create";
		}
		
		if(StringUtils.isNotBlank(blacklist.getItem())){
			fields.add(new BasicNameValuePair("item", blacklist.getItem()));
		}
		if(StringUtils.isNotBlank(blacklist.getPlatform_id())){
			fields.add(new BasicNameValuePair("platform_id", blacklist.getPlatform_id()));
		}
		if(StringUtils.isNotBlank(blacklist.getStatus_id())){
			fields.add(new BasicNameValuePair("status", blacklist.getStatus_id()));
		}
		if(StringUtils.isNotBlank(blacklist.getType_id())){
			fields.add(new BasicNameValuePair("type", blacklist.getType_id()));
		}
		
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/blacklist/" + method, fields, true);
	}
	
	/**
	 * 9588异步通知查询
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param sms
	 * @param smsPage
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_SMS_NOTIFY, type=BmcConstants.LOG_TYPE_QUERY, desc="查询9588异步通知")
	public HttpResult<SmsAsyncNotify> getAllSmsAsyncNotifys(SmsAsyncNotify cmd, PageInfo<SmsAsyncNotify> pageInfo) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/notify/all");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day="+cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getMobile())){
				params.append("&mobile="+cmd.getMobile());
			}
			params.append("&page_size="+pageInfo.getPageSize());
			params.append("&start="+pageInfo.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day="+cmd.getStartDay());
			}
		}

		HttpResult<SmsAsyncNotify> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), SmsAsyncNotify.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 根据手机号获取支付详情信息
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param mobile
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_MOBILE_QUERY, type=BmcConstants.LOG_TYPE_QUERY, desc="手机号查询平台状态")
	public JSONObject getPaymentDetailByMobile(String mobile) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/order/mobile");
		JSONObject result = BmcHttpClient.sendGetForJsonObject(uri.toString(), "&mobile="+mobile, true);
		return result;
	}
	
	/**
	 * 根据手机号获取9588状态
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param mobile
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_MOBILE_QUERY, type=BmcConstants.LOG_TYPE_QUERY, desc="手机号查询9588状态")
	public JSONObject getSmsStatusByMobile(String mobile) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/notify/mobile");
		JSONObject result = BmcHttpClient.sendGetForJsonObject(uri.toString(), "&mobile="+mobile, true);
		return result;
	}
	
	/**
	 * 订单发货
	 * @Author：XuYanbo
	 * @Date：2015年1月6日
	 * @param orderId
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_MOBILE_QUERY, type=BmcConstants.LOG_TYPE_UPDATE, desc="手机号查询-订单发货")
	public String deliverPaymentOrder(String orderId) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("order_id", orderId));
		return BmcHttpClient.sendPost(ApplicationConfigs.PAYMENT_SERVER_URI + "/notify/deliver", fields, true);
	}
	
	/**
	 * 苹果支付查询
	 * @Author：XuYanbo
	 * @Date：2015年1月13日
	 * @param cmd
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.PAYMENT_APPLE_QUERY, type=BmcConstants.LOG_TYPE_QUERY, desc="苹果支付凭证查询")
	public JSONObject searchApplePayDetail(AppleQueryCommand cmd) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/notify/apple");
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getOrderId())){
				fields.add(new BasicNameValuePair("order_id", cmd.getOrderId()));
			}
			if(StringUtils.isNotBlank(cmd.getPayAccount())){
				fields.add(new BasicNameValuePair("pay_account", cmd.getPayAccount()));
			}
			if(StringUtils.isNotBlank(cmd.getReceipt())){
				fields.add(new BasicNameValuePair("receipt", cmd.getReceipt()));
			}
		}
		
		JSONObject result = BmcHttpClient.sendPostForJsonObject(uri.toString(), fields, true);
		return result;
	}
	
	/**
	 * 支付流水按日统计汇总(维度包含业务平台,支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_ORDER, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付流水日统计数据")
	public HttpResult<PaymentStatis> getPaymentOrderStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException {

		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/order");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			params.append("&page_size=" + page.getPageSize());
			params.append("&start=" + page.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentStatis> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentStatis.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 支付流水按日统计汇总(维度-业务平台)
	 * @Author：XuYanbo
	 * @Date：2014年11月20日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_CHANNEL, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付流水(支付渠道)统计数据")
	public HttpResult<PaymentStatis> getPaymentChannelStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException {

		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/channel");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getChannelId())){
				params.append("&channel_id=" + cmd.getChannelId());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			params.append("&page_size=" + page.getPageSize());
			params.append("&start=" + page.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentStatis> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentStatis.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 支付流水按日统计汇总(维度-业务平台)
	 * @Author：XuYanbo
	 * @Date：2014年11月20日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_PLATFORM, type=BmcConstants.LOG_TYPE_QUERY, desc="获取业务平台支付统计数据")
	public HttpResult<PaymentStatis> getPaymentPlatformStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/platform");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			params.append("&page_size=" + page.getPageSize());
			if(StringUtils.isNotBlank(cmd.getPlatformId())){
				params.append("&platform_id=" + cmd.getPlatformId());
			}
			params.append("&start=" + page.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentStatis> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentStatis.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 支付流水对账
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_RECON, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付流水对账统计数据")
	public HttpResult<PaymentStatis> getPaymentReconStatis(PaymentStatisCommand cmd) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/reconcile/all");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getChannelId())){
				params.append("&channel_id=" + cmd.getChannelId());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getPlatformId())){
				params.append("&platform_id=" + cmd.getPlatformId());
			}
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentStatis> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentStatis.class);
		
		//结果按业务平台排序,前端进行Rowspan
		Collections.sort(result.getData(), new Comparator<PaymentStatis>() {
			@Override
			public int compare(PaymentStatis o1, PaymentStatis o2) {
				return o1.getPlatform_name().compareTo(o2.getPlatform_name());
			}
		});
		return result;
	}
	
	/**
	 * 财务报表(账号)
	 * @Author：XuYanbo
	 * @Date：2015年2月4日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_FINANCIAL_RECON, type=BmcConstants.LOG_TYPE_QUERY, desc="获取财务报表(账号)统计数据")
	public JSONObject getPaymentFinancialReconStatis(PaymentStatisCommand cmd) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/reconcile/financial");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getYear())){
				params.append("&year=" + cmd.getYear());
			}
		}
		
		JSONObject result = BmcHttpClient.sendGetForJsonObject(uri.toString(), params.toString(), true);
		return result;
	}
	
	/**
	 * 财务报表(支付渠道)
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_FINANCIAL_RECON, type=BmcConstants.LOG_TYPE_QUERY, desc="获取财务报表(支付渠道)统计数据")
	public JSONObject getPaymentFinancialChannelStatis(PaymentStatisCommand cmd) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/reconcile/business");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getYear())){
				params.append("&year=" + cmd.getYear());
			}
		}
		
		JSONObject result = BmcHttpClient.sendGetForJsonObject(uri.toString(), params.toString(), true);
		return result;
	}

	/**
	 * 支付系统API调用监控
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_MONITOR_PAYAPI, type=BmcConstants.LOG_TYPE_QUERY, desc="支付系统API调用监控")
	public HttpResult<PaymentApi> searchPaymentApiLogs(MonitorCommand cmd, PageInfo<PaymentApi> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/monitor/api");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getApiName())){
				params.append("&api_name=" + cmd.getApiName());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			params.append("&page_size=" + page.getPageSize());
			params.append("&start=" + page.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentApi> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentApi.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 支付流水监控
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_MONITOR_PAYORDER, type=BmcConstants.LOG_TYPE_QUERY, desc="支付流水监控")
	public HttpResult<PaymentMonitor> searchPaymentOrderLogs(MonitorCommand cmd, PageInfo<PaymentMonitor> page) throws BmcException {
		
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/monitor/order");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_day=" + cmd.getEndDay());
			}
			params.append("&page_size=" + page.getPageSize());
			params.append("&start=" + page.getStartRow());
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_day=" + cmd.getStartDay());
			}
		}
		
		HttpResult<PaymentMonitor> result = BmcHttpClient.sendGetForList(uri.toString(), params.toString(), PaymentMonitor.class);
		Collections.sort(result.getData());
		return result;
	}
	
	/**
	 * 用户分布地图数据
	 * @Author：XuYanbo
	 * @Date：2015年1月21日
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_USER, type=BmcConstants.LOG_TYPE_QUERY, desc="获取用户分布统计数据")
	public JSONObject getPayUserChartStatis() throws BmcException {
		JSONObject result = BmcHttpClient.sendGetForJsonObject(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/map", null, true);
		return result;
	}
	
}
