/************************************************************************************
 * @File name   :      SystemLogServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月17日
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
 * XuYanbo				1.0				Initial Version				2014年11月17日 上午11:17:21
 ************************************************************************************/
package com.hunantv.mbp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hunantv.mbp.command.LogCommand;
import com.hunantv.mbp.command.LoginCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.MbpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.LoginLogMapper;
import com.hunantv.mbp.dao.admin.SystemLogMapper;
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.entity.admin.LoginLog;
import com.hunantv.mbp.entity.admin.SystemLog;
import com.hunantv.mbp.service.SystemLogService;

@Service
public class SystemLogServiceImpl implements SystemLogService{

	@Resource
	private SystemLogMapper systemLogMapper;
	
	@Resource
	private LoginLogMapper loginLogMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(SystemLog log) {
		systemLogMapper.insert(log);
	}

	/**
	 * 分页查询日志列表
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param log
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<SystemLog> searchSystemLogs(LogCommand log, PageInfo<SystemLog> page) throws BmcException {
		return systemModuleDao.searchSystemLogs(log, page);
	}
	
	/**
	 * 分页查询用户登录日志列表
	 * @Author：XuYanbo
	 * @Date：2015年3月4日
	 * @param log
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<LoginLog> searchLoginLogs(LoginCommand log, PageInfo<LoginLog> page) throws BmcException {
		return systemModuleDao.searchLoginLogs(log, page);
	}
	
	/**
	 * 根据条件删除日志
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param log
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_LOG, type=BmcConstants.LOG_TYPE_DELETE, desc="删除日志")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteLogs(LogCommand log) throws BmcUpdateException {
		systemModuleDao.deleteSystemLogs(log);
	}

	@Override
	public SystemLog get(int id) {
		return null;
	}

	@Override
	public void update(SystemLog obj) {}

	@Override
	public void delete(int id) {}

	/**
	 * @Author：XuYanbo
	 * @Date：2015年3月4日
	 * @param log
	 * @throws BmcUpdateException
	 */
	@Override
	public void saveLoginLog(LoginLog log) throws BmcUpdateException {
		loginLogMapper.insert(log);
	}
}
