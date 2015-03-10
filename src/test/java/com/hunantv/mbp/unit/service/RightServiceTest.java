/************************************************************************************
 * @File name   :      RightServiceTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月4日
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 下午1:28:11
 ************************************************************************************/
package com.hunantv.mbp.unit.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hunantv.mbp.command.RightCommand;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.RightVo;
import com.hunantv.mbp.entity.admin.Right;
import com.hunantv.mbp.service.RightService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class RightServiceTest extends BaseJUnitTest {

	@Resource
	private RightService rightService;
	
	@Test
	public void testGetFullRightById() throws Exception {
		PageInfo<RightVo> rightPage = new PageInfo<RightVo>();
		rightPage = rightService.searchRights(new RightCommand(), rightPage);
		List<RightVo> rights = rightPage.getList();
		int rightId = rights!=null && rights.size()>0 ? rights.get(0).getRightId() : -1;
		Right r = rightService.getFullRightById(rightId);
		assertTrue(r!=null);
	}

	@Test
	@Rollback(true)
	public void testDeleteRightByIDs() throws Exception {
		PageInfo<RightVo> rightPage = new PageInfo<RightVo>();
		rightPage = rightService.searchRights(new RightCommand(), rightPage);
		List<RightVo> rights = rightPage.getList();
		StringBuffer rightIds = new StringBuffer();
		Integer tmpId = -1;
		for(int i=0;i<rights.size() && i<3;i++){
			if(i==0){
				tmpId = rights.get(i).getRightId();
			} else {
				rightIds.append(",");
			}
			rightIds.append(rights.get(i).getRightId());
		}
		rightService.deleteRightByIDs(rightIds.toString());
		Right r = rightService.get(tmpId);
		assertTrue(r==null);
	}

	@Test
	public void testSearchRights() throws Exception {
		RightCommand rightCmd = new RightCommand();
		rightCmd.setModuleId(7);
		PageInfo<RightVo> rightPage = new PageInfo<RightVo>();
		rightPage = rightService.searchRights(rightCmd, rightPage);
		List<RightVo> rights = rightPage.getList();
		assertTrue(rights!=null && rights.size()>0);
	}

}
