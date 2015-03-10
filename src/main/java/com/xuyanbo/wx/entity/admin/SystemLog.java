package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

/**
 * 记录系统操作日志
 * @author XuYanbo
 */
public class SystemLog implements Serializable {
	
	private static final long serialVersionUID = 4921884361526800678L;
	private Integer id;
	private Integer module;
	private String moduleName;
	private String logStyle;
	private String logStatus;
	private String logType;
	private Date logTime;
	private String logUser;
	private String logIp;
	private String logDesc;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getModule() {
		return module;
	}
	public void setModule(Integer module) {
		this.module = module;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogUser() {
		return logUser;
	}
	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	public String getLogStyle() {
		return logStyle;
	}
	public void setLogStyle(String logStyle) {
		this.logStyle = logStyle;
	}
	@Transient
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
