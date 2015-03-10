/************************************************************************************
 * @File name   :      EChartSeries.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月20日
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
 * XuYanbo				1.0				Initial Version				2014年11月20日 下午6:05:27
 ************************************************************************************/
package com.hunantv.mbp.entity.echarts;

import java.util.ArrayList;
import java.util.List;

/**
 * EChart 数据集 
 * @author XuYanbo
 */
public class Series<T> {

	private Integer id;
	private String name;
	private String type;
	private List<T> data = new ArrayList<T>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
