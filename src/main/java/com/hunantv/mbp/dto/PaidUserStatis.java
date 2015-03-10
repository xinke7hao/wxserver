/************************************************************************************
 * @File name   :      PaidUserStatis.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月27日
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
 * XuYanbo				1.0				Initial Version				2015年1月27日 上午11:08:21
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS-付费用户统计
 * @author XuYanbo
 */
public class PaidUserStatis extends BaseDataObject {
	
	private static final long serialVersionUID = 1L;
	
	private String data_name;
	private Integer all_cnt;
	private Integer valid_cnt;
	private String date;
	
	public Integer getAll_cnt() {
		return all_cnt;
	}
	public void setAll_cnt(Integer all_cnt) {
		this.all_cnt = all_cnt;
	}
	public String getData_name() {
		return data_name;
	}
	public void setData_name(String data_name) {
		this.data_name = data_name;
	}
	public Integer getValid_cnt() {
		return valid_cnt;
	}
	public void setValid_cnt(Integer valid_cnt) {
		this.valid_cnt = valid_cnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
