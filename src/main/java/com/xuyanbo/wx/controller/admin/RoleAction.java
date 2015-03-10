/************************************************************************************
 * @File name   :      RoleAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月28日
 *
 * @Copyrole Notice: 
 * Copyrole (c) 2014 Hunantv.com. All  Roles Reserved.
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

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.Module;
import com.xuyanbo.wx.entity.admin.Right;
import com.xuyanbo.wx.entity.admin.Role;
import com.xuyanbo.wx.service.ModuleService;
import com.xuyanbo.wx.service.RoleService;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/role")
public class RoleAction {

	@Resource
	private ModuleService moduleService;

	@Resource
	private RoleService roleService;

	/**
	 * 角色列表主页
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
		return "admin/role";
	}

	/**
	 * 角色列表搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param rolecmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("role") Role role, HttpServletRequest request) throws BmcException {
		PageInfo<Role> rolePage = new PageInfo<Role>(request);
		rolePage = roleService.searchRoles(role, rolePage);
		return rolePage.toString();
	}

	/**
	 * 跳转角色功能设置页面
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/config/{id}", method=RequestMethod.GET)
	public String config(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Role role = roleService.get(id);
			List<Right> roleRights = roleService.getRoleRights(id);
			model.addAttribute("role", role);
			model.addAttribute("roleRights", roleRights);
		}
		return "admin/role-config";
	}

	/**
	 * 保存角色功能设置
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doconfig", method=RequestMethod.POST)
	public String saveRoleRights(@RequestParam("id") Integer id, @RequestParam("rightIds") String rids) throws BmcUpdateException {
		try {
			if(id!=null){
				roleService.saveRoleRights(id, rids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return "admin/role";
	}

	/**
	 * 删除角色
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
				roleService.deleteRolesByIDs(ids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

	/**
	 * 弹出编辑窗口(创建角色权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("roleForm", new Role());
		return "admin/role-edit";
	}

	/**
	 * 弹出编辑窗口(编辑角色权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Role role = roleService.get(id);
			model.addAttribute("roleForm", role);
		}
		return "admin/role-edit";
	}

	/**
	 * 保存角色信息
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("roleForm") Role role, Model model) throws BmcUpdateException {
		try {
			if(role!=null && role.getRoleId()==null){
				roleService.insert(role);
			} else {
				roleService.update(role);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}
}
