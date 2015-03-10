package com.hunantv.mbp.service;

import java.util.List;

import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.entity.admin.Module;

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
