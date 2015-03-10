package com.hunantv.mbp.service;

import java.util.List;

import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.Right;
import com.hunantv.mbp.entity.admin.Role;

public interface RoleService extends BaseService<Role> {

	public PageInfo<Role> searchRoles(Role role, PageInfo<Role> page) throws BmcException;
	
	public List<Right> getRoleRights(Integer roleId);

	public void saveRoleRights(Integer roleId, String rids);

	public List<Role> getUserRoles(Integer userId);
	
	public void deleteRolesByIDs(String ids);
	
}
