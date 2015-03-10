/************************************************************************************
 * @File name   :      AbstractCsvView.java
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
 * XuYanbo				1.0				Initial Version				2015年1月23日 上午10:47:56
 ************************************************************************************/
package com.xuyanbo.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.xuyanbo.wx.commons.BmcConstants;
import com.xuyanbo.wx.commons.BmcException;

/**
 * CSV导出模型
 * @author XuYanbo
 *
 */
public abstract class AbstractCsvView extends AbstractView {

	private static final String CONTENT_TYPE = "text/csv";
	
	public AbstractCsvView(){
		setContentType(CONTENT_TYPE);
	}
	
	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}
	
	/**
	 * @Author：XuYanbo
	 * @Date：2015年1月23日
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		int total = (Integer)model.get(BmcConstants.CSV_TOTAL);
		if(total>BmcConstants.CSV_EXPORT_MAX){
			throw new BmcException("导出记录数超过限制条数("+BmcConstants.CSV_EXPORT_MAX+")");
		}
		
		response.setContentType(getContentType());
		buildCsvDocument(model, request, response);
	}
	
	/**
	 * Subclasses must implement this method to create an CSV document,
	 * given the model.
	 * @param model the model Map
	 * @param workbook the Excel workbook to complete
	 * @param request in case we need locale etc. Shouldn't look at attributes.
	 * @param response in case we need to set cookies. Shouldn't write to it.
	 */
	protected abstract void buildCsvDocument(Map<String, Object> model, HttpServletRequest request, 
			HttpServletResponse response) throws Exception;

}
