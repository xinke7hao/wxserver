package com.xuyanbo.wx.entity.admin;

import java.io.Serializable;
import java.util.Date;

public class Right implements Serializable {
	
	private static final long serialVersionUID = 4880244832236820307L;

	private Integer rightId;

    private Integer moduleId;
    
    private String moduleName;

    private Integer menuId;
    
    private String menuName;

	private String rightName;

	private String rightMethod;
    
    private String rightStatus;
    
    private Date createTime;
    
    private Date updateTime;
    
    private boolean hasRight = false;

    
    public boolean getHasRight() {
		return hasRight;
	}

	public void setHasRight(boolean hasRight) {
		this.hasRight = hasRight;
	}

    
    public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

    
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

    public String getRightStatus() {
		return rightStatus;
	}

	public void setRightStatus(String rightStatus) {
		this.rightStatus = rightStatus;
	}

	public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName == null ? null : rightName.trim();
    }

    public String getRightMethod() {
        return rightMethod;
    }

    public void setRightMethod(String rightMethod) {
        this.rightMethod = rightMethod == null ? null : rightMethod.trim();
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