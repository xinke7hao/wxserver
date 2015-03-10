/************************************************************************************
 * @File name   :      MonitorCommand.java
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
 * XuYanbo				1.0				Initial Version				2014年11月21日 上午11:16:15
 ************************************************************************************/
package com.xuyanbo.wx.command;

/**
 * API监控搜索条件
 * @author XuYanbo
 */
public class MonitorCommand {

	private String apiName;
	private String startDay;
	private String endDay;
	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
}
