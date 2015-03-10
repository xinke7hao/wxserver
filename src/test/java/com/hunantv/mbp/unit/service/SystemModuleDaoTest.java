/************************************************************************************
 * @File name   :      UserServiceTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月3日
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
 * XuYanbo				1.0				Initial Version				2014年12月3日 下午1:28:11
 ************************************************************************************/
package com.hunantv.mbp.unit.service;

import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;

import com.hunantv.mbp.command.LogCommand;
import com.hunantv.mbp.command.RightCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.dto.RightVo;
import com.hunantv.mbp.entity.admin.Department;
import com.hunantv.mbp.entity.admin.Group;
import com.hunantv.mbp.entity.admin.Role;
import com.hunantv.mbp.entity.admin.SystemLog;
import com.hunantv.mbp.entity.admin.User;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 *
 */
public class SystemModuleDaoTest extends BaseJUnitTest {

	@Resource
	private SystemModuleDao systemModuleDao;
	
	@Test
	public void testSearchUsers() throws Exception {
		PageInfo<User> userPage = new PageInfo<User>();
		User u = new User();
		u.setUserId(1);
		u.setIsAdmin(BmcConstants.NO);
		userPage = systemModuleDao.searchUsers(new User(), userPage, u);
		assertTrue(userPage!=null);
	}
	
	@Test
	public void testSearchDepartments() throws Exception {
		PageInfo<Department> resultPage = new PageInfo<Department>();
		User u = new User();
		u.setUserId(1);
		u.setIsAdmin(BmcConstants.NO);
		resultPage = systemModuleDao.searchDepartments(new Department(), resultPage, u);
		assertTrue(resultPage!=null);
	}
	
	@Test
	public void testSearchGroups() throws Exception {
		PageInfo<Group> resultPage = new PageInfo<Group>();
		resultPage = systemModuleDao.searchGroups(new Group(), resultPage);
		assertTrue(resultPage!=null);
	}
	
	@Test
	public void testSearchRights() throws Exception {
		PageInfo<RightVo> resultPage = new PageInfo<RightVo>();
		resultPage = systemModuleDao.searchRights(new RightCommand(), resultPage);
		assertTrue(resultPage!=null);
	}
	
	@Test
	public void testSearchRoles() throws Exception {
		PageInfo<Role> resultPage = new PageInfo<Role>();
		resultPage = systemModuleDao.searchRoles(new Role(), resultPage);
		assertTrue(resultPage!=null);
	}
	
	@Test
	public void testSearchSystemLogs() throws Exception {
		PageInfo<SystemLog> resultPage = new PageInfo<SystemLog>();
		resultPage = systemModuleDao.searchSystemLogs(new LogCommand(), resultPage);
		assertTrue(resultPage!=null);
	}
	
}
