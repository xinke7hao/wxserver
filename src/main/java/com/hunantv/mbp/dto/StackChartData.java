/************************************************************************************
 * @File name   :      StackChartData.java
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
 * XuYanbo				1.0				Initial Version				2015年1月14日 上午9:27:52
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.List;

/**
 * 堆积图EChart
 * @author XuYanbo
 *
 */
public class StackChartData extends BaseDataObject {

	private static final long serialVersionUID = 1L;
	
	private List<String> category;
	private String[] legend;
	private List<StackValue> series;
	private double total;
	private double[] totals;
	
	//addition for money chart
	private List<StackValue> moneySeries;
	private double totalMoney;
	private double[] totalMoneys;
	
	public StackChartData(){}
	
	public String[] getLegend() {
		return legend;
	}
	public void setLegend(String[] legend) {
		this.legend = legend;
	}
	public List<String> getCategory() {
		return category;
	}
	public void setCategory(List<String> category) {
		this.category = category;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<StackValue> getMoneySeries() {
		return moneySeries;
	}

	public void setMoneySeries(List<StackValue> moneySeries) {
		this.moneySeries = moneySeries;
	}

	public double[] getTotalMoneys() {
		return totalMoneys;
	}

	public void setTotalMoneys(double[] totalMoneys) {
		this.totalMoneys = totalMoneys;
	}

	public List<StackValue> getSeries() {
		return series;
	}

	public void setSeries(List<StackValue> series) {
		this.series = series;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double[] getTotals() {
		return totals;
	}

	public void setTotals(double[] totals) {
		this.totals = totals;
	}
	
}
