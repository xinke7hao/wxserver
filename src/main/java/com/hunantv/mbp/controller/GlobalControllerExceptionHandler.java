/************************************************************************************
 * @File name   :      GlobalControllerExceptionHandler.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月22日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年12月22日 下午4:42:02
 ************************************************************************************/
package com.hunantv.mbp.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller异常统一处理
 * @author XuYanbo
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
	
	/**
	 * 捕获非系统自定义异常
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String, String> handleDefaultException(HttpServletRequest request, Exception e){
		showRequestParams(request);
		logger.error(e.getMessage(), e);
		Map<String, String> errorMap = new HashMap<String, String>();
		errorMap.put("error", e.getMessage());
		return errorMap;
	}
	
	/**
	 * 打印请求参数内容(报错时易于分析)
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param request
	 */
	private void showRequestParams(HttpServletRequest request){
		 Map<String, String> map = new HashMap<String, String>();
         Enumeration<String> paramNames = request.getParameterNames();
         while (paramNames.hasMoreElements()) {
             String paramName = (String) paramNames.nextElement();
             String[] paramValues = request.getParameterValues(paramName);
             if (paramValues.length == 1) {
                 String paramValue = paramValues[0];
                 if (paramValue.length() != 0) {
                     map.put(paramName, paramValue);
                 }
             }
         }

         Set<Entry<String, String>> set = map.entrySet();
         System.out.println("******************************");
         for (Entry<String, String> entry : set) {
             System.out.println(entry.getKey() + ":" + entry.getValue());
         }
         System.out.println("******************************");
	}
	
}
