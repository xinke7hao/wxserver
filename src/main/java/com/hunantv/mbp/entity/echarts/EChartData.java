/************************************************************************************
 * @File name   :      EChartData.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月21日
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
 * XuYanbo				1.0				Initial Version				2014年11月21日 上午9:20:24
 ************************************************************************************/
package com.hunantv.mbp.entity.echarts;

import java.util.List;

/**
 * EChart 数据封装类 
 * @author XuYanbo
 */
public class EChartData<T> {

	private List<String> legend;
	private List<String> category;
	private List<Series<T>> series;
	
	public List<Series<T>> getSeries() {
		return series;
	}
	public void setSeries(List<Series<T>> series) {
		this.series = series;
	}
	public List<String> getLegend() {
		return legend;
	}
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	
}
