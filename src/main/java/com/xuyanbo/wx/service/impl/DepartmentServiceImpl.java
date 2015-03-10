/************************************************************************************
 * @File name   :      DepartmentServiceImpl.java
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
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午11:17:21
 ************************************************************************************/
package com.xuyanbo.wx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.MbpLog;
import com.xuyanbo.wx.commons.ModuleConstants;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.dao.admin.SystemModuleDao;
import com.xuyanbo.wx.dao.admin.DepartmentMapper;
import com.xuyanbo.wx.entity.admin.Department;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Resource
	private DepartmentMapper departmentMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	@Override
	public Department get(int departmentId) {
		return departmentMapper.selectById(departmentId);
	}
	
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_DEPARTMENT, type=BmcConstants.LOG_TYPE_UPDATE, desc="创建部门")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(Department department) {
		departmentMapper.insert(department);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_DEPARTMENT, type=BmcConstants.LOG_TYPE_DELETE, desc="删除部门")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		departmentMapper.deleteById(id);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_DEPARTMENT, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新部门")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(Department department) {
		departmentMapper.updateById(department);
	}
	
	/**
	 * 分页查询部门列表
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param depart
	 * @param page
	 * @param loginUser
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<Department> searchDepartments(Department depart, PageInfo<Department> page, User loginUser) throws BmcException {
		return systemModuleDao.searchDepartments(depart, page, loginUser);
	}

	/**
	 * 验证部门代码是否可用
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param departId
	 * @param departCode
	 * @return
	 */
	public boolean checkDepartmentCodeValid(Integer departId, String departCode) {
		Department department = new Department(departId, departCode);
		Integer count = departmentMapper.checkDepartmentCodeValid(department);
		return count==0;
	}
	
	/**
	 * 获取所有部门列表
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @return
	 */
	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
}
