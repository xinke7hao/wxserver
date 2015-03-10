/************************************************************************************
 * @File name   :      BossUtils.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月5日
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
 * XuYanbo				1.0				Initial Version				2014年11月5日 下午2:16:03
 ************************************************************************************/
package com.xuyanbo.wx.utils;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 系统工具类
 */
public class BmcUtils {

	public static SerializerFeature[] features = {SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
		SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect};
	
	/**
	 * 获取客户端真实IP
	 * @Author：XuYanbo
	 * @Date：2015年1月9日
	 * @param request
	 * @return
	 */
	public static String getRealClientIP(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		String localIP = "127.0.0.1";
		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) ||    "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 按照通用规则将对象的属性名转换成数据库中的列名
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param fieldName
	 * @return
	 */
	public static String toDatabaseColumnName(String fieldName){
		StringBuilder cname = new StringBuilder();
		for(int i=0;i<fieldName.length();i++){
			char c = fieldName.charAt(i);
			cname.append(Character.isUpperCase(c) ? ("_" + Character.toLowerCase(c)) : c);
		}
		return cname.toString();
	}
	
	/**
	 * 按照通用规则将数据库中的列名转换成对象的属性名
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param columnName
	 * @return
	 */
	public static String toObjectFieldName(String columnName){
		StringBuilder fname = new StringBuilder();
		boolean nextUpper = false;
		for(int i=0;i<columnName.length();i++){
			char c = columnName.charAt(i);
			if(c=='_'){
				nextUpper = true;
			} else {
				fname.append(nextUpper ? Character.toUpperCase(c) : c);
				nextUpper = false;
			}
		}
		return fname.toString();
	}
	
	//Test
//	public static void main(String[] args){
//		String t1 = "this_is_a_column_name";
//		System.out.println(BossUtils.toObjectFieldName(t1));
//		String t2 = "thisIsAFieldName";
//		System.out.println(BossUtils.toDatabaseColumnName(t2));
//	}
}
