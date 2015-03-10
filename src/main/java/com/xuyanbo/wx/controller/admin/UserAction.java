/************************************************************************************
 * @File name   :      UserAction.java
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.controller.BaseAction;
import com.xuyanbo.wx.controller.csv.SystemUserCsvView;
import com.xuyanbo.wx.entity.admin.Department;
import com.xuyanbo.wx.entity.admin.Group;
import com.xuyanbo.wx.entity.admin.Role;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.service.DepartmentService;
import com.xuyanbo.wx.service.GroupService;
import com.xuyanbo.wx.service.RoleService;
import com.xuyanbo.wx.service.UserService;
import com.xuyanbo.wx.utils.PassGenerator;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;
	
	@Resource
	private GroupService groupService;

	/**
	 * 用户列表主页
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(){
		return "admin/user";
	}

	/**
	 * 执行用户搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param usercmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("user") User user, HttpServletRequest request) throws BmcException {
		PageInfo<User> userPage = new PageInfo<User>(request);
		userPage = userService.searchUsers(user, userPage, getCurrentUser(request));
		return userPage.toString();
	}

	/**
	 * 删除用户信息
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param ids
	 * @return
	 * @throws BmcUpdateException 
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) throws BmcUpdateException {
		try {
			if(StringUtils.isNotBlank(ids)){
				userService.deleteUserByIDs(ids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

	/**
	 * 查看用户角色设置
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rolelist", method=RequestMethod.GET)
	public String rolelist(@RequestParam("id") Integer uid, Model model) throws Exception {
		if(uid!=null){
			User user = userService.get(uid);
			List<Role> userRoles = roleService.getUserRoles(user.getUserId());
			model.addAttribute("user", user);
			model.addAttribute("userRoles", userRoles);
		}
		return "admin/user-role-list";
	}

	/**
	 * 保存用户角色设置
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/roleconfig", method=RequestMethod.POST)
	@ResponseBody
	public String saveUserRoles(@RequestParam("id") Integer id, @RequestParam("roleIds") String roleIds) throws BmcUpdateException {
		try {
			if(id!=null){
				userService.saveUserRoles(id, roleIds);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}
	
	/**
	 * 查看用户数据角色设置
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/grouplist", method=RequestMethod.GET)
	public String grouplist(@RequestParam("id") Integer uid, Model model) throws BmcException {
		try {
			if(uid!=null){
				User user = userService.get(uid);
				List<Group> userGroups = groupService.getUserGroups(user.getUserId());
				model.addAttribute("user", user);
				model.addAttribute("userGroups", userGroups);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return "admin/user-group-list";
	}

	/**
	 * 保存用户数据角色设置
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/groupconfig", method=RequestMethod.POST)
	@ResponseBody
	public String saveUserGroups(@RequestParam("id") Integer id, @RequestParam("groupIds") String groupIds) throws BmcUpdateException {
		try {
			if(id!=null){
				userService.saveUserGroups(id, groupIds);
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
	public String create(HttpServletRequest request, Model model) throws Exception {
		List<Department> departList = departmentService.getAllDepartments();
		model.addAttribute("userForm", new User());
		model.addAttribute("departList", departList);
		return "admin/user-create";
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
			List<Department> departList = departmentService.getAllDepartments();
			User user = userService.get(id);
			model.addAttribute("userForm", user);
			model.addAttribute("departList", departList);
		}
		return "admin/user-edit";
	}
	
	/**
	 * 验证用户名唯一性
	 * @param uid
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/checkcode", method=RequestMethod.POST)
	@ResponseBody
	public String checkCode(@RequestParam("uid") Integer uid, @RequestParam("code") String code) {
		String result = BmcConstants.FALSE;
		try {
			if(StringUtils.isNotBlank(code)){
				boolean res = userService.checkUsercodeValid(uid, code);
				result = res ? BmcConstants.TRUE : BmcConstants.FALSE;
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return result;
	}
	
	/**
	 * 生成随机密码
	 */
	@RequestMapping(value="/genpass", method=RequestMethod.GET)
	@ResponseBody
	public String generatePassword(){
		return PassGenerator.next();
	}

	/**
	 * 保存用户信息
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("userForm") User user) throws BmcUpdateException {
		try {
			if(user!=null && user.getUserId()==null){
				user.setUserPass(DigestUtils.md5Hex(user.getUserPass()));
				userService.insert(user);
			} else {
				userService.update(user);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}
	
	/**
	 * 系统用户导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月28日
	 * @return
	 */
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<User> page = new PageInfo<User>(BmcConstants.CSV_PAGE_TYPE);
		page = userService.searchUsers(user, page, getCurrentUser(request));
		List<User> result = page.getList();
		model.put(BmcConstants.CSV_DATA, result);
		model.put(BmcConstants.CSV_TOTAL, result.size());
		
        return new ModelAndView(new SystemUserCsvView(), model);
	}
}
