/************************************************************************************
 * @File name   :      BossPurchaseCommand.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月12日
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
 * XuYanbo				1.0				Initial Version				2015年1月12日 上午11:43:08
 ************************************************************************************/
package com.hunantv.mbp.command;

/**
 * @author XuYanbo
 *
 */
public class BossPurchaseCommand {

	private String account;
	private String orderId;
	private String onlyValid;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOnlyValid() {
		return onlyValid;
	}
	public void setOnlyValid(String onlyValid) {
		this.onlyValid = onlyValid;
	}
	
}
