/************************************************************************************
 * @File name   :      BossRightTag.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月11日
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
 * XuYanbo				1.0				Initial Version				2014年11月11日 上午9:11:34
 ************************************************************************************/
package com.xuyanbo.wx.utils;

import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.xuyanbo.wx.commons.BmcConstants;

/**
 * 判断页面按钮操作权限
 */
public class BmcRightTag extends TagSupport {

	private static final long serialVersionUID = 8751556362814797162L;

	/**
	 * 用户拥有的Menu-Right
	 */
	private Map<String, String> authorities;

	/**
	 * 操作所在模块(MenuCode)
	 */
	private String menu;
	
	/**
	 * 操作所需要的权限(RightMethod)
	 */
	private String right;
	
	/**
	 * 是否管理员
	 */
	private String isAdmin;

	public void setAuthorities(Map<String, String> authorities) {
		this.authorities = authorities;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**
	 * 功能说明：覆盖父类的doEndTag方法
	 * @Author：XuYanbo
	 * @Date：2014-11-11
	 * @param：无
	 * @return：int
	 * @throws：JspException
	 */
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * 功能说明：覆盖父类的doStartTag方法
	 * @Author：XuYanbo
	 * @Date：2014-11-11
	 * @param：int
	 * @return：String
	 * @throws：Exception
	 */
	@Override
	public int doStartTag() throws JspException {
		
		if(BmcConstants.YES.equals(isAdmin)){
			return EVAL_BODY_INCLUDE;
		}
		
		boolean hasRight = false;
		
		String methods = authorities.get(menu);
		if(methods!=null && !"".equals(methods)){
			hasRight = methods.contains(right + BmcConstants.COMMA);
		}
		
		if (hasRight) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
	
}
