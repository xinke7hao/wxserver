/************************************************************************************
 * @File name   :      PaytimeStatis.java
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
 * XuYanbo				1.0				Initial Version				2015年1月14日 上午11:08:21
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS-购买统计
 * @author XuYanbo
 */
public class PaytimeStatis extends BaseDataObject {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String date;
	private Double times;
	private Double money;
	
	public PaytimeStatis(){}
	public PaytimeStatis(String name, String date, Double times, Double money) {
		this.name = name;
		this.date = date;
		this.times = times;
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getTimes() {
		return times;
	}
	public void setTimes(Double times) {
		this.times = times;
	}
	
}
