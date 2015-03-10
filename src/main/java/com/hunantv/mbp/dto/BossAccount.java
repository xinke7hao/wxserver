/************************************************************************************
 * @File name   :      BossAccount.java
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
 * BOSS-账户
 * @author XuYanbo
 *
 */
public class BossAccount extends BaseDataObject implements Comparable<BossAccount> {

	private static final long serialVersionUID = -8734928746240983620L;

	private String id;
	private String vip_end_time;
	private String mgb;
	private String vip_str;
	private String point;
	private String level;
	private String vip_begin_time;
	private String business_str;
	private String passport;
	private String status;
	private String status_str;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVip_end_time() {
		return vip_end_time;
	}
	public void setVip_end_time(String vip_end_time) {
		this.vip_end_time = vip_end_time;
	}
	public String getMgb() {
		return mgb;
	}
	public void setMgb(String mgb) {
		this.mgb = mgb;
	}
	public String getVip_str() {
		return vip_str;
	}
	public void setVip_str(String vip_str) {
		this.vip_str = vip_str;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getVip_begin_time() {
		return vip_begin_time;
	}
	public void setVip_begin_time(String vip_begin_time) {
		this.vip_begin_time = vip_begin_time;
	}
	public String getBusiness_str() {
		return business_str;
	}
	public void setBusiness_str(String business_str) {
		this.business_str = business_str;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getStatus_str() {
		return status_str;
	}
	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}

	@Override
	public int compareTo(BossAccount o) {
		return o.getId().compareTo(this.getId());
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
