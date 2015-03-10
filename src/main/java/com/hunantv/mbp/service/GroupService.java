package com.hunantv.mbp.service;

import java.util.List;
import java.util.Map;

import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.Group;
import com.hunantv.mbp.entity.admin.GroupData;

public interface GroupService extends BaseService<Group> {

	public PageInfo<Group> searchGroups(Group group, PageInfo<Group> page) throws BmcException;
	
	public List<GroupData> getGroupDatas(Integer groupId);

	public void saveGroupDatas(Integer groupId, String pids, String cids, String dids);

	public List<Group> getUserGroups(Integer userId);

	public void deleteGroupsByIDs(String ids);

	public List<GroupData> getDepartmentGroupDatas(Integer id);
	
	public Map<String, String> getUserGroupDatas(Integer userId, String isAdmin);
}
