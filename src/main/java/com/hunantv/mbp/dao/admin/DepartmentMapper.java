package com.hunantv.mbp.dao.admin;

import java.util.List;

import com.hunantv.mbp.entity.admin.Department;

public interface DepartmentMapper {
	
    int deleteById(Integer id);

    int insert(Department record);

    Department selectById(Integer id);

    int updateById(Department user);

	/**
	 * 验证用户名可用性
	 * @Author：XuYanbo
	 * @Date：2014年12月5日
	 * @param user
	 * @return
	 */
	Integer checkDepartmentCodeValid(Department depart);

	/**
	 * 获取所有部门
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @return
	 */
	List<Department> getAllDepartments();

}