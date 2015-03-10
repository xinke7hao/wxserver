/************************************************************************************
 * @File name   :      BossContentAction.java
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.hunantv.mbp.command.BossAssetCommand;
import com.hunantv.mbp.command.BossContentCommand;
import com.hunantv.mbp.command.BossPackCommand;
import com.hunantv.mbp.command.BossSingleCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.csv.BossPackAssetCsvView;
import com.hunantv.mbp.controller.csv.BossSingleCsvView;
import com.hunantv.mbp.dto.BossAsset;
import com.hunantv.mbp.dto.BossPackAssetContent;
import com.hunantv.mbp.dto.BossSourceContent;
import com.hunantv.mbp.dto.BossPackContent;
import com.hunantv.mbp.dto.BossSingleContent;
import com.hunantv.mbp.dto.BossSystemTag;
import com.hunantv.mbp.dto.BossVipContent;
import com.hunantv.mbp.service.BossOperateService;
import com.hunantv.mbp.service.BossService;

/**
 * BOSS内容管理
 * 频道源内容、VIP内容、集合内容、单点内容
 * @author XuYanbo
 */
@Controller
@RequestMapping("/boss/content")
public class BossContentAction {

	@Resource
	private BossService bossService;
	
	@Resource
	private BossOperateService bossOperateService;

	/**
	 * BOSS频道源管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/source/list", method=RequestMethod.GET)
	public String listSource(){
		return "boss/boss-content-source";
	}

	/**
	 * BOSS频道源管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/source/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchSource(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossSourceContent> page = new PageInfo<BossSourceContent>(request);
		HttpResult<BossSourceContent> result = bossService.searchChannelSources(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 弹出收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/source/edit", method=RequestMethod.GET)
	public String editSource(@RequestParam("ids") String id, @RequestParam("minvip") String minvip, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("ids", id);
			model.addAttribute("minvip", minvip);
			model.addAttribute("vips", bossService.loadSystemVips());
		}
		return "boss/boss-content-source-edit";
	}

	/**
	 * 保存用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param payment
	 * @return
	 */
	@RequestMapping(value="/source/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveSource(@RequestParam("ids") String id, @RequestParam("status") String status) throws BmcException {
		bossOperateService.setVip(id, status);
		return BmcConstants.YES;
	}
	
