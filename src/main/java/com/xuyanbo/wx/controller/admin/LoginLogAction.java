/************************************************************************************
 * @File name   :      LoginLogAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年3月4日
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
 * 2015年3月4日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.command.LoginCommand;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.LoginLog;
import com.xuyanbo.wx.service.SystemLogService;

/**
 * 系统用户登录日志管理
 */
@Controller
@RequestMapping("/admin/login")
public class LoginLogAction {

	@Resource
	private SystemLogService systemLogService;

	/**
	 * 加载主页面
	 * @Author：XuYanbo
	 * @Date：2015年3月4日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		return "admin/login-log";
	}

	/**
	 * 执行搜索
	 * @Author：XuYanbo
	 * @Date：2015年3月4日
	 * @param log
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("log") LoginCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<LoginLog> page = new PageInfo<LoginLog>(request);
		page = systemLogService.searchLoginLogs(cmd, page);
		return page.toString();
	}

}
