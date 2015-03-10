/************************************************************************************
 * @File name   :      PaymentOrder.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月12日
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
 * 支付流水
 */
public class PaymentOrder extends BaseDataObject implements Comparable<PaymentOrder> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String business_order_id;
	private String product_id;
	private String product_name;
	private String channel_id;
	private String channel_name;
	private String amount;
	private String pay_code;
	private String status;
	private String create_time;
	private String recon_code;
	private String recon_status;
	private String recon_time;
	private String business_code;
	private String business_result;
	private String notify_time;
	private String fix_type;
	private String mobile;
	private String account;
	
	public PaymentOrder(){}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusiness_order_id() {
		return business_order_id;
	}

	public void setBusiness_order_id(String business_order_id) {
		this.business_order_id = business_order_id;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getRecon_status() {
		return recon_status;
	}

	public void setRecon_status(String recon_status) {
		this.recon_status = recon_status;
	}

	public String getRecon_time() {
		return recon_time;
	}

	public void setRecon_time(String recon_time) {
		this.recon_time = recon_time;
	}

	public String getBusiness_result() {
		return business_result;
	}

	public void setBusiness_result(String business_result) {
		this.business_result = business_result;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getFix_type() {
		return fix_type;
	}

	public void setFix_type(String fix_type) {
		this.fix_type = fix_type;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月8日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(PaymentOrder o) {
		return o!=null && o.getId()!=null ? o.getId().compareTo(this.getId()) : 0;
	}

	public String getPay_code() {
		return pay_code;
	}

	public void setPay_code(String pay_code) {
		this.pay_code = pay_code;
	}

	public String getRecon_code() {
		return recon_code;
	}

	public void setRecon_code(String recon_code) {
		this.recon_code = recon_code;
	}

	public String getBusiness_code() {
		return business_code;
	}

	public void setBusiness_code(String business_code) {
		this.business_code = business_code;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
