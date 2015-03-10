/************************************************************************************
 * @File name   :      SystemLogServiceTest.java
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

import com.hunantv.mbp.command.LogCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.SystemLog;
import com.hunantv.mbp.service.SystemLogService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class SystemLogServiceTest extends BaseJUnitTest {

	@Resource
	private SystemLogService systemLogService;
	
	@Test
	@Rollback(true)
	public void testDeleteLogs() throws Exception {
		LogCommand cmd = new LogCommand();
		cmd.setStartDay("2014-12-16");
		cmd.setEndDay("2014-12-16");
		cmd.setStyle(BmcConstants.LOG_STYLE_HTTP);
		cmd.setType(BmcConstants.LOG_TYPE_QUERY);
		systemLogService.deleteLogs(cmd);
		
		PageInfo<SystemLog> logPage = new PageInfo<SystemLog>();
		logPage = systemLogService.searchSystemLogs(cmd, logPage);
		List<SystemLog> logs = logPage.getList();
		assertTrue(logs==null || logs.size()==0);
	}

	@Test
	public void testSearchSystemLogs() throws Exception {
		LogCommand logcmd = new LogCommand();
		logcmd.setStartDay("2014-12-01");
		logcmd.setEndDay("2014-12-31");
		PageInfo<SystemLog> logPage = new PageInfo<SystemLog>();
		logPage = systemLogService.searchSystemLogs(logcmd, logPage);
		List<SystemLog> logs = logPage.getList();
		assertTrue(logs!=null && logs.size()>0);
	}

}
