/************************************************************************************
 * @File name   :      UserServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月28日
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
import com.hunantv.mbp.dao.admin.SystemModuleDao;
import com.hunantv.mbp.dao.admin.UserMapper;
import com.hunantv.mbp.entity.admin.User;
import com.hunantv.mbp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private SystemModuleDao systemModuleDao;

	@Override
	public User get(int userId) {
		return userMapper.selectById(userId);
	}
	
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_UPDATE, desc="创建用户")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void insert(User user) {
		userMapper.insert(user);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_DELETE, desc="删除用户")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void delete(int id) {
		userMapper.deleteById(id);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_UPDATE, desc="更新用户")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void update(User user) {
		userMapper.updateById(user);
	}
	
	/**
	 * 分页查询用户列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param user
	 * @param page
	 * @param loginUser
	 * @return
	 * @throws BmcException 
	 */
	@Override
	public PageInfo<User> searchUsers(User user, PageInfo<User> page, User loginUser) throws BmcException {
		return systemModuleDao.searchUsers(user, page, loginUser);
	}

	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_DELETE, desc="删除用户")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteUserByIDs(String ids) {
		String[] sids = ids.split(",");
		userMapper.deleteByIds(Arrays.asList(sids));
	}

	/**
	 * 重新设置用户-角色关联
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @param rids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_UPDATE, desc="设置用户功能角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveUserRoles(Integer userId, String rids) {
		userMapper.deleteUserRoles(userId);
		if(rids!=null && !"".equals(rids)){
			userMapper.insertUserRoles(userId, rids);	
		}
	}

	/**
	 * 重新设置用户-数据角色关联
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param userId
	 * @param gids
	 */
	@Override
	@MbpLog(module=ModuleConstants.SYSTEM_USER, type=BmcConstants.LOG_TYPE_UPDATE, desc="设置用户数据角色")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void saveUserGroups(Integer userId, String gids) {
		userMapper.deleteUserGroups(userId);
		if(gids!=null && !"".equals(gids)){
			userMapper.insertUserGroups(userId, gids);	
		}
	}

	/**
	 * 根据用户名查找账号
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userCode
	 * @return
	 */
	@Override
	public User getUserByCode(String userCode) {
		List<User> users = userMapper.selectByCode(userCode);
		return (users!=null && users.size()>0) ? users.get(0) : null;
	}
	
	/**
	 * 根据用户ID获取密码
	 * @Author：XuYanbo
	 * @Date：2014年12月4日
	 * @param userId
	 * @return
	 */
	@Override
	public String getPassById(Integer userId) {
		return userMapper.getPassById(userId);
	}
	
	/**
	 * 更新用户密码
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param user
	 * @return
	 */
	public void updatePassById(User user) {
		userMapper.updatePassById(user);
	}

	/**
	 * 验证用户名是否可用
	 * @Author：XuYanbo
	 * @Date：2014年12月5日
	 * @param uid
	 * @param code
	 * @return
	 */
	public boolean checkUsercodeValid(Integer uid, String code) {
		User user = new User(uid, code);
		Integer count = userMapper.checkUsercodeValid(user);
		return count==0;
	}
}
