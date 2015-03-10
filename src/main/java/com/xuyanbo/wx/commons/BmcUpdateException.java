/************************************************************************************
 * @File name   :      BossUpdateException.java
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
 * XuYanbo				1.0				Initial Version				2014年12月22日 上午10:23:40
 ************************************************************************************/
package com.xuyanbo.wx.commons;

/**
 * 更新操作异常类
 * @author XuYanbo
 */
public class BmcUpdateException extends BmcException {

	private static final long serialVersionUID = 1L;

	public BmcUpdateException(String message){
		super(message);
	}
	
	public BmcUpdateException(Throwable cause) {
		super(cause);
	}

	public BmcUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

}
