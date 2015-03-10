/************************************************************************************
 * @File name   :      PaymentPlatform.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月15日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 业务平台
 */
public class PaymentPlatform extends BaseDataObject implements Comparable<PaymentPlatform> {

	private static final long serialVersionUID = 1L;
	
	private Integer platform_id;
	private String platform_name;
	private String remark;
	private String status;
	private String create_time;
	private String update_time;
	private String ext_data;
	private String secret;
	private String contact;
	
	//"platform_id","platform_name","remark","status","create_time","update_time","ext_data","secret","contact"
	
	public PaymentPlatform(){}

	public Integer getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(Integer platform_id) {
		this.platform_id = platform_id;
	}

	public String getPlatform_name() {
		return platform_name;
	}

	public void setPlatform_name(String platform_name) {
		this.platform_name = platform_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getExt_data() {
		return ext_data;
	}

	public void setExt_data(String ext_data) {
		this.ext_data = ext_data;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月8日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(PaymentPlatform o) {
		return o!=null && o.getPlatform_id()!=null ? o.getPlatform_id().compareTo(this.platform_id) : 0;
	}
	
}
