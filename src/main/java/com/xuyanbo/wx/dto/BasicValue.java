/************************************************************************************
 * @File name   :      ItemValue.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月14日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年1月14日 下午4:08:21
 ************************************************************************************/
package com.xuyanbo.wx.dto;

/**
 * @author XuYanbo
 *
 */
public class BasicValue {

	private String name;
	private Integer value;

	public BasicValue(){}
	public BasicValue(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
