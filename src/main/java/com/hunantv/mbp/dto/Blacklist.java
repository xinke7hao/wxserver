/************************************************************************************
 * @File name   :      Blacklist.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月30日
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
 * XuYanbo				1.0				Initial Version				2014年12月30日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 黑名单
 */
public class Blacklist extends BaseDataObject implements Comparable<Blacklist> {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String item;
	private String type_id;
	private String type;
	private String channel_id;
	private String channel_name;
	private String platform_id;
	private String platform_name;
	private String status_id;
	private String status;
	private String create_time;
	private String update_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
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

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月8日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Blacklist o) {
		return o!=null && o.getUpdate_time()!=null ? o.getUpdate_time().compareTo(this.update_time) : 0;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
}
