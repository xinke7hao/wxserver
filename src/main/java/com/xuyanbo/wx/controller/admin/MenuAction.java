/************************************************************************************
 * @File name   :      MenuAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月10日
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
 * 2015年2月10日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.command.RightCommand;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.dao.admin.SystemModuleDao;
import com.xuyanbo.wx.entity.admin.Menu;
import com.xuyanbo.wx.entity.admin.Module;
import com.xuyanbo.wx.service.MenuService;
import com.xuyanbo.wx.service.ModuleService;

/**
 * 菜单管理
 */
@Controller
@RequestMapping("/menu")
public class MenuAction {

	@Resource
	private ModuleService moduleService;

	@Resource
	private MenuService menuService;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	/**
	 * 加载主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		List<Module> modules = moduleService.getModules(null);
		model.addAttribute("modules", modules);
		return "admin/menu";
	}

	/**
	 * 执行搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menucmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("menu") RightCommand menu, HttpServletRequest request) throws BmcException {
		PageInfo<Menu> menuPage = new PageInfo<Menu>(request);
		menuPage = systemModuleDao.searchMenus(menu, menuPage);
		return menuPage.toString();
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Menu menu = menuService.getFullMenuById(id);
			model.addAttribute("menuForm", menu);
		}
		return "admin/menu-edit";
	}

	/**
	 * 保存菜单设置
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("menuForm") Menu menu, Model model) throws BmcUpdateException {
		try {
			if(menu!=null && menu.getMenuId()!=null){
				menuService.updateDesc(menu);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

}
