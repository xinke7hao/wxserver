/************************************************************************************
 * @File name   :      LogAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月18日
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
 * 2014年11月18日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.command.LogCommand;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.Menu;
import com.xuyanbo.wx.entity.admin.SystemLog;
import com.xuyanbo.wx.service.MenuService;
import com.xuyanbo.wx.service.SystemLogService;

/**
 * 日志管理
 */
@Controller
@RequestMapping("/admin/log")
public class SystemLogAction {

	@Resource
	private MenuService menuService;
	
	@Resource
	private SystemLogService systemLogService;

	/**
	 * 加载主页面
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		List<Menu> menus = menuService.getAllMenus();
		model.addAttribute("menus", menus);
		return "admin/system-log";
	}

	/**
	 * 执行搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param log
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("log") LogCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<SystemLog> page = new PageInfo<SystemLog>(request);
		page = systemLogService.searchSystemLogs(cmd, page);
		return page.toString();
	}

	/**
	 * 根据查询条件清理日志
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param log
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public String delete(@ModelAttribute("log") LogCommand cmd) throws BmcUpdateException {
		try {
			systemLogService.deleteLogs(cmd);
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

}
