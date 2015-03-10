package com.xuyanbo.wx.service;

import com.xuyanbo.wx.commons.BaseService;
import com.xuyanbo.wx.commons.BmcException;
import com.xuyanbo.wx.commons.PageInfo;
import com.xuyanbo.wx.entity.admin.User;

public interface UserService extends BaseService<User> {

	public void deleteUserByIDs(String ids);

	public PageInfo<User> searchUsers(User user, PageInfo<User> page, User loginUser) throws BmcException;

	public void saveUserRoles(Integer userId, String rids);

	public void saveUserGroups(Integer userId, String gids);

	/**
	 * 根据用户名查找账号
	 * @Author：XuYanbo
	 * @Date：2014年11月11日
	 * @param userCode
	 * @return
	 * @throws BmcException 
	 */
	public User getUserByCode(String userCode);

	/**
	 * 根据用户ID获取密码
	 * @Author：XuYanbo
	 * @Date：2014年12月4日
	 * @param userId
	 * @return
	 */
	public String getPassById(Integer userId);

	/**
	 * 更新用户密码
	 * @Author：XuYanbo
	 * @Date：2014年12月7日
	 * @param user
	 * @return
	 */
	public void updatePassById(User user);

	/**
	 * 验证用户名是否可用
	 * @Author：XuYanbo
	 * @Date：2014年12月5日
	 * @param uid
	 * @param code
	 * @return
	 */
	public boolean checkUsercodeValid(Integer uid, String code);

}
