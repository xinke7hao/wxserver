package com.hunantv.mbp.entity.admin;

import java.io.Serializable;

public class Menu implements Serializable, Comparable<Menu> {
	
	private static final long serialVersionUID = 5103777730322568143L;

	private Integer menuId;
	private Integer moduleId;
	private String moduleName;
	private Integer menuPid;
	private String menuCode;
	private String menuName;
	private String menuUrl;
	private String menuStatus;
	private Integer menuIndex;
	private String menuMethods;
	private String menuDesc;
	private String icon;

	public String getMenuMethods() {
		return menuMethods;
	}

	public void setMenuMethods(String menuMethods) {
		this.menuMethods = menuMethods;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(Integer menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode == null ? null : menuCode.trim();
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus == null ? null : menuStatus.trim();
	}

	public Integer getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(Integer menuIndex) {
		this.menuIndex = menuIndex;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Menu o) {
		return this.menuIndex.compareTo(o.getMenuIndex());
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
}