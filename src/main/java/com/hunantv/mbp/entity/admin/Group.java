package com.hunantv.mbp.entity.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

public class Group implements Serializable {
	
	private static final long serialVersionUID = 279822061622264984L;

	private Integer groupId;

    private String groupName;
    
    private String groupStatus;

    private Date createTime;
    
    private Date updateTime;
    
    //Transient
    private Integer userId;

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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	@Transient
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}