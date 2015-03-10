package com.xuyanbo.wx.service;

import java.util.List;
import java.util.Map;

import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.Group;
import com.xuyanbo.wx.entity.admin.GroupData;

public interface GroupService extends BaseService<Group> {

	public PageInfo<Group> searchGroups(Group group, PageInfo<Group> page) throws BmcException;
	
	public List<GroupData> getGroupDatas(Integer groupId);

	public void saveGroupDatas(Integer groupId, String pids, String cids, String dids);

	public List<Group> getUserGroups(Integer userId);

	public void deleteGroupsByIDs(String ids);

	public List<GroupData> getDepartmentGroupDatas(Integer id);
	
	public Map<String, String> getUserGroupDatas(Integer userId, String isAdmin);
}
