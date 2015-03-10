/************************************************************************************
 * @File name   :      MenuServiceTest.java
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
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.dto.MenuBar;
import com.hunantv.mbp.entity.admin.Menu;
import com.hunantv.mbp.service.MenuService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class MenuServiceTest extends BaseJUnitTest {

	@Resource
	private MenuService menuService;
	
	@Test
	public void testGetMenus() throws Exception {
		Integer userId = 1;
		List<MenuBar> menuMap = menuService.getMenus(userId, BmcConstants.YES);
		assertTrue(menuMap!=null && menuMap.size()>0);
	}

	@Test
	public void testGetAllMenus() throws Exception {
		List<Menu> menus = menuService.getAllMenus();
		assertTrue(menus!=null && menus.size()>0);
	}

	@Test
	public void testGetModuleMenus() throws Exception {
		Integer moduleId = 7;
		List<Menu> menus = menuService.getModuleMenus(moduleId);
		assertTrue(menus!=null && menus.size()>0);
	}

	@Test
	public void testGetUserMenuRightMap() throws Exception {
		Integer userId = 1;
		Map<String, String> umrMap = menuService.getUserMenuRightMap(userId, BmcConstants.YES);
		assertTrue(umrMap!=null && umrMap.size()>0);
	}

}
