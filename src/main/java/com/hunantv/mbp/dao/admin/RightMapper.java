package com.hunantv.mbp.dao.admin;

import java.util.List;

import com.hunantv.mbp.entity.admin.Right;

public interface RightMapper {
	
    int deleteById(Integer rightId);

    int insert(Right record);

    Right selectById(Integer rightId);
    
    int updateById(Right record);

    /**
     * 包含模块信息和菜单信息
     * @Author：XuYanbo
     * @Date：2014年11月7日
     * @param rightId
     * @return
     */
    Right selectFullRightById(Integer rightId);
    
    /**
     * 查询指定状态的Right
     * @Author：XuYanbo
     * @Date：2014年11月7日
     * @param status
     * @return
     */
	List<Right> selectAllByStatus(String status);
    
	/**
	 * 根据ID集合删除
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param ids
	 */
	void deleteByIds(List<String> ids);

	/**
	 * 查询指定角色拥有的RightId
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param roleId
	 * @return
	 */
	List<Integer> selectRightIdsByRole(Integer roleId);

}