	/**
	 * VIP内容管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/vip/list", method=RequestMethod.GET)
	public String listVip(Model model) throws BmcException {
		JSONArray types = bossService.getAllAssetKind();
		model.addAttribute("types", types);
		return "boss/boss-content-vip";
	}

	/**
	 * VIP内容管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/vip/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchVip(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossVipContent> page = new PageInfo<BossVipContent>(request);
		HttpResult<BossVipContent> result = bossService.searchVipContents(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 查看媒资文件列表
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/vip/showasset", method=RequestMethod.GET)
	public String toAssets(@RequestParam("id") String id, Model model) throws BmcException {
		model.addAttribute("id", id);
		return "boss/boss-content-vip-asset";
	}
	
	@RequestMapping(value="/vip/viewasset", method=RequestMethod.POST)
	@ResponseBody
	public Object viewAssets(@ModelAttribute("assetForm") BossAssetCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossAsset> page = new PageInfo<BossAsset>(request);
		HttpResult<BossAsset> result = bossService.searchAssetFiles(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 手工同步
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @param request
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/vip/syncasset", method=RequestMethod.GET)
	@ResponseBody
	public Object syncAsset(@RequestParam("id") String id) throws BmcException {
		bossOperateService.syncAssetFiles(id);
		return BmcConstants.YES;
	}
	
	/**
	 * 弹出VIP收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/pay", method=RequestMethod.GET)
	public String editVipSet(@RequestParam("ids") String id, @RequestParam("minvip") String minvip, @RequestParam("markid") String markid,
			@RequestParam("prev") String prev, Model model) throws BmcException {
		HttpResult<BossSystemTag> tags = bossService.getSystemTags();
		model.addAttribute("tags", tags.getData());
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("ids", id);
			model.addAttribute("minvip", minvip);
			model.addAttribute("markid", markid);
			model.addAttribute("prev", prev);
		}
		return "boss/boss-content-vip-pay";
	}

	/**
	 * 保存VIP收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/vip/setpay", method=RequestMethod.POST)
	@ResponseBody
	public String saveVipSet(@RequestParam("ids") String id, @RequestParam("min_vip_id") String min_vip_id, 
			@RequestParam("mark_id") String mark_id, @RequestParam("preview") String preview) throws BmcException {
		bossOperateService.saveVipPaySettings(id, min_vip_id, mark_id, preview);
		return BmcConstants.YES;
	}
	
	/**
	 * 弹出按集收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/episode", method=RequestMethod.GET)
	public String editVipEpisode(@RequestParam("ids") String id, @RequestParam("start") String start, @RequestParam("markid") String markid, Model model) throws BmcException {
		HttpResult<BossSystemTag> tags = bossService.getSystemTags();
		model.addAttribute("tags", tags.getData());
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("ids", id);
			model.addAttribute("start", start);
			model.addAttribute("markid", markid);
		}
		return "boss/boss-content-vip-episode";
	}

	/**
	 * 保存按集收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/vip/setepisode", method=RequestMethod.POST)
	@ResponseBody
	public String saveVipEpisode(@RequestParam("ids") String id, @RequestParam("start") String start, @RequestParam("mark_id") String mark_id) throws BmcException {
		bossOperateService.saveVipEpisodeSettings(id, start, mark_id);
		return BmcConstants.YES;
	}
	
	/**
	 * 弹出运营方式设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/vip/operation", method=RequestMethod.GET)
	public String editVipOperation(@RequestParam("ids") String id, @RequestParam("ops") String ops, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("ids", id);
			model.addAttribute("ops", ops);
		}
		return "boss/boss-content-vip-operation";
	}

	/**
	 * 保存运营方式设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/vip/setoperation", method=RequestMethod.POST)
	@ResponseBody
	public String saveVipOperation(@RequestParam("ids") String id, @RequestParam("codes") String codes) throws BmcException {
		bossOperateService.saveVipOperateCodeSettings(id, codes);
		return BmcConstants.YES;
	}
	
	/**
	 * 集合内容管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/list", method=RequestMethod.GET)
	public String listPack() throws BmcException {
		return "boss/boss-content-pack";
	}

	/**
	 * 集合内容管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pack/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchPack(HttpServletRequest request) throws BmcException {
		PageInfo<BossPackContent> page = new PageInfo<BossPackContent>(request);
		HttpResult<BossPackContent> result = bossService.searchPackContents(page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 集合内容--导入集合产品列表
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/pack/import", method=RequestMethod.GET)
	public String toImportPack(@RequestParam("pid") String pid) throws BmcException {
		return "boss/boss-content-pack-import";
	}
	
	/**
	 * 集合内容--执行导入
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/pack/doimport", method=RequestMethod.POST)
	@ResponseBody
	public String doImportPack(@RequestParam("pid") String pid, @RequestParam("ids") String ids) throws BmcException {
		bossOperateService.savePackImports(pid, ids);
		return BmcConstants.YES;
	}
	
	/**
	 * 弹出编辑窗口(创建权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/create", method=RequestMethod.GET)
	public String createPack(Model model) throws Exception {
		model.addAttribute("packForm", new BossPackContent());
		return "boss/boss-content-pack-edit";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/edit/{id}", method=RequestMethod.GET)
	public String editPack(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			BossPackContent vip = bossService.getPackContentById(id);
			model.addAttribute("packForm", vip);
		}
		return "boss/boss-content-pack-edit";
	}
	
	/**
	 * 保存集合收费内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pack/save", method=RequestMethod.POST)
	@ResponseBody
	public String savePack(@ModelAttribute("packForm") BossPackContent packForm) throws BmcException {
		bossOperateService.savePackContent(packForm);
		return BmcConstants.YES;
	}
	
	/**
	 * 删除集合收费内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/pack/delete", method=RequestMethod.GET)
	@ResponseBody
	public String deletePack(@RequestParam("ids") String ids) throws BmcException {
		String result = BmcConstants.NO;
		if(StringUtils.isNotBlank(ids)){
			result = bossOperateService.deletePackContents(ids);
		}
		return result;
	}
	
	/**
	 * 集合产品--编辑集合媒资主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/aslist", method=RequestMethod.GET)
	public String listPackAsset(@RequestParam("pid") String pid, Model model) throws BmcException {
		BossPackAssetContent pack = bossService.getPackAssetsContentById(pid);
		model.addAttribute("pid", pid);
		model.addAttribute("assets", pack.getAssets());
		return "boss/boss-content-pack-asset";
	}
	
	/**
	 * 集合产品--编辑集合媒资列表
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/assearch", method=RequestMethod.POST)
	@ResponseBody
	public Object searchPackAsset(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossSingleContent> page = new PageInfo<BossSingleContent>(request);
		HttpResult<BossSingleContent> result = bossService.searchSingleContents(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 集合产品-编辑集合媒资-模式设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/setmode", method=RequestMethod.GET)
	public String setPackMode(@RequestParam("pid") String pid, @RequestParam("ids") String ids, Model model) throws BmcException {
		if(StringUtils.isNotBlank(ids)){
			BossPackCommand cmd = new BossPackCommand();
			cmd.setPid(pid);
			cmd.setAids(ids);
			model.addAttribute("cmd", cmd);
			
			HttpResult<BossSystemTag> tags = bossService.getSystemTags();
			model.addAttribute("tags", tags.getData());
		}
		return "boss/boss-content-pack-mode";
	}
	
	/**
	 * 集合产品-编辑集合媒资-保存模式设置(打包)
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/pack/savemode", method=RequestMethod.POST)
	@ResponseBody
	public String savePackMode(@ModelAttribute("cmd") BossPackCommand cmd) throws BmcException {
		bossOperateService.savePackMode(cmd);
		return BmcConstants.YES;
	}
	
	/**
	 * 导出集合片单
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @return
	 */
	@RequestMapping(value="/pack/asexport", method=RequestMethod.POST)
	public ModelAndView exportPackAsset(@RequestParam("pid") String pid) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		BossPackAssetContent pack = bossService.getPackAssetsContentById(pid);
		model.put(BmcConstants.CSV_DATA, pack.getAssets());
		model.put(BmcConstants.CSV_TOTAL, pack.getAssets().size());
        return new ModelAndView(new BossPackAssetCsvView(), model);
	}

	/**
	 * 单点内容管理主页面
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param model
	 * @return
	 * @throws BmcException  
	 */
	@RequestMapping(value="/single/list", method=RequestMethod.GET)
	public String listSingle(Model model) throws BmcException {
		JSONArray types = bossService.getAllAssetKind();
		model.addAttribute("types", types);
		return "boss/boss-content-single";
	}

	/**
	 * 单点内容管理搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/single/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchSingle(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<BossSingleContent> page = new PageInfo<BossSingleContent>(request);
		HttpResult<BossSingleContent> result = bossService.searchSingleContents(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 弹出单点收费
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/single/pay", method=RequestMethod.GET)
	public String editSinglePay(@RequestParam("ids") String id, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			
			BossSingleCommand cmd = new BossSingleCommand();
			cmd.setIds(id);

			//single
			if(!id.contains(",")){
				BossPackAssetContent c = bossService.getSingleContentById(id);
				if(c!=null){
					if(c.getAssets()!=null && c.getAssets().size()>0){
						BossSingleContent s = c.getAssets().get(0);
						cmd.setAsset_id(s.getId());
						cmd.setPreview(s.getPreview());
						cmd.setOpercodes(s.getOperation_mode());
						cmd.setMarkid(s.getMark_id());
					}
					
					cmd.setProduct_id(c.getId());
					cmd.setDays(c.getDays());
					cmd.setPrice(c.getYuan_price());
					cmd.setSync_sid(c.getSync_sid());
				}
			}
			
			model.addAttribute("cmd", cmd);
			HttpResult<BossSystemTag> tags = bossService.getSystemTags();
			model.addAttribute("tags", tags.getData());
		}
		return "boss/boss-content-single-pay";
	}

	/**
	 * 保存单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/single/setpay", method=RequestMethod.POST)
	@ResponseBody
	public String saveSinglePay(@ModelAttribute("cmd") BossSingleCommand cmd) throws BmcException {
		bossOperateService.saveSinglePay(cmd);
		return BmcConstants.YES;
	}
	
	/**
	 * 取消单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/single/cancel", method=RequestMethod.POST)
	@ResponseBody
	public String saveSingleCancel(@RequestParam("ids") String id) throws BmcException {
		bossOperateService.cancelSinglePay(id);
		return BmcConstants.YES;
	}
	
	/**
	 * 单点收费内容-按集收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/single/episode", method=RequestMethod.GET)
	public String editSingleEpisode(@RequestParam("ids") String id, @RequestParam("start") String start, @RequestParam("markid") String markid, Model model) throws BmcException {
		HttpResult<BossSystemTag> tags = bossService.getSystemTags();
		model.addAttribute("tags", tags.getData());
		if(StringUtils.isNotBlank(id)){
			model.addAttribute("ids", id);
			model.addAttribute("start", start);
			model.addAttribute("markid", markid);
		}
		return "boss/boss-content-single-episode";
	}

	/**
	 * 单点收费内容-保存按集收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/single/setepisode", method=RequestMethod.POST)
	@ResponseBody
	public String saveSingleEpisode(@RequestParam("ids") String id, @RequestParam("start") String start, @RequestParam("mark_id") String mark_id) throws BmcException {
		bossOperateService.saveVipEpisodeSettings(id, start, mark_id);
		return BmcConstants.YES;
	}
	
	/**
	 * 导出单片
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @return
	 */
	@RequestMapping(value="/single/export", method=RequestMethod.POST)
	public ModelAndView export(@ModelAttribute("cmd") BossContentCommand cmd, HttpServletRequest request, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<BossSingleContent> page = new PageInfo<BossSingleContent>(BmcConstants.CSV_PAGE_TYPE);
		HttpResult<BossSingleContent> result = bossService.searchSingleContents(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new BossSingleCsvView(), model);
	}
}
