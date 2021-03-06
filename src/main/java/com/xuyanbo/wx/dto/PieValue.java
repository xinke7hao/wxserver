/************************************************************************************
 * @File name   :      PieValue.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月11日
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
 * XuYanbo				1.0				Initial Version				2014年12月11日 下午5:16:18
 ************************************************************************************/
package com.xuyanbo.wx.dto;

/**
 * @author XuYanbo
 *
 */
public class PieValue {
	
	private String name;
	private Integer value;
	
	public PieValue(){}
	
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
