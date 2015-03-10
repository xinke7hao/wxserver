/************************************************************************************
 * @File name   :      IndexServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月4日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年1月4日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.dao.admin.IndexDao;
import com.xuyanbo.wx.entity.admin.SystemLog;
import com.xuyanbo.wx.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	@Resource
	private IndexDao indexDao; 
	
	/**
	 * 主页加载日志控件
	 * @throws BmcException 
	 * @Author：XuYanbo
	 * @Date：2015年1月4日
	 * @return
	 */
	@Override
	public List<SystemLog> getDesktopLogs() throws BmcException{
		return indexDao.loadSystemLogs();
	}


}
