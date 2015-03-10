/************************************************************************************
 * @File name   :      HttpDataId.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月25日
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
 * XuYanbo				1.0				Initial Version				2014年12月25日 上午11:49:57
 ************************************************************************************/
package com.hunantv.mbp.dto;

/**
 * 承接POST返回值
 * data: {id="1234567890"}
 * @author XuYanbo
 *
 */
public class HttpDataId {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
