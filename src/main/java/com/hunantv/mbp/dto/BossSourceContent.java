/************************************************************************************
 * @File name   :      BossSourceContent.java
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
 * XuYanbo				1.0				Initial Version				2015年1月26日 下午4:24:37
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS-频道源
 * @author XuYanbo
 *
 */
public class BossSourceContent extends BaseDataObject {

	private static final long serialVersionUID = 2083307239684475633L;
	private String status_str;
	private String channel_name;
	private String updated_at;
	private String quality_name;
	private String id;
	private String channel_flag;
	private String min_vip_id;
	private String min_vip_str;
	
	public String getStatus_str() {
		return status_str;
	}
	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}
	public String getChannel_name() {
		return channel_name;
	}
	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getQuality_name() {
		return quality_name;
	}
	public void setQuality_name(String quality_name) {
		this.quality_name = quality_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannel_flag() {
		return channel_flag;
	}
	public void setChannel_flag(String channel_flag) {
		this.channel_flag = channel_flag;
	}
	public String getMin_vip_str() {
		return min_vip_str;
	}
	public void setMin_vip_str(String min_vip_str) {
		this.min_vip_str = min_vip_str;
	}
	public String getMin_vip_id() {
		return min_vip_id;
	}
	public void setMin_vip_id(String min_vip_id) {
		this.min_vip_id = min_vip_id;
	}

}
