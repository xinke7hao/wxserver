/************************************************************************************
 * @File name   :      PaymentChannel.java
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
 * 支付渠道
 */
public class PaymentChannel extends BaseDataObject implements Comparable<PaymentChannel> {

	private static final long serialVersionUID = 1L;

	private Integer channel_id;
	private String channel_code;
	private String channel_name;
	private String channel_type;
	private String channel_alias;
	private String agent_name;
	private String is_available;
	private String pay_url;
	private String return_url;
	private String notify_url;
	private String query_url;
	private String key;
	private String per;
	private String cper;
	private String secret;
	private String seller;
	private String seller_name;
	private String app_id;
	private String app_secret;
	private String app_signkey;
	private String create_time;
	private String update_time;
	private String order_rate;
	
	public PaymentChannel(){}

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getIs_available() {
		return is_available;
	}

	public void setIs_available(String is_available) {
		this.is_available = is_available;
	}

	public String getPay_url() {
		return pay_url;
	}

	public void setPay_url(String pay_url) {
		this.pay_url = pay_url;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getChannel_code() {
		return channel_code;
	}

	public void setChannel_code(String channel_code) {
		this.channel_code = channel_code;
	}

	public String getChannel_type() {
		return channel_type;
	}

	public void setChannel_type(String channel_type) {
		this.channel_type = channel_type;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getQuery_url() {
		return query_url;
	}

	public void setQuery_url(String query_url) {
		this.query_url = query_url;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getChannel_alias() {
		return channel_alias;
	}

	public void setChannel_alias(String channel_alias) {
		this.channel_alias = channel_alias;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getCper() {
		return cper;
	}

	public void setCper(String cper) {
		this.cper = cper;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}

	public String getApp_signkey() {
		return app_signkey;
	}

	public void setApp_signkey(String app_signkey) {
		this.app_signkey = app_signkey;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月8日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(PaymentChannel o) {
		return o!=null && o.getChannel_id()!=null ? o.getChannel_id().compareTo(this.getChannel_id()) : 0;
	}

	public String getOrder_rate() {
		return order_rate;
	}

	public void setOrder_rate(String order_rate) {
		this.order_rate = order_rate;
	}
	
}
