/************************************************************************************
 * @File name   :      PaycountStatis.java
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
 * BOSS-VIP购买人数统计
 * @author XuYanbo
 */
public class PaycountStatis extends BaseDataObject {
	
	private static final long serialVersionUID = 1L;
	
	private String date;
	private Integer all_cnt;
	private Integer first_cnt;
	private Integer again_cnt;
	
	public Integer getAll_cnt() {
		return all_cnt;
	}
	public void setAll_cnt(Integer all_cnt) {
		this.all_cnt = all_cnt;
	}
	public Integer getFirst_cnt() {
		return first_cnt;
	}
	public void setFirst_cnt(Integer first_cnt) {
		this.first_cnt = first_cnt;
	}
	public Integer getAgain_cnt() {
		return again_cnt;
	}
	public void setAgain_cnt(Integer again_cnt) {
		this.again_cnt = again_cnt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
