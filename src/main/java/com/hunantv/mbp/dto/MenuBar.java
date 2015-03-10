/************************************************************************************
 * @File name   :      MenuBar.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月9日
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
 * XuYanbo				1.0				Initial Version				2015年1月9日 上午10:44:45
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.util.ArrayList;
import java.util.List;

import com.hunantv.mbp.entity.admin.Menu;

/**
 * 首页菜单
 */
public class MenuBar extends BaseDataObject {

	private static final long serialVersionUID = 1L;
	
	private String moduleName;
	private List<Menu> menus = new ArrayList<Menu>();
	
	public MenuBar(){}
	public MenuBar(String moduleName){
		this.moduleName = moduleName;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}
