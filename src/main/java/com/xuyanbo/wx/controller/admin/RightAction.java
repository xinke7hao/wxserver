/************************************************************************************
 * @File name   :      RightAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月28日
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
 * 2014年10月28日 下午5:24:45			XuYanbo				1.0				Initial Version
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.command.RightCommand;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.dto.RightVo;
import com.xuyanbo.wx.entity.admin.Menu;
import com.xuyanbo.wx.entity.admin.Module;
import com.xuyanbo.wx.entity.admin.Right;
import com.xuyanbo.wx.service.MenuService;
import com.xuyanbo.wx.service.ModuleService;
import com.xuyanbo.wx.service.RightService;

/**
 * 模块/菜单/功能管理
 */
@Controller
@RequestMapping("/right")
public class RightAction {

	@Resource
	private ModuleService moduleService;

	@Resource
	private MenuService menuService;

	@Resource
	private RightService rightService;

	/**
	 * 加载主页面
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		List<Module> modules = moduleService.getModules(null);
		model.addAttribute("modules", modules);
		return "admin/right";
	}

	/**
	 * 执行搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param rightcmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("right") RightCommand right, HttpServletRequest request) throws BmcException {
		PageInfo<RightVo> rightPage = new PageInfo<RightVo>(request);
		rightPage = rightService.searchRights(right, rightPage);
		return rightPage.toString();
	}

	/**
	 * 删除功能菜单
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) throws BmcUpdateException {
		try {
			if(ids!=null && !"".equals(ids)){
				rightService.deleteRightByIDs(ids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

	/**
	 * 弹出编辑窗口(创建权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model) throws Exception {
		List<Module> modules = moduleService.getModules(null);
		model.addAttribute("modules", modules);
		model.addAttribute("rightForm", new Right());
		return "admin/right-create";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Right right = rightService.getFullRightById(id);
			model.addAttribute("rightForm", right);
		}
		return "admin/right-edit";
	}

	/**
	 * 保存功能设置
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param right
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("rightForm") Right right, Model model) throws BmcUpdateException {
		try {
			if(right!=null && right.getRightId()==null){
				rightService.insert(right);
			} else {
				rightService.update(right);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}


	@RequestMapping(value="/loadchild", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> moduleChilds(@RequestParam("mid") Integer mid) throws Exception {
		List<Menu> menus = menuService.getModuleMenus(mid);
		return menus;
	}

}
