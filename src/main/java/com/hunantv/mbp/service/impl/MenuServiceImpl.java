/************************************************************************************
 * @File name   :      MenuServiceImpl.java
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
package com.hunantv.mbp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.MbpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.dao.admin.MenuMapper;
import com.hunantv.mbp.dto.MenuBar;
import com.hunantv.mbp.entity.admin.Menu;
import com.hunantv.mbp.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
	private MenuMapper menuMapper;
	
	@Override
	public Menu get(int id) {
		return menuMapper.selectById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(Menu Menu) {
		menuMapper.insert(Menu);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		menuMapper.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(Menu menu) {
		menuMapper.updateById(menu);
	}

	/**
	 * 更新菜单描述说明
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menu
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_MENU, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新菜单描述说明")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateDesc(Menu menu) {
		menuMapper.updateDesc(menu);
		
		//更新菜单说明描述
		ApplicationConfigs.menuDescMap.put(menu.getMenuId(), menu.getMenuDesc());
	}

	/**
	 * 获取用户的菜单列表并转换成Map
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @param isAdmin
	 * @return
	 */
	@Override
	public List<MenuBar> getMenus(int userId, String isAdmin) {
		List<MenuBar> barMap = new ArrayList<MenuBar>();
		List<Menu> menus = null;
		
		if(BmcConstants.YES.equals(isAdmin)){
			menus = menuMapper.selectAllMenuBar();
		} else {
			menus = menuMapper.selectUserMenuBar(userId);
		}
		
		if(menus!=null){
			int index = -1;
			String moduleName = "";
			for(Menu m:menus){
				if(!moduleName.equals(m.getModuleName())){
					moduleName = m.getModuleName();
					barMap.add(new MenuBar(moduleName));
					index ++;
				}
				barMap.get(index).getMenus().add(m);
			}
		}
		return barMap;
	}
	
	/**
	 * 日志管理--菜单列表--获得所有菜单集合(包含禁用的)
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @return
	 */
	@Override
	public List<Menu> getAllMenus() {
		return menuMapper.getAllMenus();
	}

	/**
	 * 根据模块获得下级菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param mid
	 * @return
	 */
	public List<Menu> getModuleMenus(Integer mid) {
		return menuMapper.getMenusByModuleId(mid);
	}

	/**
	 * 获取用户菜单功能列表
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userId
	 * @param isAdmin
	 * @return
	 */
	@Override
	public Map<String, String> getUserMenuRightMap(int userId, String isAdmin) {
		Map<String, String> menuRights = new HashMap<String, String>();
		
		List<Menu> menus = null;
		
		if(BmcConstants.YES.equals(isAdmin)){
			menus = menuMapper.selectAllMenuRights();
		} else {
			menus = menuMapper.selectUserMenuRights(userId);
		}
		
		if(menus!=null){
			menus.forEach(m->{
				menuRights.put(m.getMenuUrl(), m.getMenuMethods());
			});
		}
		
		return menuRights;
	}

	/**
	 * 获取指定菜单详情
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param id
	 * @return
	 */
	@Override
	public Menu getFullMenuById(Integer id) {
		return menuMapper.getFullMenuById(id);
	}
}
