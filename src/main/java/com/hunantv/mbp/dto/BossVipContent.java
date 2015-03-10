/************************************************************************************
 * @File name   :      BossVipContent.java
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
 * XuYanbo				1.0				Initial Version				2015年2月11日 下午3:37:20
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * @author XuYanbo
 *
 */
public class BossVipContent {

	private String is_vip_product;
	private String updated_at;
	private String status_str;
	private String id;
	private String is_single_product;
	private String operation_mode_str;
	private String is_collection_product;
	private String kind;
	private String name;
	
	//for detail
	private String operation_mode;
	private String min_vip_str;
	private String start_charges;
	private String preview;
	private String mark_id;
	
	public String getIs_vip_product() {
		return is_vip_product;
	}
	public void setIs_vip_product(String is_vip_product) {
		this.is_vip_product = is_vip_product;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getStatus_str() {
		return status_str;
	}
	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}
	public String getOperation_mode_str() {
		return operation_mode_str;
	}
	public void setOperation_mode_str(String operation_mode_str) {
		this.operation_mode_str = operation_mode_str;
	}
	public String getStart_charges() {
		return start_charges;
	}
	public void setStart_charges(String start_charges) {
		this.start_charges = start_charges;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIs_single_product() {
		return is_single_product;
	}
	public void setIs_single_product(String is_single_product) {
		this.is_single_product = is_single_product;
	}
	public String getMin_vip_str() {
		return min_vip_str;
	}
	public void setMin_vip_str(String min_vip_str) {
		this.min_vip_str = min_vip_str;
	}
	public String getIs_collection_product() {
		return is_collection_product;
	}
	public void setIs_collection_product(String is_collection_product) {
		this.is_collection_product = is_collection_product;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperation_mode() {
		return operation_mode;
	}
	public void setOperation_mode(String operation_mode) {
		this.operation_mode = operation_mode;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getMark_id() {
		return mark_id;
	}
	public void setMark_id(String mark_id) {
		this.mark_id = mark_id;
	}
}
