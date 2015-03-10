/************************************************************************************
 * @File name   :      BossProductAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月11日
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
 * 2015年2月11日 下午5:24:45			XuYanbo				1.0				Initial Version
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunantv.mbp.command.BossContentCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.BossSingleProduct;
import com.hunantv.mbp.dto.BossVipProduct;
import com.hunantv.mbp.service.BossOperateService;
import com.hunantv.mbp.service.BossService;

/**
 * BOSS产品管理
 * VIP身份产品、媒资单点产品
 * @author XuYanbo
 */
@Controller
@RequestMapping("/boss/product")
public class BossProductAction {

	@Resource
	private BossService bossService;
	
	@Resource
	private BossOperateService bossOperateService;
	
	/**
	 * VIP产品管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/vip/list", method=RequestMethod.GET)
	public String listVip(Model model) throws BmcException {
		return "boss/boss-product-vip";
	}

	/**
	 * VIP产品管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/vip/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchVip(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossVipProduct> page = new PageInfo<BossVipProduct>(request);
		HttpResult<BossVipProduct> result = bossService.searchVipProducts(cmd, page);
		page.setData(result);
		return page.toString();
	}

	/**
	 * 弹出编辑窗口(创建权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/create", method=RequestMethod.GET)
	public String createVip(Model model) throws Exception {
		model.addAttribute("vips", bossService.loadSystemVips());
		model.addAttribute("vipForm", new BossVipProduct());
		return "boss/boss-product-vip-edit";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/edit/{id}", method=RequestMethod.GET)
	public String editVip(@PathVariable("id") String id, Model model) throws BmcException {
		model.addAttribute("vips", bossService.loadSystemVips());
		if(id!=null){
			BossVipProduct vip = bossService.getVipProductById(id);
			model.addAttribute("vipForm", vip);
		}
		return "boss/boss-product-vip-edit";
	}
	
	/**
	 * 保存VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vip/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveVip(@ModelAttribute("vipForm") BossVipProduct vipForm) throws BmcException {
		bossOperateService.saveVipProduct(vipForm);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/vip/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteVip(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deleteVipProducts(ids);
		}
		return result;
	}
	
	/**
	 * 媒资单点产品管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/single/list", method=RequestMethod.GET)
	public String listSingle(){
		return "boss/boss-product-single";
	}

	/**
	 * 媒资单点产品搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/single/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchSingle(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossSingleProduct> page = new PageInfo<BossSingleProduct>(request);
		HttpResult<BossSingleProduct> result = bossService.searchSingleProducts(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/single/edit/{id}", method=RequestMethod.GET)
	public String editSingle(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossSingleProduct single = bossService.getSingleProductById(id);
			model.addAttribute("singleForm", single);
		}
		return "boss/boss-product-single-edit";
	}
	
	/**
	 * 保存单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/single/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveSingle(@ModelAttribute("vipForm") BossVipProduct singleForm) throws BmcException {
		bossOperateService.saveSingleProduct(singleForm);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/single/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteSingle(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deleteSingleProducts(ids);
		}
		return result;
	}
	
}
