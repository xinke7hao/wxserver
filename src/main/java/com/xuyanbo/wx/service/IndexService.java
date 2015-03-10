package com.xuyanbo.wx.service;

import java.util.List;

import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.entity.admin.SystemLog;

public interface IndexService {

	/**
	 * 主页加载日志控件
	 * @Author：XuYanbo
	 * @Date：2015年1月4日
	 * @return
	 */
	public List<SystemLog> getDesktopLogs() throws BmcException;

}
