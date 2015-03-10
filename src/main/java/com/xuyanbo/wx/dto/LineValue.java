/************************************************************************************
 * @File name   :      LineValue.java
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
 * XuYanbo				1.0				Initial Version				2014年12月11日 下午5:16:47
 ************************************************************************************/
package com.xuyanbo.wx.dto;

import java.util.List;

/**
 * @author XuYanbo
 *
 */
public class LineValue {

	private String name;
	private String smooth;
	private String type = "line";
	private List<Double> data;

	public LineValue(){}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSmooth() {
		return smooth;
	}

	public void setSmooth(String smooth) {
		this.smooth = smooth;
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

}
