package com.xuyanbo.wx.service;

import com.xuyanbo.wx.command.RightCommand;
import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.dto.RightVo;
import com.xuyanbo.wx.entity.admin.Right;

public interface RightService extends BaseService<Right> {

	public Right getFullRightById(int id);
	
	public void deleteRightByIDs(String ids);

	public PageInfo<RightVo> searchRights(RightCommand rightCmd, PageInfo<RightVo> page) throws BmcException;
	
}
