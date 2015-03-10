/************************************************************************************
 * @File name   :      PieChartData.java
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
 * XuYanbo				1.0				Initial Version				2014年12月11日 上午9:27:52
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.List;

/**
 * 饼图EChart
 * @author XuYanbo
 *
 */
public class PieChartData extends BaseDataObject {

	private static final long serialVersionUID = 1L;
	
	private List<String> legend;
	private List<PieValue> series;
	
	public List<String> getLegend() {
		return legend;
	}
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}
	public List<PieValue> getSeries() {
		return series;
	}
	public void setSeries(List<PieValue> series) {
		this.series = series;
	}
	
}
