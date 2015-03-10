/************************************************************************************
 * @File name   :      GroupAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月9日
 *
 * @Copygroup Notice: 
 * Copygroup (c) 2014 Hunantv.com. All  Groups Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2014年12月9日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.entity.admin.Group;
import com.hunantv.mbp.entity.admin.GroupData;
import com.hunantv.mbp.service.GroupService;

/**
 * 数据角色管理
 */
@Controller
@RequestMapping("/group")
public class GroupAction {

	@Resource
	private GroupService groupService;

	/**
	 * 数据角色列表主页
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(){
		return "admin/group";
	}

	/**
	 * 数据角色列表搜索
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param groupcmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("group") Group group, HttpServletRequest request) throws BmcException {
		PageInfo<Group> groupPage = new PageInfo<Group>(request);
		groupPage = groupService.searchGroups(group, groupPage);
		return groupPage.toString();
	}

	/**
	 * 跳转数据角色功能设置页面
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/config/{id}", method=RequestMethod.GET)
	public String config(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Group group = groupService.get(id);
			List<GroupData> groupDatas = groupService.getGroupDatas(id);
			
			//load departments
			List<GroupData> departDatas = groupService.getDepartmentGroupDatas(id);
			groupDatas.addAll(departDatas);
			
			model.addAttribute("group", group);
			model.addAttribute("groupDatas", groupDatas);
		}
		return "admin/group-config";
	}

	/**
	 * 保存数据角色功能设置
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/doconfig", method=RequestMethod.POST)
	public String saveGroupDatas(@RequestParam("id") Integer id, @RequestParam("pids") String pids, @RequestParam("cids") String cids, 
			@RequestParam("dids") String dids) throws BmcUpdateException {
		try {
			if(id!=null){
				groupService.saveGroupDatas(id, pids, cids, dids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return "admin/group";
	}

	/**
	 * 删除数据角色
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) throws BmcUpdateException {
		try {
			if(StringUtils.isNotBlank(ids)){
				groupService.deleteGroupsByIDs(ids);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

	/**
	 * 弹出编辑窗口(创建数据角色权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("groupForm", new Group());
		return "admin/group-edit";
	}

	/**
	 * 弹出编辑窗口(编辑数据角色权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) throws Exception {
		if(id!=null){
			Group group = groupService.get(id);
			model.addAttribute("groupForm", group);
		}
		return "admin/group-edit";
	}

	/**
	 * 保存数据角色信息
	 * @Author：XuYanbo
	 * @Date：2014年12月9日
	 * @param group
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("groupForm") Group group, Model model) throws BmcUpdateException {
		try {
			if(group!=null && group.getGroupId()==null){
				groupService.insert(group);
			} else {
				groupService.update(group);
			}
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}
}
