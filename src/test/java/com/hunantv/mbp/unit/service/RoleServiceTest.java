/************************************************************************************
 * @File name   :      RoleServiceTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月4日
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 下午1:28:11
 ************************************************************************************/
package com.hunantv.mbp.unit.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.entity.admin.Right;
import com.hunantv.mbp.entity.admin.Role;
import com.hunantv.mbp.service.RoleService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class RoleServiceTest extends BaseJUnitTest {

	@Resource
	private RoleService roleService;
	
	@Resource
	private SystemModuleDao systemModuleDao;
	
	@Test
	public void testSearchRoles() throws Exception {
		Role role = new Role();
		role.setRoleStatus(BmcConstants.YES);
		PageInfo<Role> rolePage = new PageInfo<Role>();
		rolePage = roleService.searchRoles(role, rolePage);
		List<Role> roles = rolePage.getList();
		assertTrue(roles!=null && roles.size()>0);
	}

	@Test
	public void testGetRoleRights() throws Exception {
		Integer roleId = 1;
		List<Right> rights = roleService.getRoleRights(roleId);
		assertTrue(rights!=null && rights.size()>0);
	}

	@Test
	@Rollback(true)
	public void testSaveRoleRights() throws Exception {
		int roleId = systemModuleDao.getNextKey("tm_role");
		Role role = new Role();
		role.setRoleName("unittest");
		role.setRoleStatus(BmcConstants.YES);
		roleService.insert(role);
		List<Right> rights = roleService.getRoleRights(1);
		StringBuffer rids = new StringBuffer();
		for(Right r: rights){
			if(rids.length()!=0) rids.append(",");
			rids.append(r.getRightId());
		}
		roleService.saveRoleRights(roleId, rids.toString());
		assertTrue(true);
	}

	@Test
	public void testGetUserRoles() throws Exception {
		Integer userId = 1;
		List<Role> userRoles = roleService.getUserRoles(userId);
		assertTrue(userRoles!=null && userRoles.size()>0);
	}

	@Test
	@Rollback(true)
	public void testDeleteRolesByIDs() throws Exception {
		String ids = "5,7,8";
		roleService.deleteRolesByIDs(ids);
		Role role = new Role();
		role.setRoleStatus(BmcConstants.YES);
		PageInfo<Role> rolePage = new PageInfo<Role>();
		rolePage = roleService.searchRoles(role, rolePage);
		List<Role> roles = rolePage.getList();
		String[] idarr = ids.split(",");
		boolean exist = false;
		for(Role r: roles){
			for(String idstr: idarr){
				if(r.getRoleId().intValue()==Integer.parseInt(idstr)){
					exist = true;
				}
			}
		}
		
		assertTrue(!exist);
	}

}
