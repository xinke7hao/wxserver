package com.hunantv.mbp.service;

import java.util.List;

import com.hunantv.mbp.commons.BaseService;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.Department;
import com.hunantv.mbp.entity.admin.User;

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
