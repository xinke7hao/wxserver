package com.xuyanbo.wx.dao.admin;

import java.util.List;

import com.xuyanbo.wx.entity.admin.Dictionary;
import com.xuyanbo.wx.entity.admin.SystemData;

public interface DictionaryMapper {
	
	/**
	 * 根据Code获取可见字典列表
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param dictCode
	 * @return
	 */
	List<Dictionary> getDictionariesByCode(String dictCode);

	/**
	 * 根据Code提取Value
	 * @Author：XuYanbo
	 * @Date：2014年12月2日
	 * @param dictServicePayment
	 * @return
	 */
	String getValueByCode(String dictCode);
	
	
	/**
	 * 删除数据角色关联关系
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param data
	 */
	void deleteGroupData(SystemData data);
	
	/**
	 * 更新数据状态(开启/关闭)
	 * @Author：XuYanbo
	 * @Date：2014年12月24日
	 * @param data
	 */
	void updateStatus(SystemData data);
	
	void insert(SystemData data);
	void delete(SystemData data);
	void update(SystemData data);

}