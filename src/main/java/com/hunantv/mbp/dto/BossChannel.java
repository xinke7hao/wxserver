/************************************************************************************
 * @File name   :      BossChannel.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月29日
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
 * XuYanbo				1.0				Initial Version				2015年1月29日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS支付渠道
 */
public class BossChannel extends BaseDataObject {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String uuid;
	private String status;
	private String status_str;
	private String created_at;
	private String fee_percent;
	private String updated_at;
	private String discount;
	private String fee_per_trans;
	private String discount_str;
	private String fee_str;
	
	public BossChannel(){}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_str() {
		return status_str;
	}

	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getFee_percent() {
		return fee_percent;
	}

	public void setFee_percent(String fee_percent) {
		this.fee_percent = fee_percent;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getFee_per_trans() {
		return fee_per_trans;
	}

	public void setFee_per_trans(String fee_per_trans) {
		this.fee_per_trans = fee_per_trans;
	}

	public String getDiscount_str() {
		return discount_str;
	}

	public void setDiscount_str(String discount_str) {
		this.discount_str = discount_str;
	}

	public String getFee_str() {
		return fee_str;
	}

	public void setFee_str(String fee_str) {
		this.fee_str = fee_str;
	}
	
}
