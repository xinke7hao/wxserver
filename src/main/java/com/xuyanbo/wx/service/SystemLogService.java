package com.xuyanbo.wx.service;

import com.xuyanbo.wx.command.LogCommand;
import com.xuyanbo.wx.command.LoginCommand;
import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.BmcUpdateException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.LoginLog;
import com.xuyanbo.wx.entity.admin.SystemLog;

public interface SystemLogService extends BaseService<SystemLog> {

	public void deleteLogs(LogCommand log) throws BmcUpdateException;

	public PageInfo<SystemLog> searchSystemLogs(LogCommand log, PageInfo<SystemLog> page) throws BmcException;

	public void saveLoginLog(LoginLog log) throws BmcUpdateException;
	
	public PageInfo<LoginLog> searchLoginLogs(LoginCommand log, PageInfo<LoginLog> page) throws BmcException;
}
