/************************************************************************************
 * @File name   :      HttpResult.java
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
 * XuYanbo				1.0				Initial Version				2014年11月12日 上午10:23:40
 ************************************************************************************/
package com.hunantv.mbp.commons;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.hunantv.mbp.dto.BaseDataObject;

/**
 * HTTP请求后结果对象
 */
@XmlRootElement
public class HttpResult<T> extends BaseDataObject {

	private static final long serialVersionUID = 1L;

	private Integer code;
	private String message;
	private Integer totalCount;
	private T entity;
	private List<T> data;
	
	public HttpResult(){}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
