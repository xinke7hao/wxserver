package com.hunantv.mbp.service;

import java.util.List;

import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.entity.admin.SystemLog;

public interface IndexService {

	/**
	 * 主页加载日志控件
	 * @Author：XuYanbo
	 * @Date：2015年1月4日
	 * @return
	 */
	public List<SystemLog> getDesktopLogs() throws BmcException;

}
