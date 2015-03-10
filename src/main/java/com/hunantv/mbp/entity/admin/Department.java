package com.hunantv.mbp.entity.admin;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
	
	private static final long serialVersionUID = 4697702497528774135L;
	
	private Integer departId;
	private Integer parentId;
	private String departCode;
	private String departName;
	private Date createTime;
	private Date updateTime;
	
	public Department(){}
	public Department(Integer departId, String departCode){
		this.departId = departId;
		this.departCode = departCode;
	}
	
	public Integer getDepartId() {
		return departId;
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getDepartCode() {
		return departCode;
	}
	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}