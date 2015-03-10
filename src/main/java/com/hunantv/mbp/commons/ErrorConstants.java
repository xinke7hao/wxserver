/************************************************************************************
 * @File name   :      ErrorConstants.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月12日
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 下午5:11:56
 ************************************************************************************/
package com.hunantv.mbp.commons;

/**
 * 系统错误常量类
 */
public interface ErrorConstants {

	int LOGIN_SUCCESS = 0;				//登录成功
	int LOGIN_ERROR_NOUSER = 1;			//用户名不存在
	int LOGIN_ERROR_WRONG_PASSWORD = 2;	//密码错误
	int LOGIN_ERROR_USER_LOCKED = 3;	//账号被锁定,联系管理员
	int LOGIN_ERROR_CAPTCHA_WRONG = 4;	//验证码不正确
	
	String LOGIN_ERROR_NOUSER_DESC = "用户名不存在";
	String LOGIN_ERROR_WRONG_PASSWORD_DESC = "密码错误";
	String LOGIN_ERROR_USER_LOCKED_DESC = "账号被锁定";
}
