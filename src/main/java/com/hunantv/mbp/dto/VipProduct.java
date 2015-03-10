/************************************************************************************
 * @File name   :      VipProduct.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月15日
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
 * XuYanbo				1.0				Initial Version				2015年1月15日 上午11:30:43
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * OTT-BOSS-VIP产品列表
 * @author XuYanbo
 *
 */
public class VipProduct extends BaseDataObject {

	private static final long serialVersionUID = 8438244480747642633L;
	
	private String id;
	private String name;
	
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
	
}
