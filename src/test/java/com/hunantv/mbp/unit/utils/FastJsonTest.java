/************************************************************************************
 * @File name   :      FastJsonTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月4日
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 上午10:54:10
 ************************************************************************************/
package com.hunantv.mbp.unit.utils;


import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * FASTJSON测试类
 * @author XuYanbo
 *
 */
public class FastJsonTest {
	
	@Test
	public void testParseString(){
		String srcString = "{\"result\":{"
				+ "\"0\":{\"id\":\"1006\",\"name\":\"平台aaa\"},"
				+ "\"1\":{\"id\":\"1001\",\"name\":\"网页游戏外包商\"},"
				+ "\"2\":{\"id\":\"110\",\"name\":\"AAA\"},"
				+ "\"3\":{\"id\":\"107\",\"name\":\"芒果TV-湖南铁通\"},"
				+ "\"4\":{\"id\":\"106\",\"name\":\"视达科ITV\"},"
				+ "\"5\":{\"id\":\"105\",\"name\":\"芒果圈\"},"
				+ "\"6\":{\"id\":\"104\",\"name\":\"酷视ITV\"},"
				+ "\"7\":{\"id\":\"103\",\"name\":\"芒果TV\"},"
				+ "\"8\":{\"id\":\"102\",\"name\":\"他在\"},"
				+ "\"9\":{\"id\":\"101\",\"name\":\"哈哈\"},"
				+ "\"10\":{\"id\":\"4\",\"name\":\"信息接入系统\"},"
				+ "\"11\":{\"id\":\"3\",\"name\":\"用户中心\"},"
				+ "\"12\":{\"id\":\"2\",\"name\":\"测试中心核心层\"},"
				+ "\"13\":{\"id\":\"1\",\"name\":\"充值中心核心层\"}"
				+ "}}";
		
		TmpResult obj = JSONObject.parseObject(srcString, TmpResult.class);
		JSONObject obj2 = JSONObject.parseObject(srcString);
		Iterator<Entry<String, Object>> it = obj2.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Object> e = it.next();
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
		System.out.println(obj.getResult().toString());
		System.out.println(obj2.toString());
	}

}

class TmpResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String, TmpUser> result;

	public Map<String, TmpUser> getResult() {
		return result;
	}
	public void setResult(Map<String, TmpUser> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(result!=null){
			Iterator<Entry<String, TmpUser>> it = result.entrySet().iterator();
			while(it.hasNext()){
				Entry<String, TmpUser> e = it.next();
				sb.append("** "+e.getKey()+":"+e.getValue().toString()+" **");
			}
		}
		return sb.toString();
	}
}

class TmpUser implements Serializable {
	private static final long serialVersionUID = 1L;
	String id;
	String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return this.id + "->" + this.name;
	}
}
