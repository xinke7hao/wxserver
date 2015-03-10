package com.hunantv.mbp.dao.admin;

import java.util.List;

import com.hunantv.mbp.entity.admin.Menu;

public interface MenuMapper {
	
    int deleteById(Integer menuId);

    int insert(Menu record);

    Menu selectById(Integer menuId);

    int updateById(Menu record);

    /**
	 * 加载左侧菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @param userId
	 * @return
	 */
    List<Menu> selectAllMenuBar();
    
	/**
	 * 根据用户加载左侧菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @return
	 */
	List<Menu> selectUserMenuBar(int userId);

	/**
	 * 根据模块获得下级菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param mid
	 * @return
	 */
	List<Menu> getMenusByModuleId(Integer moduleId);

	/**
	 * 获取用户菜单功能列表
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userId
	 * @return
	 */
	List<Menu> selectUserMenuRights(int userId);

	/**
	 * 日志管理--菜单列表--获得所有菜单集合(包含禁用的)
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @return
	 */
	List<Menu> getAllMenus();

	/**
	 * 获取所有菜单功能
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @return
	 */
	List<Menu> selectAllMenuRights();

	/**
	 * 获取指定菜单
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param id
	 * @return
	 */
	Menu getFullMenuById(Integer id);

	/**
	 * 更新菜单说明
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menu
	 */
	void updateDesc(Menu menu);

	/**
	 * 查询菜单说明
	 * @Author：XuYanbo
	 * @Date：2015年2月10日
	 * @param menuId
	 * @return
	 */
	String getMenuDescById(Integer menuId);
}