/************************************************************************************
 * @File name   :      DictionaryServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月15日
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
 * 2014年11月15日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.xuyanbo.wx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.dao.admin.DictionaryMapper;
import com.xuyanbo.wx.dao.admin.SystemModuleDao;
import com.xuyanbo.wx.entity.admin.Dictionary;
import com.xuyanbo.wx.entity.admin.SystemData;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.service.DictionaryService;

@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryMapper dictionaryMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao; 
	
	/**
	 * 根据Code获取可见字典列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param dictCode
	 * @return
	 */
	@Override
	@Cacheable("statisFieldCache")
	public List<Dictionary> getDictionariesByCode(String dictCode) throws BmcException {
		return dictionaryMapper.getDictionariesByCode(dictCode);
	}
	
	/**
	 * 获取数据列表
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param dataType
	 * @return
	 * @throws BmcException 
	 */
	public List<SystemData> getSystemDatas(String dataType) throws BmcException{
		return systemModuleDao.getSystemDatas(dataType);
	}

	/**
	 * 获取用户可见数据列表
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param loginUser
	 * @param dataType
	 * @param dataIds
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public List<SystemData> getSystemDatas(User loginUser, String dataType, String dataIds) throws BmcException {
		return systemModuleDao.getSystemDatas(loginUser, dataType, dataIds);
	}

}
