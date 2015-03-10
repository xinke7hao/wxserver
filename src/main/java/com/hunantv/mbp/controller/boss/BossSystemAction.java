/************************************************************************************
 * @File name   :      BossSystemAction.java
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



import java.util.List;
import java.util.stream.Collectors;

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
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.BossChannel;
import com.hunantv.mbp.dto.BossSystemBitrate;
import com.hunantv.mbp.dto.BossSystemPlatform;
import com.hunantv.mbp.dto.BossSystemTag;
import com.hunantv.mbp.dto.BossSystemVip;
import com.hunantv.mbp.service.BossOperateService;
import com.hunantv.mbp.service.BossService;

/**
 * BOSS产品管理
 * 角标管理、VIP身份、平台、清晰度
 * @author XuYanbo
 */
@Controller
@RequestMapping("/boss/system")
public class BossSystemAction {

	@Resource
	private BossService bossService;
	
	@Resource
	private BossOperateService bossOperateService;
	
	/**
	 * 角标管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/tag/list", method=RequestMethod.GET)
	public String listTag(Model model) throws BmcException {
		return "boss/boss-system-tag";
	}

	/**
	 * 角标管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/tag/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchTag(HttpServletRequest request) throws BmcException {
		PageInfo<BossSystemTag> page = new PageInfo<BossSystemTag>(request);
		HttpResult<BossSystemTag> result = bossService.searchSystemTags(page);
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
	@RequestMapping(value="/tag/create", method=RequestMethod.GET)
	public String createTag(Model model) throws Exception {
		model.addAttribute("tagForm", new BossSystemTag());
		return "boss/boss-system-tag-edit";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tag/edit/{id}", method=RequestMethod.GET)
	public String editTag(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossSystemTag tag = bossService.getSystemTagById(id);
			model.addAttribute("tagForm", tag);
		}
		return "boss/boss-system-tag-edit";
	}
	
	/**
	 * 保存角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/tag/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveTag(@ModelAttribute("tagForm") BossSystemTag tag) throws BmcException {
		bossOperateService.saveSystemTag(tag);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/tag/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteTag(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deleteTags(ids);
		}
		return result;
	}
	
	/**
	 * VIP身份管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/vip/list", method=RequestMethod.GET)
	public String listVip() throws BmcException {
		return "boss/boss-system-vip";
	}
	
	/**
	 * VIP身份管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/vip/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchVip(HttpServletRequest request) throws BmcException {
		PageInfo<BossSystemVip> page = new PageInfo<BossSystemVip>(request);
		HttpResult<BossSystemVip> result = bossService.searchSystemVips(page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 创建VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/create", method=RequestMethod.GET)
	public String createVip(Model model) throws Exception {
		model.addAttribute("vipForm", new BossSystemVip());
		HttpResult<BossSystemBitrate> bits = bossService.getAllBitrates();
		List<BossSystemBitrate> bitrates = bits.getData();
		if(bitrates!=null && bitrates.size()>0){
			model.addAttribute("bitMap", bitrates.stream().collect(Collectors.toMap(x->x.getId(), x->x.getName())));
		}
		return "boss/boss-system-vip-edit";
	}

	/**
	 * 编辑VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/edit/{id}", method=RequestMethod.GET)
	public String editVip(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossSystemVip vip = bossService.getSystemVipById(id);
			model.addAttribute("vipForm", vip);
			HttpResult<BossSystemBitrate> bits = bossService.getAllBitrates();
			List<BossSystemBitrate> bitrates = bits.getData();
			if(bitrates!=null && bitrates.size()>0){
				model.addAttribute("bitMap", bitrates.stream().collect(Collectors.toMap(x->x.getId(), x->x.getName())));
			}
		}
		return "boss/boss-system-vip-edit";
	}
	
	/**
	 * 保存VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/vip/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveVip(@ModelAttribute("vipForm") BossSystemVip vip) throws BmcException {
		bossOperateService.saveSystemVip(vip);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除角标
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
			result = bossOperateService.deleteVips(ids);
		}
		return result;
	}
	
	/**
	 * 平台管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/platform/list", method=RequestMethod.GET)
	public String listPlatform() throws BmcException {
		return "boss/boss-system-platform";
	}
	
	/**
	 * 平台搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param request
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/platform/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchPlatform(HttpServletRequest request) throws BmcException {
		PageInfo<BossSystemPlatform> page = new PageInfo<BossSystemPlatform>(request);
		HttpResult<BossSystemPlatform> result = bossService.searchSystemPlatforms(page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 创建厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/platform/create", method=RequestMethod.GET)
	public String createPlatform(Model model) throws Exception {
		model.addAttribute("platformForm", new BossSystemPlatform());
		return "boss/boss-system-platform-edit";
	}

	/**
	 * 编辑厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/platform/edit/{id}", method=RequestMethod.GET)
	public String editPlatform(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossSystemPlatform platform = bossService.getSystemPlatformById(id);
			model.addAttribute("platformForm", platform);
		}
		return "boss/boss-system-platform-edit";
	}
	
	/**
	 * 保存厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/platform/save", method=RequestMethod.POST)
	@ResponseBody
	public String savePlatform(@ModelAttribute("platformForm") BossSystemPlatform platform) throws BmcException {
		bossOperateService.saveSystemPlatform(platform);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/platform/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deletePlatform(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deletePlatforms(ids);
		}
		return result;
	}
	
	/**
	 * 清晰率管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/bitrate/list", method=RequestMethod.GET)
	public String listBitrate() throws BmcException {
		return "boss/boss-system-bitrate";
	}

	/**
	 * 清晰度搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param request
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/bitrate/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchBitrate(HttpServletRequest request) throws BmcException {
		PageInfo<BossSystemBitrate> page = new PageInfo<BossSystemBitrate>(request);
		HttpResult<BossSystemBitrate> result = bossService.searchSystemBitrates(page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 创建清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bitrate/create", method=RequestMethod.GET)
	public String createBitrate(Model model) throws Exception {
		model.addAttribute("bitForm", new BossSystemBitrate());
		return "boss/boss-system-bitrate-edit";
	}

	/**
	 * 编辑清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/bitrate/edit/{id}", method=RequestMethod.GET)
	public String editBitrate(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossSystemBitrate bitrate = bossService.getSystemBitrateById(id);
			model.addAttribute("bitForm", bitrate);
		}
		return "boss/boss-system-bitrate-edit";
	}
	
	/**
	 * 保存清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/bitrate/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveBitrate(@ModelAttribute("bitrateForm") BossSystemBitrate bitrate) throws BmcException {
		bossOperateService.saveSystemBitrate(bitrate);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/bitrate/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deleteBitrate(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deleteBitrates(ids);
		}
		return result;
	}
	
	/**
	 * 支付渠道列表主页
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/channel/list", method=RequestMethod.GET)
	public String listChannel(){
		return "boss/boss-system-channel";
	}

	/**
	 * 支付渠道查询
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param usercmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(HttpServletRequest request) throws BmcException {
		PageInfo<BossChannel> channelPage = new PageInfo<BossChannel>(request);
		HttpResult<BossChannel> result = bossService.searchSystemChannels(channelPage);
		channelPage.setData(result);
		return channelPage.toString();
	}

	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/channel/delete", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@RequestParam("ids") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = bossOperateService.deleteSystemChannel(id);
		}
		return result;
	}

	/**
	 * 弹出添加窗口(添加权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/channel/create", method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("channelForm", new BossChannel());
		return "boss/boss-system-channel-edit";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/channel/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			BossChannel result = bossService.getSystemChannelById(id);
			model.addAttribute("channelForm", result);
		}
		return "boss/boss-system-channel-edit";
	}

	/**
	 * 保存支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param payment
	 * @return
	 */
	@RequestMapping(value="/channel/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("channelForm") BossChannel channel) throws BmcException {
		bossOperateService.saveSystemChannel(channel);
		return BmcConstants.YES;
	}
}
