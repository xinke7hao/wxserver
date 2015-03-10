/************************************************************************************
 * @File name   :      SpringTestUtil.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月19日
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
 * XuYanbo				1.0				Initial Version				2014年11月19日 下午4:54:39
 ************************************************************************************/
package com.hunantv.mbp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUrlEncodeUtil;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.dto.BasicValue;
import com.hunantv.mbp.entity.admin.User;
import com.hunantv.mbp.service.UserService;

/**
 * @author XuYanbo
 *
 */
public class AppTest {

	public static void testAspect() throws BmcException{
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		UserService userService = ctx.getBean(UserService.class);
		String usercode = "admin";
		User user = userService.getUserByCode(usercode);
		if(user!=null){
			System.out.println(user.getUserName());
		} else {
			System.out.println("user is null");
		}
		ctx.close();
	}
	
	public static void testMD5(){
		String key = "m(ongo%4qwei(*2&)!TV)";
//		String key = "123";	//202cb962ac59075b964b07152d234b70
		System.out.println(DigestUtils.md5Hex(key));	//f344972d6b2daf008b089615159b0240
	}
	
	public static void testFastJson(){
		String str = BmcLocalServerForTest.getResponseContent();
		JSONObject jsonObj = JSONObject.parseObject(str);
		if(jsonObj!=null){
			JSONObject data = JSON.parseObject(jsonObj.get(HttpServiceConstants.RESULT_DATA).toString());
			for(String account: data.keySet()){
				JSONObject monthData = JSON.parseObject(data.get(account).toString());
				for(String month: monthData.keySet()){
					JSONObject pData = JSON.parseObject(monthData.get(month).toString());
					for(String platformId: pData.keySet()){
						System.out.println(platformId + " -> " + pData.get(platformId));
					}
				}
			}
		}
	}
	
	/**
	 * 测试Java8新特性
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 */
	public static void testNewCharacter(){
		List<BasicValue> values = new ArrayList<BasicValue>();
		values.add(new BasicValue("a", 1));
		values.add(new BasicValue("b", 2));
		values.add(new BasicValue("c", 3));
		values.add(new BasicValue("d", 4));
		Map<Object, Object> map = values.stream().collect(Collectors.toMap(x->x.getName(), x->x.getValue()));
		map.forEach((k,v)->{System.out.println(k+"="+v);});
	}
	
	public static void printObjectFields(){
		String filepath = "D:/Develop/GitRepository/boss-mbp/src/main/java/com/hunantv/mbp/utils/fields.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
			String line = null;
			while((line = br.readLine()) != null){
				System.out.print("private String ");
				for(int i=0;i<line.length();i++){
					if(line.charAt(i)==':'){
						System.out.println(";");
						break;
					} else {
						System.out.print(line.charAt(i));
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		testAspect();
//		testMD5();
//		testFastJson();
//		testNewCharacter();
//		printObjectFields();
//		System.out.println(BmcUrlEncodeUtil.encodeURIComponent("Heart to Heart"));
	}
}
