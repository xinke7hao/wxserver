/************************************************************************************
 * @File name   :      AppleQueryCommand.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月13日
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
 * XuYanbo				1.0				Initial Version				2015年1月13日 上午11:16:15
 ************************************************************************************/
package com.hunantv.mbp.command;

/**
 * 苹果支付搜索条件
 * @author XuYanbo
 */
public class AppleQueryCommand {

	private String orderId;
	private String payAccount;
	private String receipt;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
}
