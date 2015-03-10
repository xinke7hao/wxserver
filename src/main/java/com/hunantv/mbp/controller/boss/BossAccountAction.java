/************************************************************************************
 * @File name   :      BossAccountAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月26日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年1月26日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.boss;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunantv.mbp.command.BossAccountCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.BossAccount;
import com.hunantv.mbp.service.BossOperateService;
import com.hunantv.mbp.service.BossService;

/**
 * BOSS账户管理
 * @author XuYanbo
 */
@Controller
@RequestMapping("/boss/account")
public class BossAccountAction {

	@Resource
	private BossService bossService;
	
	@Resource
	private BossOperateService bossOperateService;

	/**
	 * BOSS账户管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(){
		return "boss/boss-account";
	}

	/**
	 * BOSS账户管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("cmd") BossAccountCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossAccount> page = new PageInfo<BossAccount>(request);
		HttpResult<BossAccount> result = bossService.searchAccounts(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 删除用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = bossOperateService.deleteAccount(id);
		}
		return result;
	}

	/**
	 * 用户账户取消服务
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/close/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String close(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = bossOperateService.deleteAccountInvoices(id);
		}
		return result;
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			BossAccount result = bossService.getAccountById(id);
			result.setId(id);
			model.addAttribute("accountForm", result);
		}
		return "boss/boss-account-edit";
	}

	/**
	 * 保存用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param payment
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("accountForm") BossAccount account) throws BmcException {
		bossOperateService.saveAccount(account);		
		return BmcConstants.YES;
	}
	
}
