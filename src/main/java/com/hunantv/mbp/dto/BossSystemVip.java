/************************************************************************************
 * @File name   :      BossSystemVip.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月12日
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
 * XuYanbo				1.0				Initial Version				2015年2月12日 上午9:37:29
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.List;

/**
 * @author XuYanbo
 *
 */
public class BossSystemVip {

	private String name;
	private String weight;
	private String created_at;
	private String updated_at;
	private String id;
	private String quality_ids;
	private String qualities_str;
	private String status;
	private String status_str;
	private List<BossSystemBitrate> qualities;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQualities_str() {
		return qualities_str;
	}
	public void setQualities_str(String qualities_str) {
		this.qualities_str = qualities_str;
	}
	public String getQuality_ids() {
		return quality_ids;
	}
	public void setQuality_ids(String quality_ids) {
		this.quality_ids = quality_ids;
	}
	public List<BossSystemBitrate> getQualities() {
		return qualities;
	}
	public void setQualities(List<BossSystemBitrate> qualities) {
		this.qualities = qualities;
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
	
}
