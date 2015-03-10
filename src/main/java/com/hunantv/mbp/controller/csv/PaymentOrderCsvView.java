/************************************************************************************
 * @File name   :      PaymentOrderCsvView.java
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
import com.hunantv.mbp.dto.PaymentOrder;

/**
 * 导出支付流水
 * @author XuYanbo
 */
public class PaymentOrderCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"支付流水ID","业务订单ID","支付渠道","产品ID","产品名称","账号","手机号","金额(元)","状态","创建时间","对账状态","业务方处理结果"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"order.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentOrder> data = (List<PaymentOrder>)model.get(BmcConstants.CSV_DATA);
			
			for(Object r: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentOrder o = (PaymentOrder)r;
				content[i++] = o.getId()+"\t";
				content[i++] = o.getBusiness_order_id();
				content[i++] = o.getChannel_name();
				content[i++] = o.getProduct_id();
				content[i++] = o.getProduct_name();
				content[i++] = o.getAccount();
				content[i++] = o.getMobile();
				content[i++] = o.getAmount();
				content[i++] = o.getStatus();
				content[i++] = o.getCreate_time();
				content[i++] = o.getRecon_status();
				content[i++] = o.getBusiness_result();
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
