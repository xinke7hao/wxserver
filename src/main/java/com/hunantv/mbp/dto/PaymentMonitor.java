/************************************************************************************
 * @File name   :      PaymentMonitor.java
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
 * 支付系统支付流水监控数据
 * @author XuYanbo
 */
public class PaymentMonitor extends BaseDataObject implements Comparable<PaymentMonitor> {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Integer total_count;
	private Integer pay_count;
	private Integer sync_count;
	private Integer async_count;
	private Integer close_count;
	private Integer business_count;
	private String monitor_date;
	private String monitor_time;
	private String create_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	public Integer getPay_count() {
		return pay_count;
	}
	public void setPay_count(Integer pay_count) {
		this.pay_count = pay_count;
	}
	public Integer getSync_count() {
		return sync_count;
	}
	public void setSync_count(Integer sync_count) {
		this.sync_count = sync_count;
	}
	public Integer getAsync_count() {
		return async_count;
	}
	public void setAsync_count(Integer async_count) {
		this.async_count = async_count;
	}
	public Integer getClose_count() {
		return close_count;
	}
	public void setClose_count(Integer close_count) {
		this.close_count = close_count;
	}
	public Integer getBusiness_count() {
		return business_count;
	}
	public void setBusiness_count(Integer business_count) {
		this.business_count = business_count;
	}
	
	@Override
	public int compareTo(PaymentMonitor o) {
		return o.getCreate_time().compareTo(this.getCreate_time());
	}
	
}
