package com.hunantv.mbp.service;

import com.hunantv.mbp.command.LogCommand;
import com.hunantv.mbp.command.LoginCommand;
import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.LoginLog;
import com.hunantv.mbp.entity.admin.SystemLog;

public interface SystemLogService extends BaseService<SystemLog> {

	public void deleteLogs(LogCommand log) throws BmcUpdateException;

	public PageInfo<SystemLog> searchSystemLogs(LogCommand log, PageInfo<SystemLog> page) throws BmcException;

	public void saveLoginLog(LoginLog log) throws BmcUpdateException;
	
	public PageInfo<LoginLog> searchLoginLogs(LoginCommand log, PageInfo<LoginLog> page) throws BmcException;
}
