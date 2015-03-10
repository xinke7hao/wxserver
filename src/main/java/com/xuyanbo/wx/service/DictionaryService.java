package com.xuyanbo.wx.service;

import java.util.List;

import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.entity.admin.Dictionary;
import com.xuyanbo.wx.entity.admin.SystemData;
import com.xuyanbo.wx.entity.admin.User;

public interface DictionaryService {

	/**
	 * 根据Code获取可见字典列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param dictCode
	 * @return
	 */
	public List<Dictionary> getDictionariesByCode(String dictCode) throws BmcException;

	/**
	 * 获取数据列表
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param dataType
	 * @return
	 * @throws BmcException 
	 */
	public List<SystemData> getSystemDatas(String dataType) throws BmcException;
	
	/**
	 * 获取用户可见数据列表
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param user
	 * @param dataType
	 * @param dataIds
	 * @return
	 * @throws BmcException 
	 */
	public List<SystemData> getSystemDatas(User user, String dataType, String dataIds) throws BmcException;
}
