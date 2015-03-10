package com.hunantv.mbp.service;

import java.util.List;
import java.util.Map;

import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.dto.MenuBar;
import com.hunantv.mbp.entity.admin.Menu;

public interface MenuService extends BaseService<Menu>{

	/**
	 * 根据用户获得可见菜单集合
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param userId
	 * @param isAdmin
	 * @return
	 */
	public List<MenuBar> getMenus(int userId, String isAdmin);
	
	/**
	 * 日志管理--菜单列表--获得所有菜单集合(包含禁用的)
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param userId
	 * @return
	 */
	public List<Menu> getAllMenus();

	/**
	 * 根据模块获得下级菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param mid
	 * @return
	 */
	public List<Menu> getModuleMenus(Integer mid);

	/**
	 * 获取用户菜单功能列表
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userId
	 * @param isAdmin
	 * @return
	 */
	public Map<String, String> getUserMenuRightMap(int userId, String isAdmin);

	/**
	 * 查询指定菜单
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param id
	 * @return
	 */
	public Menu getFullMenuById(Integer id);

	/**
	 * 更新菜单描述说明
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menu
	 */
	public void updateDesc(Menu menu);
	
}
