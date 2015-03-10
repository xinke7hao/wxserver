package com.xuyanbo.wx.dao.admin;

import java.util.List;

import com.xuyanbo.wx.entity.admin.Module;

public interface ModuleMapper {
	
    int deleteById(Integer moduleId);

    int insert(Module record);

    Module selectById(Integer moduleId);
    
    int updateById(Module record);
    
    /**
     * 根据状态查询模块列表
     * @Author：XuYanbo
     * @Date：2014年11月7日
     * @param moduleStatus
     * @return
     */
    List<Module> selectModules(String moduleStatus);

}