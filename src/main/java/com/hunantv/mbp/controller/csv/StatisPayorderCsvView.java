/************************************************************************************
 * @File name   :      StatisPayorderCsvView.java
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
 * 导出支付流水统计
 * @author XuYanbo
 */
public class StatisPayorderCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"统计日期","支付流水总数","已结单的支付流水数","总金额(元)","同步返回数","异步返回数","关闭数","天对账失败数","业务方处理失败数","创建时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"statis-order.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentStatis> data = (List<PaymentStatis>)model.get(BmcConstants.CSV_DATA);
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentStatis p = (PaymentStatis)o;
				content[i++] = p.getDay();
				content[i++] = p.getTotal_count()+"";
				content[i++] = p.getFinish_count()+"";
				content[i++] = p.getTotal_amount()+"";
				content[i++] = p.getSync_count()+"";
				content[i++] = p.getAsync_count()+"";
				content[i++] = p.getClose_count()+"";
				content[i++] = p.getRecon_count()+"";
				content[i++] = p.getBusiness_count()+"";
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
