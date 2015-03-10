/************************************************************************************
 * @File name   :      BossPackContent.java
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
 * XuYanbo				1.0				Initial Version				2015年2月11日 下午4:16:03
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.List;

/**
 * @author XuYanbo
 *
 */
public class BossPackAssetContent {

	private String updated_at;
	private String yuan_price;
	private String id;
	private String name;
	private String days;
	private String sync_sid;
	private List<BossSingleContent> assets;
	
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
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getSync_sid() {
		return sync_sid;
	}
	public void setSync_id(String sync_sid) {
		this.sync_sid = sync_sid;
	}
	public List<BossSingleContent> getAssets() {
		return assets;
	}
	public void setAssets(List<BossSingleContent> assets) {
		this.assets = assets;
	}
	public void setSync_sid(String sync_sid) {
		this.sync_sid = sync_sid;
	}
}
