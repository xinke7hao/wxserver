/************************************************************************************
 * @File name   :      DesktopAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月4日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年1月4日	15:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.controller.BaseAction;
import com.xuyanbo.wx.entity.admin.SystemLog;
import com.xuyanbo.wx.service.IndexService;

/**
 * 加载工作台控件
 */
@Controller
@RequestMapping("/desk")
public class DesktopAction extends BaseAction {

	@Resource
	private IndexService indexService;
	
	/**
	 * 日志
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param departmentcmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logs", method=RequestMethod.GET)
	@ResponseBody
	public Object logs() throws BmcException {
		List<SystemLog> logs = indexService.getDesktopLogs();
		return logs;
	}
	
}
