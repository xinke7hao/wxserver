/************************************************************************************
 * @File name   :      BossAsset.java
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
 * XuYanbo				1.0				Initial Version				2015年2月11日 下午4:20:30
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * @author XuYanbo
 *
 */
public class BossAsset {

	private String id;
	private String asset_name;
	private String asset_partial_number;
	private String hashcode;
	private String quality_str;
	private String min_vip_str;
	private String created_at;
	private String updated_at;
	
	public String getAsset_partial_number() {
		return asset_partial_number;
	}
	public void setAsset_partial_number(String asset_partial_number) {
		this.asset_partial_number = asset_partial_number;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getQuality_str() {
		return quality_str;
	}
	public void setQuality_str(String quality_str) {
		this.quality_str = quality_str;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMin_vip_str() {
		return min_vip_str;
	}
	public void setMin_vip_str(String min_vip_str) {
		this.min_vip_str = min_vip_str;
	}
	
}
