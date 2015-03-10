package com.hunantv.mbp.service;

import com.hunantv.mbp.command.RightCommand;
import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.RightVo;
import com.hunantv.mbp.entity.admin.Right;

public interface RightService extends BaseService<Right> {

	public Right getFullRightById(int id);
	
	public void deleteRightByIDs(String ids);

	public PageInfo<RightVo> searchRights(RightCommand rightCmd, PageInfo<RightVo> page) throws BmcException;
	
}
