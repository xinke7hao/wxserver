/************************************************************************************
 * @File name   :      BossSingleProduct.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月11日
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
 * XuYanbo				1.0				Initial Version				2015年2月11日 下午5:33:59
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * @author XuYanbo
 *
 */
public class BossSingleProduct {
	
	private String updated_at;
	private String yuan_price;
	private String id;
	private String name;
	private String created_at;
	private String days;
	
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getYuan_price() {
		return yuan_price;
	}
	public void setYuan_price(String yuan_price) {
		this.yuan_price = yuan_price;
	}
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	
}
