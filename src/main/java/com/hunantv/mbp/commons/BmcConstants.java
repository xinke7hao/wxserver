/************************************************************************************
 * @File name   :      BossConstants.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月4日
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
 * XuYanbo				1.0				Initial Version				2014年11月4日 下午2:11:56
 ************************************************************************************/
package com.hunantv.mbp.commons;

import java.util.ResourceBundle;

/**
 * 系统常量类
 */
public interface BmcConstants {
	
	//User Type
	int USER_TYPE_COMMON = 0;
	int USER_TYPE_ADMIN = 1;
	int USER_TYPE_LEADER = 2;
	
	//System Log Type
	String LOG_TYPE_UPDATE = "U";
	String LOG_TYPE_DELETE = "D";
	String LOG_TYPE_QUERY = "Q";
	
	//Log Style
	String LOG_STYLE_LOCAL = "L";
	String LOG_STYLE_HTTP = "H";
	String LOG_STYLE_REST = "R";
	
	//Login Information
	String LOGIN_USER = "loginUser";
	String USER_MENU_RIGHT = "userAuthorities";
	String USER_GROUP_DATA = "userGroupDatas";
	
	//Page Order
	String ASC = "asc";
	String DESC = "desc";
	
	//Status
	String YES = "Y";
	String NO = "N";
	
	//JQuery validation Remote Result
	String TRUE = "true";
	String FALSE = "false";
	
	//Common Character
	String COMMA = ",";
	String QUOTE = "\"";
	String DOT = ".";
	String COLON = ":";
	String SEMICOLON = ";";
	
	//Page
	int PAGE_DEFAULT_SIZE = 15;
	
	//DataTable Request Variables
//	String DATATABLE_REQUEST_DRAW = "draw";
//	String DATATABLE_REQUEST_ORDER_COLUMN_INDEX = "order[0][column]";
//	String DATATABLE_REQUEST_ORDER_DIRECTION = "order[0][dir]";
//	String DATATABLE_REQUEST_ORDER_COLUMN_PREFIX = "columns[";
//	String DATATABLE_REQUEST_ORDER_COLUMN_SUFFIX = "][data]";
	String DATATABLE_REQUEST_DRAW = "sEcho";
	String DATATABLE_REQUEST_STARTROW = "iDisplayStart";
	String DATATABLE_REQUEST_PAGESIZE = "iDisplayLength";
	String DATATABLE_REQUEST_ORDER_COLUMN_INDEX = "iSortCol_0";
	String DATATABLE_REQUEST_ORDER_DIRECTION = "sSortDir_0";
	String DATATABLE_REQUEST_ORDER_COLUMN_PREFIX = "mDataProp_";
	
	//DataTable Response Variables
	String DATATABLE_SECHO = "sEcho";
	String DATATABLE_ITOTAL_RECORDS = "iTotalRecords";
	String DATATABLE_ITOTAL_DISPLAYRECORDS = "iTotalDisplayRecords";
	String DATATABLE_AADATA = "aaData";
	
	//Time Format
	String DATE_YMD = "yyyy-MM-dd";
	String DATE_YMD2 = "yyyy/MM/dd";
	String TIME_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	String TIME_YMDHMS2 = "yyyy/MM/dd HH:mm:ss";
	
	//System Related
	String APPLICATION_PROPERTIES = "application";
	String SERVICE_URL_PAYMENT = "service.url.payment";
	String SERVICE_URL_BOSS = "service.url.boss";
	String SERVICE_KEY_PAYMENT = "service.key.payment";
	String HTTP_CLIENT_CONFIG = "http.client.pool.config";
	ResourceBundle applicationService = ResourceBundle.getBundle(APPLICATION_PROPERTIES);
	
	//System Data Type
	String SYSTEM_DATA_TYPE_DEPARTMENT = "D";
	String SYSTEM_DATA_TYPE_PLATFORM = "P";
	String SYSTEM_DATA_TYPE_CHANNEL = "C";
	
	//EChart Constants
	String CHART_PIE_PLATFORM = "platform";
	String CHART_PIE_CHANNEL = "channel";
	
	//Product Type
	int PRODUCT_TYPE_COMMON = 0;
	int PRODUCT_TYPE_VIP = 1;
	
	//Index Constants
	int DESKTOP_LOG_SIZE = 10;
	
	//CSV Export Data
	String CSV_DATA = "csv_data";
	String CSV_TOTAL = "csv_total";
	int CSV_PAGE_TYPE = 0;
	int CSV_START_ROW = 0;
	int CSV_EXPORT_MAX = 1000;
	
}
