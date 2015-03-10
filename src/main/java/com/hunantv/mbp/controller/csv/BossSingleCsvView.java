/************************************************************************************
 * @File name   :      BossSingleCsvView.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月25日
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
 * XuYanbo				1.0				Initial Version				2015年2月25日 下午3:28:35
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
import com.hunantv.mbp.dto.BossSingleContent;

/**
 * 导出单点收费内容
 * @author XuYanbo
 */
public class BossSingleCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","名称","分类","最低VIP身份","媒资运营模式","VIP","集合","单点","是否按集收费","状态","最后修改时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"single.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<BossSingleContent> data = (List<BossSingleContent>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				BossSingleContent p = (BossSingleContent)o;
				content[i++] = p.getId();
				content[i++] = p.getName();
				content[i++] = p.getKind();
				content[i++] = p.getMin_vip_str();
				content[i++] = p.getOperation_mode_str();
				content[i++] = p.getIs_vip_product();
				content[i++] = p.getIs_collection_product();
				content[i++] = p.getIs_single_product();
				content[i++] = p.getStart_charges();
				content[i++] = p.getStatus_str();
				content[i++] = p.getUpdated_at();
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
