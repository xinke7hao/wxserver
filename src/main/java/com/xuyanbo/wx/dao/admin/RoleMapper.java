package com.xuyanbo.wx.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuyanbo.wx.entity.admin.Role;

public interface RoleMapper {
	
    int deleteById(Integer roleId);

    int insert(Role record);

    Role selectById(Integer roleId);

    int updateById(Role record);

	/**
	 * 清空指定Role的Right关联关系
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param roleId
	 */
	void deleteRoleRights(Integer roleId);
	
	/**
	 * 插入Role-Right关联关系
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param roleId
	 * @param ids
	 */
	void insertRoleRights(@Param("roleId") Integer roleId, @Param("rightIds") String ids);

	/**
	 * 根据IDs删除角色
	 * @Author：XuYanbo
	 * @Date：2014年11月10日
	 * @param asList
	 */
	void deleteByIds(List<String> ids);

	/**
	 * 获取用户角色列表
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param userId
	 * @return
	 */
	List<Role> getUserRoles(Integer userId) ;
}