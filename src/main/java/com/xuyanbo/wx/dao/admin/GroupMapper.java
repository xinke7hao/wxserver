package com.xuyanbo.wx.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuyanbo.wx.entity.admin.Group;
import com.xuyanbo.wx.entity.admin.GroupData;

public interface GroupMapper {
	
    int deleteById(Integer groupId);

    int insert(Group record);

    Group selectById(Integer groupId);

    int updateById(Group record);

	/**
	 * 获取用户数据组列表
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param groupId
	 * @return
	 */
	List<Group> getUserGroups(Integer userId);

	/**
	 * 清空指定Group的Right关联关系
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param groupId
	 */
	void deleteGroupRights(Integer groupId);
	
	/**
	 * 插入Group-Right关联关系
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param groupId
	 * @param ids
	 */
	void insertGroupRights(@Param("groupId") Integer groupId, @Param("rightIds") String ids);

	/**
	 * 根据IDs删除角色
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param asList
	 */
	void deleteByIds(List<String> ids);

	/**
	 * 删除数据角色设置关联
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param groupId
	 */
	void deleteGroupDatas(Integer groupId);

	/**
	 * 获取数据角色部门信息
	 * @Author：XuYanbo
	 * @Date：2014年12月20日
	 * @param id
	 */
	List<GroupData> getDepartmentGroupDatas(Integer id);

	/**
	 * 根据GroupId提取GroupData
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param groupId
	 * @return
	 */
	List<GroupData> selectAllGroupDatas(Integer groupId);
	
	/**
	 * 根据所有可视数据(业务平台/支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年12月23日
	 * @param userId
	 * @return
	 */
	List<GroupData> getAllGroupDatas();
	
	/**
	 * 根据用户获取可视数据(业务平台/支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年12月23日
	 * @param userId
	 * @return
	 */
	List<GroupData> getUserGroupDatas(Integer userId);

}