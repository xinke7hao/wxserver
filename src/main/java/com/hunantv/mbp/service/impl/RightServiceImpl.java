/************************************************************************************
 * @File name   :      RightServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月4日
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

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hunantv.mbp.command.RightCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.MbpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.RightMapper;
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.dto.RightVo;
import com.hunantv.mbp.entity.admin.Right;
import com.hunantv.mbp.service.RightService;

@Service
public class RightServiceImpl implements RightService{

	@Resource
	private RightMapper rightMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	@Override
	public Right get(int id) {
		return rightMapper.selectById(id);
	}
	
	@Override
	public Right getFullRightById(int rightId) {
		return rightMapper.selectFullRightById(rightId);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_RIGHT, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(Right right) {
		rightMapper.updateById(right);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_RIGHT, type=BmcConstants.LOG_TYPE_UPDATE, desc="创建功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(Right Right) {
		rightMapper.insert(Right);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_RIGHT, type=BmcConstants.LOG_TYPE_DELETE, desc="删除功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		rightMapper.deleteById(id);
	}

	/**
	 * 功能列表分页查询
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param rightCmd
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<RightVo> searchRights(RightCommand rightCmd, PageInfo<RightVo> page) throws BmcException {
		return systemModuleDao.searchRights(rightCmd, page);
	}

	/**
	 * 根据ID集合删除功能
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param ids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_RIGHT, type=BmcConstants.LOG_TYPE_DELETE, desc="删除功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteRightByIDs(String ids) {
		String[] sids = ids.split(",");
		rightMapper.deleteByIds(Arrays.asList(sids));
	}

}
