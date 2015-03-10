/************************************************************************************
 * @File name   :      ApplicationConfigs.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月10日
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
 * XuYanbo				1.0				Initial Version				2014年12月10日 下午5:43:27
 ************************************************************************************/
package com.xuyanbo.wx.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuyanbo.wx.service.MenuService;

/**
 * 系统参数配置类
 * @author XuYanbo
 *
 */
public class ApplicationConfigs {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfigs.class);
	public static String PAYMENT_SERVER_URI;
	public static String BOSS_SERVER_URI;
	public static String HTTP_KEY;
	public static String[] HTTP_CLIENT_CONFIG;
	public static Map<Integer, String> menuDescMap = new HashMap<Integer, String>();
	
	@Resource
	private MenuService menuService;
	
	static {
		
		// 得到资源文件
		logger.debug("**** Initial SystemVariables Start ****");
		ResourceBundle appRes = BmcConstants.applicationService;

		// 读取HTTP请求连接池配置
		String httpClientConfig = appRes.getString(BmcConstants.HTTP_CLIENT_CONFIG);
		if(StringUtils.isNotBlank(httpClientConfig)){
			HTTP_CLIENT_CONFIG = httpClientConfig.split(BmcConstants.COMMA);
		}
		
		logger.debug("**** Initial SystemVariables End ****");
	}
	
}
