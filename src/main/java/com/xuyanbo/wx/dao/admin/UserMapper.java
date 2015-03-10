package com.xuyanbo.wx.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuyanbo.wx.entity.admin.User;

public interface UserMapper {
	
    int deleteById(Integer id);

    int insert(User record);

    User selectById(Integer id);

    int updateById(User user);

    /**
     * 修改用户密码
     * @Author：XuYanbo
     * @Date：2014年12月5日
     * @param user
     * @return
     */
    void updatePassById(User user);
    
	/**
	 * 根据ID集合删除
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param ids
	 */
	void deleteByIds(List<String> ids);

	/**
	 * 根据用户ID清除关联的角色
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 */
	void deleteUserRoles(Integer userId);

	/**
	 * 插入用户角色关联关系
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param userId
	 * @param roleIds
	 */
	void insertUserRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);
	
	/**
	 * 根据用户ID清除关联的数据角色
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param userId
	 */
	void deleteUserGroups(Integer userId);

	/**
	 * 插入用户数据角色关联关系
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param userId
	 * @param groupIds
	 */
	void insertUserGroups(@Param("userId") Integer userId, @Param("groupIds") String groupIds);

	/**
	 * 根据用户名查找账号
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userCode
	 * @return
	 */
	List<User> selectByCode(String userCode);

	/**
	 * 根据用户ID获取密码
	 * @Author：XuYanbo
	 * @Date：2014年12月4日
	 * @param userId
	 * @return
	 */
	String getPassById(Integer userId);

	/**
	 * 验证用户名可用性
	 * @Author：XuYanbo
	 * @Date：2014年12月5日
	 * @param user
	 * @return
	 */
	Integer checkUsercodeValid(User user);

}