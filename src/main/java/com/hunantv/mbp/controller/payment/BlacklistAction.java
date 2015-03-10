/************************************************************************************
 * @File name   :      BlackListAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月30日
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
 * 2014年12月30日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.payment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.controller.csv.PaymentBlacklistCsvView;
import com.hunantv.mbp.dto.Blacklist;
import com.hunantv.mbp.service.DictionaryService;
import com.hunantv.mbp.service.PaymentService;

/**
 * 黑名单管理
 */
@Controller
@RequestMapping("/payment/blacklist")
public class BlacklistAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	@Resource
	private DictionaryService dictionaryService;
	
	/**
	 * 黑名单列表主页
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) throws BmcException{
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		return "payment/blacklist";
	}

	/**
	 * 黑名单查询
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("blacklistCmd") Blacklist blacklist, HttpServletRequest request) throws BmcException {
		PageInfo<Blacklist> blacklistPage = new PageInfo<Blacklist>(request);
		HttpResult<Blacklist> result = paymentService.getAllBlacklists(blacklist, blacklistPage);
		blacklistPage.setData(result);
		return blacklistPage.toString();
	}

	/**
	 * 删除黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = paymentService.deleteBlacklist(id);
		}
		return result;
	}

	/**
	 * 弹出添加窗口(添加权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @param model
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) throws BmcException{
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		model.addAttribute("blacklistForm", new Blacklist());
		return "payment/blacklist-create";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(HttpServletRequest request, @PathVariable("id") String id, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			Blacklist blacklist = paymentService.getBlacklistById(id);
			Map<String, String> dataMap = getUserDataMap(request);
			model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
			model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
			model.addAttribute("blacklistForm", blacklist);
		}
		return "payment/blacklist-edit";
	}

	/**
	 * 保存黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param blacklist
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("blacklistForm") Blacklist blacklist) throws BmcException {
		paymentService.saveBlacklist(blacklist);
		return BmcConstants.YES;
	}
	
	/**
	 * 导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export(@ModelAttribute("blacklistCmd") Blacklist blacklist, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<Blacklist> page = new PageInfo<Blacklist>(BmcConstants.CSV_PAGE_TYPE);
		HttpResult<Blacklist> result = paymentService.getAllBlacklists(blacklist, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new PaymentBlacklistCsvView(), model);
	}
}
