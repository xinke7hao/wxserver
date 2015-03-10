/************************************************************************************
 * @File name   :      StackValue.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月14日
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
 * XuYanbo				1.0				Initial Version				2015年1月14日 下午5:16:47
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.List;

/**
 * @author XuYanbo
 *
 */
public class StackValue {

	private String stack = "group1";
	private String name;
	private String type = "bar";
	private List<Double> data;

	public StackValue(){}
	public StackValue(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getData() {
		return data;
	}

	public void setData(List<Double> data) {
		this.data = data;
	}

	public String getStack() {
		return stack;
	}

	public void setStack(String stack) {
		this.stack = stack;
	}

}
