/************************************************************************************
 * @File name   :      PaymentApi.java
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
 * XuYanbo				1.0				Initial Version				2014年11月21日 上午11:08:21
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 支付系统API调用监控数据
 * @author XuYanbo
 */
public class PaymentApi extends BaseDataObject implements Comparable<PaymentApi> {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String api_name;
	private Integer call_count;
	private Integer error_count;
	private Integer warning_count;
	private String avg_time;
	private String monitor_type;
	private String monitor_date;
	private String monitor_time;
	private String create_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApi_name() {
		return api_name;
	}
	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}
	public Integer getCall_count() {
		return call_count;
	}
	public void setCall_count(Integer call_count) {
		this.call_count = call_count;
	}
	public Integer getError_count() {
		return error_count;
	}
	public void setError_count(Integer error_count) {
		this.error_count = error_count;
	}
	public Integer getWarning_count() {
		return warning_count;
	}
	public void setWarning_count(Integer warning_count) {
		this.warning_count = warning_count;
	}
	public String getAvg_time() {
		return avg_time;
	}
	public void setAvg_time(String avg_time) {
		this.avg_time = avg_time;
	}
	public String getMonitor_type() {
		return monitor_type;
	}
	public void setMonitor_type(String monitor_type) {
		this.monitor_type = monitor_type;
	}
	public String getMonitor_date() {
		return monitor_date;
	}
	public void setMonitor_date(String monitor_date) {
		this.monitor_date = monitor_date;
	}
	public String getMonitor_time() {
		return monitor_time;
	}
	public void setMonitor_time(String monitor_time) {
		this.monitor_time = monitor_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(PaymentApi o) {
		return o.getCreate_time().compareTo(this.getCreate_time());
	}
	
}
