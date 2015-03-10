/************************************************************************************
 * @File name   :      BossAssetCommand.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月13日
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
 * XuYanbo				1.0				Initial Version				2015年2月13日 下午5:22:50
 ************************************************************************************/
package com.hunantv.mbp.command;

/**
 * 媒资文件搜索
 * @author XuYanbo
 */
public class BossAssetCommand {

	private String id;
	private String asset_id;
	private String asset_name;
	private String hashcode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
}
