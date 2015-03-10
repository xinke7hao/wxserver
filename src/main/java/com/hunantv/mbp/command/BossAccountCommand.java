/************************************************************************************
 * @File name   :      BossAccountCommand.java
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
public class BossAccountCommand {

	private String accountId;
	private String accountName;
	private String accountType;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
