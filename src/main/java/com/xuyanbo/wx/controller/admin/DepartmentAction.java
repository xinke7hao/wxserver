/************************************************************************************
 * @File name   :      DepartmentAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月12日
 *
 * @Copydepartment Notice: 
 * CopyRight (c) 2014 Hunantv.com. All  Departments Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014年12月12日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.controller.BaseAction;
import com.xuyanbo.wx.entity.admin.Department;
import com.xuyanbo.wx.service.ModuleService;
import com.xuyanbo.wx.service.DepartmentService;

/**
 * 部门管理
 */
@Controller
@RequestMapping("/depart")
public class DepartmentAction extends BaseAction {

	@Resource
	private ModuleService moduleService;

	@Resource
	private DepartmentService departmentService;

	/**
	 * 部门列表主页
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model){
		return "admin/depart";
	}

	/**
	 * 部门列表搜索
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param departmentcmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("department") Department department, HttpServletRequest request) throws BmcException {
		PageInfo<Department> departmentPage = new PageInfo<Department>(request);
		departmentPage = departmentService.searchDepartments(department, departmentPage, getCurrentUser(request));
		return departmentPage.toString();
	}

	/**
	 * 删除部门(只允许单个删除)
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") Integer id) throws BmcUpdateException {
		try {
			if(id!=null){
				departmentService.delete(id);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

	/**
	 * 弹出编辑窗口(创建部门权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model) throws Exception {
		model.addAttribute("departForm", new Department());
		return "admin/depart-create";
	}

	/**
	 * 弹出编辑窗口(编辑部门权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Department department = departmentService.get(id);
			model.addAttribute("departForm", department);
		}
		return "admin/depart-edit";
	}

	/**
	 * 保存部门信息
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param department
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("departForm") Department department, Model model) throws BmcUpdateException {
		try {
			if(department!=null && department.getDepartId()==null){
				departmentService.insert(department);
			} else {
				departmentService.update(department);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}
}
