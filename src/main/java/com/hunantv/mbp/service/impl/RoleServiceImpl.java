/************************************************************************************
 * @File name   :      RoleServiceImpl.java
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
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.MbpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dao.admin.RightMapper;
import com.hunantv.mbp.dao.admin.RoleMapper;
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.entity.admin.Right;
import com.hunantv.mbp.entity.admin.Role;
import com.hunantv.mbp.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private RightMapper rightMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	@Override
	public Role get(int id) {
		return roleMapper.selectById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@MbpLog(module=ModuleConstants.SYSTEM_ROLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="创建角色")
	public void insert(Role role) {
		roleMapper.insert(role);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_ROLE, type=BmcConstants.LOG_TYPE_DELETE, desc="删除角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		roleMapper.deleteById(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@MbpLog(module=ModuleConstants.SYSTEM_ROLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新角色")
	public void update(Role role) {
		roleMapper.updateById(role);
	}

	/**
	 * 分页查询角色列表
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param role
	 * @param page
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<Role> searchRoles(Role role, PageInfo<Role> page) throws BmcException {
		return systemModuleDao.searchRoles(role, page);
	}
	
	/**
	 * 获取指定角色的功能集合
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param roleId
	 * @return
	 */
	@Override
	public List<Right> getRoleRights(Integer roleId) {
		
		List<Right> allRights = rightMapper.selectAllByStatus(BmcConstants.YES);
		List<Integer> roleRightIds = rightMapper.selectRightIdsByRole(roleId);
		
		if(allRights!=null){
			
			allRights.forEach(r -> {
				roleRightIds.forEach(rid -> {
					if(r.getRightId().intValue() == rid.intValue()){
						r.setHasRight(true);
					}
				});
			});
			
		}
		
		return allRights;
	}

	/**
	 * 重新设置角色-功能关联
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param roleId
	 * @param rids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_ROLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="设置角色功能")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveRoleRights(Integer roleId, String rids) {
		roleMapper.deleteRoleRights(roleId);
		if(rids!=null && !"".equals(rids)){
			roleMapper.insertRoleRights(roleId, rids);
		}
	}

	/**
	 * 根据用户获取角色列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @return
	 */
	@Override
	public List<Role> getUserRoles(Integer userId) {
		return roleMapper.getUserRoles(userId);
	}

	/**
	 * 根据IDs删除角色
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param ids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_ROLE, type=BmcConstants.LOG_TYPE_DELETE, desc="删除角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteRolesByIDs(String ids) {
		String[] sids = ids.split(",");
		roleMapper.deleteByIds(Arrays.asList(sids));
	}
}
