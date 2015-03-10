/************************************************************************************
 * @File name   :      RightVo.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月6日
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
 * XuYanbo				1.0				Initial Version				2014年11月6日 上午11:20:11
 ************************************************************************************/
package com.hunantv.mbp.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统功能
 */
public class RightVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer moduleId;
	private String moduleName;
	private String moduleStatus;
	private Integer menuId;
	private String menuName;
	private String menuCode;
	private String menuUrl;
	private String menuStatus;
	private Integer rightId;
	private String rightName;
	private String rightMethod;
	private String rightStatus;
    private Date createTime;
    private Date updateTime;

	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleStatus() {
		return moduleStatus;
	}
	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuStatus() {
		return menuStatus;
	}
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightMethod() {
		return rightMethod;
	}
	public void setRightMethod(String rightMethod) {
		this.rightMethod = rightMethod;
	}
	public String getRightStatus() {
		return rightStatus;
	}
	public void setRightStatus(String rightStatus) {
		this.rightStatus = rightStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
}
