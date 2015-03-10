/************************************************************************************
 * @File name   :      PaymentReconCsvView.java
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
import com.hunantv.mbp.dto.PaymentStatis;

/**
 * 导出支付管理基础对账
 * @author XuYanbo
 */
public class PaymentReconCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String[] header = {"业务平台","支付渠道","支付数","总金额(元)","总收入(元)","手续费(元)"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"payrecon.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentStatis> data = (List<PaymentStatis>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentStatis p = (PaymentStatis)o;
				content[i++] = p.getPlatform_name();
				content[i++] = p.getChannel_alias();
				content[i++] = p.getTotal_count() + "";
				content[i++] = p.getTotal_amount() + "";
				content[i++] = p.getTotal_income();
				content[i++] = p.getTotal_poundage();
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
