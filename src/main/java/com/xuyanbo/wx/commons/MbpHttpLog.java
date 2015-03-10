/************************************************************************************
 * @File name   :      MbpHttpLog.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月17日
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
 * XuYanbo				1.0				Initial Version				2014年11月17日 上午10:23:40
 ************************************************************************************/
package com.xuyanbo.wx.commons;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP请求日志记录注解 
 * @author XuYanbo
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MbpHttpLog {

	int module();		//模块ID
	String type();		//日志操作类别(CREATE/UPDATE/DELETE)
	String desc();		//操作描述
}
