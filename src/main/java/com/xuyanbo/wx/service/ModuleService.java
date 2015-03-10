package com.xuyanbo.wx.service;

import java.util.List;

import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.entity.admin.Module;

public interface ModuleService extends BaseService<Module> {

	/**
	 * 获取一级菜单列表
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param status
	 * @return
	 */
	public List<Module> getModules(String status);
	
}
