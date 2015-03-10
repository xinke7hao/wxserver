/************************************************************************************
 * @File name   :      BossPurchaseAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月27日
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
 * 2015年1月27日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.boss;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunantv.mbp.command.BossPurchaseCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.BossPurchase;
import com.hunantv.mbp.service.BossService;

/**
 * BOSS订购关系管理
 * @author XuYanbo
 */
@Controller
@RequestMapping("/boss/purchase")
public class BossPurchaseAction {

	@Resource
	private BossService bossService;

	/**
	 * BOSS订购关系主页面
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(){
		return "boss/boss-purchase";
	}

	/**
	 * BOSS订购关系搜索
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("cmd") BossPurchaseCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossPurchase> page = new PageInfo<BossPurchase>(request);
		HttpResult<BossPurchase> result = bossService.searchPurchases(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
}
