/************************************************************************************
 * @File name   :      ItemValue.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月14日
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
 * XuYanbo				1.0				Initial Version				2015年1月14日 下午4:08:21
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * @author XuYanbo
 *
 */
public class ItemValue {

	private String label;
	private Double value;
	private Double money;
	
	public ItemValue(){}
	public ItemValue(String label, Double value, Double money){
		this.label = label;
		this.value = value;
		this.money = money;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
