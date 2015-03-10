/************************************************************************************
 * @File name   :      PaymentChannelCsvView.java
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
import com.hunantv.mbp.dto.PaymentChannel;
import com.hunantv.mbp.dto.PaymentPlatform;

/**
 * 导出支付渠道
 * @author XuYanbo
 */
public class PaymentChannelCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","代码","名称","别名","渠道费率","状态","接口地址","对应key","创建时间","更新时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"channel.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<PaymentPlatform> data = (List<PaymentPlatform>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				PaymentChannel c = (PaymentChannel)o;
				content[i++] = c.getChannel_id()+"";
				content[i++] = c.getChannel_code();
				content[i++] = c.getChannel_name();
				content[i++] = c.getChannel_alias();
				content[i++] = c.getOrder_rate();
				content[i++] = c.getIs_available();
				content[i++] = c.getPay_url();
				content[i++] = c.getKey();
				content[i++] = c.getCreate_time();
				content[i++] = c.getUpdate_time();
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
