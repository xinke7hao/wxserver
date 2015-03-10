/************************************************************************************
 * @File name   :      PaymentBlacklistCsvView.java
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
package com.hunantv.mbp.controller.csv;

import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import au.com.bytecode.opencsv.CSVWriter;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.controller.AbstractCsvView;
import com.hunantv.mbp.dto.Blacklist;

/**
 * 导出黑名单配置
 * @author XuYanbo
 */
public class PaymentBlacklistCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","黑名单名称","名单类型","业务平台ID","支付渠道ID","状态","创建时间","更新时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"blacklist.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<Blacklist> data = (List<Blacklist>)model.get(BmcConstants.CSV_DATA);

			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				Blacklist p = (Blacklist)o;
				content[i++] = p.getId();
				content[i++] = p.getItem();
				content[i++] = p.getType();
				content[i++] = p.getPlatform_id();
				content[i++] = p.getChannel_id();
				content[i++] = p.getStatus();
				content[i++] = p.getCreate_time();
				content[i++] = p.getUpdate_time();
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
