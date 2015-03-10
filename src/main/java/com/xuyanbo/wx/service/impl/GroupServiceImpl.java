/************************************************************************************
 * @File name   :      GroupServiceImpl.java
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
package com.xuyanbo.wx.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.MbpLog;
import com.xuyanbo.wx.commons.ModuleConstants;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.dao.admin.GroupMapper;
import com.xuyanbo.wx.dao.admin.SystemModuleDao;
import com.xuyanbo.wx.entity.admin.Group;
import com.xuyanbo.wx.entity.admin.GroupData;
import com.xuyanbo.wx.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{

	@Resource
	private GroupMapper groupMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	@Override
	public Group get(int id) {
		return groupMapper.selectById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@MbpLog(module=ModuleConstants.SYSTEM_GROUP, type=BmcConstants.LOG_TYPE_UPDATE, desc="创建数据角色")
	public void insert(Group Group) {
		groupMapper.insert(Group);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_GROUP, type=BmcConstants.LOG_TYPE_DELETE, desc="删除数据角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		groupMapper.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@MbpLog(module=ModuleConstants.SYSTEM_GROUP, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新数据角色")
	public void update(Group group) {
		groupMapper.updateById(group);
	}

	/**
	 * 分页查询数据角色列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param group
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<Group> searchGroups(Group group, PageInfo<Group> page) throws BmcException {
		return systemModuleDao.searchGroups(group, page);
	}

	/**
	 * 重新设置数据角色-功能关联
	 * @Author：XuYanbo
	 * @Date：2014年12月17日
	 * @param groupId
	 * @param pids
	 * @param cids
	 * @param dids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_GROUP, type=BmcConstants.LOG_TYPE_UPDATE, desc="设置数据角色功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveGroupDatas(Integer groupId, String pids, String cids, String dids) {
		groupMapper.deleteGroupDatas(groupId);
		List<GroupData> datas = new ArrayList<GroupData>();
		if(StringUtils.isNotBlank(pids)){
			String[] pidArr = pids.split(BmcConstants.COMMA);
			for(String pid:pidArr){
				datas.add(new GroupData(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, pid));
			}
		}
		if(StringUtils.isNotBlank(cids)){
			String[] cidArr = cids.split(BmcConstants.COMMA);
			for(String cid:cidArr){
				datas.add(new GroupData(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, cid));
			}
		}
		if(StringUtils.isNotBlank(dids)){
			String[] didArr = dids.split(BmcConstants.COMMA);
			for(String did:didArr){
				datas.add(new GroupData(BmcConstants.SYSTEM_DATA_TYPE_DEPARTMENT, did));
			}
		}
		systemModuleDao.insertGroupData(groupId, datas);
	}

	/**
	 * 根据用户获取数据角色列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @return
	 */
	@Override
	public List<Group> getUserGroups(Integer userId) {
		return groupMapper.getUserGroups(userId);
	}

	/**
	 * 根据IDs删除数据角色
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param ids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_GROUP, type=BmcConstants.LOG_TYPE_DELETE, desc="删除数据角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteGroupsByIDs(String ids) {
		String[] sids = ids.split(",");
		groupMapper.deleteByIds(Arrays.asList(sids));
	}
	
	/**
	 * 获取指定数据角色的功能集合
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param groupId
	 * @return
	 */
	@Override
	public List<GroupData> getGroupDatas(Integer groupId) {
		return groupMapper.selectAllGroupDatas(groupId);
	}

	/**
	 * 获取数据角色部门信息
	 * @Author：XuYanbo
	 * @Date：2014年12月20日
	 * @param id
	 */
	@Override
	public List<GroupData> getDepartmentGroupDatas(Integer id) {
		return groupMapper.getDepartmentGroupDatas(id);
	}
	
	/**
	 * 根据用户获取可视数据
	 * @Author：XuYanbo
	 * @Date：2014年12月23日
	 * @param userId
	 * @return
	 */
	@Override
	public Map<String, String> getUserGroupDatas(Integer userId, String isAdmin) {
		Map<String, String> dataMap = new HashMap<String, String>();
		List<GroupData> datas = null;
		if(BmcConstants.YES.equals(isAdmin)){
			datas = groupMapper.getAllGroupDatas();
		} else {
			datas = groupMapper.getUserGroupDatas(userId);
		}
		datas.forEach(d->{ dataMap.put(d.getDataType(), d.getDataId()); });
		return dataMap;
	}
}
