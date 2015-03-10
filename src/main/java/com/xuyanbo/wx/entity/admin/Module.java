package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;

public class Module implements Serializable, Comparable<Module>{
	
	private static final long serialVersionUID = -1221089387767292875L;

	private Integer moduleId;

    private String moduleCode;

    private String moduleName;

    private Integer moduleIndex;

    private String moduleStatus;
    
    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public Integer getModuleIndex() {
        return moduleIndex;
    }

    public void setModuleIndex(Integer moduleIndex) {
        this.moduleIndex = moduleIndex;
    }

    public String getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(String moduleStatus) {
        this.moduleStatus = moduleStatus == null ? null : moduleStatus.trim();
    }

	/**
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Module o) {
		return this.moduleIndex.compareTo(o.getModuleIndex());
	}

}