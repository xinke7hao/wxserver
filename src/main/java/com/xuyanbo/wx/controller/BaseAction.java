/************************************************************************************
 * @File name   :      BaseAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月24日
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
 * XuYanbo				1.0				Initial Version				2014年12月24日 上午10:58:44
 ************************************************************************************/
package com.xuyanbo.wx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.entity.admin.User;

/**
 * Controller基类
 * @author XuYanbo
 */
public class BaseAction {

	/**
	 * 获取当前用户
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param request
	 * @return
	 */
	protected User getCurrentUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(BmcConstants.LOGIN_USER);
	}
	
	/**
	 * 获取当前用户数据权限
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getUserDataMap(HttpServletRequest request){
		Map<String, String> dataMap = (Map<String, String>)request.getSession().getAttribute(BmcConstants.USER_GROUP_DATA);
		return dataMap==null ? new HashMap<String, String>() : dataMap;
	}
	
}
