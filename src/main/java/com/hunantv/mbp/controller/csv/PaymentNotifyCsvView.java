/************************************************************************************
 * @File name   :      PaymentNotifyCsvView.java
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
import com.hunantv.mbp.dto.SmsAsyncNotify;

/**
 * 导出异步通知
 * @author XuYanbo
 */
public class PaymentNotifyCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","连接ID","消息时间","手机号","消息内容","订阅参数","通知类型","创建时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"notify.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<SmsAsyncNotify> data = (List<SmsAsyncNotify>)model.get(BmcConstants.CSV_DATA);

			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				SmsAsyncNotify p = (SmsAsyncNotify)o;
				content[i++] = p.getId()+"";
				content[i++] = p.getConnect_id();
				content[i++] = p.getMessage_time()+"\t";
				content[i++] = p.getMobile();
				content[i++] = p.getMessage();
				content[i++] = p.getOrder_parameter();
				content[i++] = p.getType();
				content[i++] = p.getCreate_time();
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
