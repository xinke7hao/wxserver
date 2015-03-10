package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;

public class Dictionary implements Serializable {
	
	private static final long serialVersionUID = -8313338524194298000L;
	
	private Integer dictId;
	private String dictCode;
	private String dictValue;
	private String dictDesc;
	private String dictGroup;
	private String isDisplay;
	
	public Integer getDictId() {
		return dictId;
	}
	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getDictDesc() {
		return dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getDictGroup() {
		return dictGroup;
	}
	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}
	
}