/************************************************************************************
 * @File name   :      BossPurchase.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月26日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年1月26日 下午4:24:37
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS-订购关系
 * @author XuYanbo
 *
 */
public class BossPurchase extends BaseDataObject implements Comparable<BossPurchase> {

	private static final long serialVersionUID = -8734928746240983620L;

	private String id;
	private String account_passport;
	private String order_uuid;
	private String order_paid_at;
	private String order_created_at;
	private String channel_name;
	private String product_name;
	private String begin_time;
	private String end_time;
	private String quantity;
	private String category_str;
	private String delivered_at;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount_passport() {
		return account_passport;
	}
	public void setAccount_passport(String account_passport) {
		this.account_passport = account_passport;
	}
	public String getOrder_uuid() {
		return order_uuid;
	}
	public void setOrder_uuid(String order_uuid) {
		this.order_uuid = order_uuid;
	}
	public String getOrder_paid_at() {
		return order_paid_at;
	}
	public void setOrder_paid_at(String order_paid_at) {
		this.order_paid_at = order_paid_at;
	}
	public String getOrder_created_at() {
		return order_created_at;
	}
	public void setOrder_created_at(String order_created_at) {
		this.order_created_at = order_created_at;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCategory_str() {
		return category_str;
	}
	public void setCategory_str(String category_str) {
		this.category_str = category_str;
	}
	public String getDelivered_at() {
		return delivered_at;
	}
	public void setDelivered_at(String delivered_at) {
		this.delivered_at = delivered_at;
	}
	
	@Override
	public int compareTo(BossPurchase o) {
		return o.getId().compareTo(this.getId());
	}
}
