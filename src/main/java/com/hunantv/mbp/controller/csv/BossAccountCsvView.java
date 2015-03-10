/************************************************************************************
 * @File name   :      BossAccountCsvView.java
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
import com.hunantv.mbp.dto.BossAccount;

/**
 * 导出BOSS账户管理
 * @author XuYanbo
 */
public class BossAccountCsvView extends AbstractCsvView {

	@Override
	protected void buildCsvDocument(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String[] header = {"ID","通行证","积分","等级","芒果币","业务","VIP身份","状态","VIP身份生效时间","VIP身份失效时间"};
		CSVWriter cw = null;
		
		try {
			response.setHeader("Content-Disposition","attachment; filename=\"account.csv\"");
			cw = new CSVWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
			cw.writeNext(header);
			@SuppressWarnings("unchecked")
			List<BossAccount> data = (List<BossAccount>)model.get(BmcConstants.CSV_DATA);
			
			for(Object o: data){
				int i = 0;
				String[] content = new String[header.length];
				BossAccount p = (BossAccount)o;
				content[i++] = p.getId();
				content[i++] = p.getPassport();
				content[i++] = p.getPoint();
				content[i++] = p.getLevel();
				content[i++] = p.getMgb();
				content[i++] = p.getBusiness_str();
				content[i++] = p.getVip_str();
				content[i++] = p.getVip_begin_time();
				content[i++] = p.getVip_end_time();
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
