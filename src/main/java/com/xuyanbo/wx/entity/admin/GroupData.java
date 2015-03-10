package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;

import javax.persistence.Transient;

public class GroupData implements Serializable {

	private static final long serialVersionUID = -6871683449800711129L;
	
	private Integer id;
    private Integer groupId;
    private String dataId;
    private String dataType;
    
    private boolean hasData = false;
    private String dataName;
    
    public GroupData(){}
    public GroupData(String dataType, String dataId){
    	this.dataType = dataType;
    	this.dataId = dataId;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Transient
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	public boolean isHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
    
}