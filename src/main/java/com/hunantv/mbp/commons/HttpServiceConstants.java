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

/**
 * HTTP接口服务常量类
 */
public interface HttpServiceConstants {

	//返回结果字段
	String RESULT_CODE = "code";
	String RESULT_MESSAGE = "msg";
	String RESULT_TOTALCOUNT = "total_count";
	String RESULT_DATA = "data";
	String RESULT_ERROR_NULL = "Response Result is empty.";
	String RESULT_TOTAL = "total";
	String RESULT_PAGE_ROWS = "rows";
	
	//接口返回码
	int RESULT_CODE_SUCCESS = 0;
	int RESULT_CODE_REQUEST_WRONG = 400;
	int RESULT_CODE_REQUEST_TONULL = 404;
	int RESULT_CODE_SERVER_ERROR = 500;
	
	//操作状态(支付渠道/业务平台)
	int PAY_OPERATION_OPEN = 1;
	int PAY_OPERATION_CLOSE = 0;
	
	String DICT_PAY_STATUS = "PAY_STATUS";
	String DICT_PAY_RECO_STATUS = "PAY_RECO_STATUS";
	String DICT_PAY_BUSINESS_RESULT = "PAY_BUSINESS_RESULT";
	String DICT_PAY_CHANNEL = "PAY_CHANNEL_STATUS";
	String DICT_PAY_STATIS_ITEM = "PAY_STATIS_ITEM";
	
	//支付流水状态
	String PAY_STATUS_UNPAID = "0";
	String PAY_STATUS_PAYING = "1";
	String PAY_STATUS_SYNC_BACK = "2";
	String PAY_STATUS_ASYNC_BACK = "3";
	String PAY_STATUS_CLOSE = "8";
	
	//对账状态
	String PAY_RECO_STATUS_INIT = "0";
	String PAY_RECO_STATUS_HOUR_SUCCESS = "1";
	String PAY_RECO_STATUS_HOUR_FAIL = "2";
	String PAY_RECO_STATUS_DAY_SUCCESS = "3";
	String PAY_RECO_STATUS_DAY_FAIL = "4";
	
	//支付业务处理结果
	String PAY_BUSINESS_RESULT_SUCCESS = "1";
	String PAY_BUSINESS_RESULT_FAIL = "2";
	
	//支付渠道状态
	String PAY_CHANNEL_STATUS_DISABLED = "0";
	String PAY_CHANNEL_STATUS_VALID = "1";

	//业务平台状态
	String PAY_PLATFORM_STATUS_DISABLED = "0";
	String PAY_PLATFORM_STATUS_VALID = "1";
	String PAY_PLATFORM_STATUS_DELETED = "9";
	
	//对接系统
	String DICT_SERVICE_PAYMENT = "PAYMENT";
	
}
