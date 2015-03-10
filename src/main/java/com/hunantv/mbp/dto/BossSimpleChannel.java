/************************************************************************************
 * @File name   :      BossSimpleChannel.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月29日
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
 * XuYanbo				1.0				Initial Version				2015年1月29日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * BOSS支付渠道
 */
public class BossSimpleChannel extends BaseDataObject {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String uuid;
	
	public BossSimpleChannel(){}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
