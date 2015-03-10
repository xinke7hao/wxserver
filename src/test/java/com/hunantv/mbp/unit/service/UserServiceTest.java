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

import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.User;
import com.hunantv.mbp.service.UserService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 *
 */
public class UserServiceTest extends BaseJUnitTest {

	@Resource
	private UserService userService;
	
	@Test
	public void testGetUserByCode() throws Exception {
		String code = "admin";
		User user = userService.getUserByCode(code);
		assertTrue("admin".equals(user.getUserCode()));
	}
	
	@Test
	public void testSearchUsers() throws Exception {
		PageInfo<User> userPage = new PageInfo<User>();
		User u1 = new User();
		User u = new User();
		u.setUserId(1);
		u.setIsAdmin(BmcConstants.NO);
		userPage = userService.searchUsers(u1, userPage, u);
		assertTrue(userPage!=null);
	}
	
	@Test
	@Rollback(true)
	public void testInsert() throws Exception {
		User u = new User();
		int id = new Random().nextInt(500);
		u.setUserCode("unittest"+id);
		u.setUserName("JUnit");
		userService.insert(u);
		System.out.println("insert user id = "+id);
		assertTrue(true);
	}
	
	@Test
	@Rollback(true)
	public void testUpdate() throws Exception {
		User u = new User();
		u.setUserCode("unittest");
		User user = userService.getUserByCode(u.getUserCode());
		user.setUserName("JUnit-Update");
		userService.update(user);
		assertTrue(true);
	}
	
	@Test
	@Rollback(true)
	public void testDelete() throws Exception {
		String usercode = "unittest";
		User user = userService.getUserByCode(usercode);
		userService.delete(user.getUserId());
		assertTrue(true);
	}
}
