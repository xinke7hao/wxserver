/************************************************************************************
 * @File name   :      ModuleServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月28日
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
package com.hunantv.mbp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hunantv.mbp.dao.admin.ModuleMapper;
import com.hunantv.mbp.entity.admin.Module;
import com.hunantv.mbp.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService{

	@Resource
	private ModuleMapper moduleMapper;

	@Override
	public Module get(int id) {
		return moduleMapper.selectById(id);
	}

	@Override
	public List<Module> getModules(String status) {
		return moduleMapper.selectModules(status);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(Module Module) {
		moduleMapper.insert(Module);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		moduleMapper.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(Module module) {
		moduleMapper.updateById(module);
	}

}
