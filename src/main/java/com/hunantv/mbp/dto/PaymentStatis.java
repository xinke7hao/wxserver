/************************************************************************************
 * @File name   :      PaymentStatis.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月15日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 支付流水按日统计汇总
 */
public class PaymentStatis extends BaseDataObject implements Comparable<PaymentStatis> {

	private static final long serialVersionUID = 1L;

	//支付流水维度
	private String id;

	//业务平台维度
	private String platform_id;
	private String platform_name;
	
	//支付渠道维度
	private String channel_id;
	private String channel_name;
	private String channel_alias;

	//对账数据
	private Integer total_count;
	private String total_amount;
	private String total_income;
	private String total_poundage;
	
	private Integer finish_count;
	private Integer sync_count;
	private Integer async_count;
	private Integer close_count;
	private Integer recon_count;
	private Integer business_count;
	private String day;
	private String create_time;
	
	public PaymentStatis(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_alias() {
		return channel_alias;
	}

	public void setChannel_alias(String channel_alias) {
		this.channel_alias = channel_alias;
	}

	public Integer getFinish_count() {
		return finish_count;
	}

	public void setFinish_count(Integer finish_count) {
		this.finish_count = finish_count;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
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

	public Integer getRecon_count() {
		return recon_count;
	}

	public void setRecon_count(Integer recon_count) {
		this.recon_count = recon_count;
	}

	public Integer getBusiness_count() {
		return business_count;
	}

	public void setBusiness_count(Integer business_count) {
		this.business_count = business_count;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(PaymentStatis o) {
		return o.getCreate_time().compareTo(this.getCreate_time());
	}

	public String getTotal_income() {
		return total_income;
	}

	public void setTotal_income(String total_income) {
		this.total_income = total_income;
	}

	public String getTotal_poundage() {
		return total_poundage;
	}

	public void setTotal_poundage(String total_poundage) {
		this.total_poundage = total_poundage;
	}

}
