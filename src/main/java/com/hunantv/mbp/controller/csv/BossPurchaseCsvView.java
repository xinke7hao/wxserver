/************************************************************************************
 * @File name   :      BossPurchaseCsvView.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月27日
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
 * XuYanbo				1.0				Initial Version				2015年1月27日 下午3:28:35
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
import com.hunantv.mbp.dto.BossPurchase;

/**
 * 导出BOSS订购关系
 * @author XuYanbo
 */
public class BossPurchaseCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","账号","订单编号","订单支付时间","订单创建时间","支付渠道","产品","产品服务开始时间","产品服务结束时间","数量","类别","发货时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"purchase.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<BossPurchase> data = (List<BossPurchase>)model.get(BmcConstants.CSV_DATA);
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				BossPurchase p = (BossPurchase)o;
				content[i++] = p.getId();
				content[i++] = p.getAccount_passport();
				content[i++] = p.getOrder_uuid();
				content[i++] = p.getOrder_paid_at();
				content[i++] = p.getOrder_created_at();
				content[i++] = p.getChannel_name();
				content[i++] = p.getProduct_name();
				content[i++] = p.getBegin_time();
				content[i++] = p.getEnd_time();
				content[i++] = p.getQuantity();
				content[i++] = p.getCategory_str();
				content[i++] = p.getDelivered_at();
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
