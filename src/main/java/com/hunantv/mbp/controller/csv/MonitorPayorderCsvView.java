/************************************************************************************
 * @File name   :      MonitorPayorderCsvView.java
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
import com.hunantv.mbp.dto.PaymentMonitor;

/**
 * 导出支付流水监控
 * @author XuYanbo
 */
public class MonitorPayorderCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","订单总数","未支付数","同步通知数","异步通知数","关闭数","业务方失败数","监控数据日期","监控数据时间","数据建立时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"payorder.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentMonitor> data = (List<PaymentMonitor>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentMonitor p = (PaymentMonitor)o;
				content[i++] = p.getId();
				content[i++] = p.getTotal_count()+"";
				content[i++] = p.getPay_count()+"";
				content[i++] = p.getSync_count()+"";
				content[i++] = p.getAsync_count()+"";
				content[i++] = p.getClose_count()+"";
				content[i++] = p.getBusiness_count()+"";
				content[i++] = p.getMonitor_date();
				content[i++] = p.getMonitor_time();
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
