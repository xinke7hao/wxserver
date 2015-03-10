/************************************************************************************
 * @File name   :      BossAccountCsvView.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月26日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年1月26日 下午3:28:35
 ************************************************************************************/
package com.xuyanbo.wx.controller.csv;

import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVWriter;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.controller.AbstractCsvView;
import com.xuyanbo.wx.entity.admin.User;
import com.xuyanbo.wx.utils.DateUtil;

/**
 * 导出系统账户管理
 * @author XuYanbo
 */
public class SystemUserCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","账号","姓名","联系电话","部门","Email","管理员","状态","创建时间","更新时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"user.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<User> data = (List<User>)model.get(BmcConstants.CSV_DATA);
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				User p = (User)o;
				content[i++] = p.getUserId() + "";
				content[i++] = p.getUserCode();
				content[i++] = p.getUserName();
				content[i++] = p.getPhone();
				content[i++] = p.getDepartName();
				content[i++] = p.getEmail();
				content[i++] = p.getIsAdmin();
				content[i++] = p.getUserStatus();
				content[i++] = DateUtil.parseDate(p.getCreateTime(), DateUtil.DATETIME_COMMON_FORMAT);
				content[i++] = DateUtil.parseDate(p.getUpdateTime(), DateUtil.DATETIME_COMMON_FORMAT);
				cw.writeNext(content);
				cw.flush(); 
			}
			cw.close();
		} catch (Exception e ){
			e.printStackTrace();
		} finally {
			if(cw!=null){
				cw.close();
			}
		}
	}

}
