/************************************************************************************
 * @File name   :      MenuDesc.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月10日
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
 * XuYanbo				1.0				Initial Version				2015年2月10日 下午4:06:18
 ************************************************************************************/
package com.xuyanbo.wx.dto;

/**
 * @author XuYanbo
 *
 */
public class MenuDesc {

	private Integer menuId;
	private String menuDesc;

	public MenuDesc(){}
	public MenuDesc(Integer menuId, String menuDesc) {
		this.menuId = menuId;
		this.menuDesc = menuDesc;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	
}
