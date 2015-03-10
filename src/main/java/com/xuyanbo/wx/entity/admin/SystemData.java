/************************************************************************************
 * @File name   :      SystemData.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月24日
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
 * XuYanbo				1.0				Initial Version				2014年12月24日 下午2:41:58
 ************************************************************************************/
package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;

/**
 * @author XuYanbo
 *
 */
public class SystemData implements Serializable {

	private static final long serialVersionUID = 911142835411564441L;

	private Integer id;
	private String dataType;
	private Integer dataId;
	private String dataCode;
	private String dataName;
	private String dataStatus;
	
	public SystemData(){}
	public SystemData(String dataType, Integer dataId){
		this.dataType = dataType;
		this.dataId = dataId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	
}
