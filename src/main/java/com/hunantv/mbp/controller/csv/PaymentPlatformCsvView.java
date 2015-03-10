/************************************************************************************
 * @File name   :      PaymentPlatformCsvView.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月23日
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
 * XuYanbo				1.0				Initial Version				2015年1月23日 下午3:28:35
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
import com.hunantv.mbp.dto.PaymentPlatform;

/**
 * 导出支付业务平台
 * @author XuYanbo
 */
public class PaymentPlatformCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","平台名","平台简介","签名","状态","创建时间","更新时间","附加信息","接口人"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"platform.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentPlatform> data = (List<PaymentPlatform>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentPlatform p = (PaymentPlatform)o;
				content[i++] = p.getPlatform_id()+"";
				content[i++] = p.getPlatform_name();
				content[i++] = p.getRemark();
				content[i++] = p.getSecret();
				content[i++] = p.getStatus();
				content[i++] = p.getCreate_time();
				content[i++] = p.getUpdate_time();
				content[i++] = p.getExt_data();
				content[i++] = p.getContact();
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
