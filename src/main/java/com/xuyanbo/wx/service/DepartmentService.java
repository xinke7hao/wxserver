package com.xuyanbo.wx.service;

import java.util.List;

import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.Department;
import com.xuyanbo.wx.entity.admin.User;

public interface DepartmentService extends BaseService<Department> {

	public PageInfo<Department> searchDepartments(Department depart, PageInfo<Department> page, User loginUser) throws BmcException ;

	/**
	 * 验证部门代码是否可用
	 * @Author：XuYanbo
	 * @Date：2014年12月12日
	 * @param departId
	 * @param departCode
	 * @return
	 */
	public boolean checkDepartmentCodeValid(Integer departId, String departCode);

	/**
	 * 获取所有部门列表
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @return
	 */
	public List<Department> getAllDepartments();

}
