package com.xuyanbo.wx.service;

import java.util.List;

import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.Right;
import com.xuyanbo.wx.entity.admin.Role;

public interface RoleService extends BaseService<Role> {

	public PageInfo<Role> searchRoles(Role role, PageInfo<Role> page) throws BmcException;
	
	public List<Right> getRoleRights(Integer roleId);

	public void saveRoleRights(Integer roleId, String rids);

	public List<Role> getUserRoles(Integer userId);
	
	public void deleteRolesByIDs(String ids);
	
}
