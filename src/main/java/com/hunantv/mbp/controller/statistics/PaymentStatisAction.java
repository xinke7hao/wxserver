/************************************************************************************
 * @File name   :      PaymentStatisAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月17日
 *
 * @Copyright Notice: 
 * Copyright (c) 2014 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2014年11月17日 下午2:48:14
 ************************************************************************************/
package com.hunantv.mbp.controller.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.controller.csv.PaymentReconCsvView;
import com.hunantv.mbp.controller.csv.StatisChannelCsvView;
import com.hunantv.mbp.controller.csv.StatisPayorderCsvView;
import com.hunantv.mbp.controller.csv.StatisPlatformCsvView;
import com.hunantv.mbp.dto.PaymentStatis;
import com.hunantv.mbp.entity.admin.SystemData;
import com.hunantv.mbp.service.DictionaryService;
import com.hunantv.mbp.service.PaymentService;

@Controller
@RequestMapping("/statis/payment")
public class PaymentStatisAction extends BaseAction {

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private PaymentService paymentService;

	/**
	 * 支付流水统计
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @return
	 */
	@RequestMapping(value="/order/list", method=RequestMethod.GET)
	public String listOrder(){
		return "statis/payment-order";
	}

	/**
	 * 支付对账
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @return
	 */
	@RequestMapping(value="/recon/list", method=RequestMethod.GET)
	public String listRecon(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		return "statis/payment-recon";
	}
	
	/**
	 * 支付流水统计(业务平台)
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/platform/list", method=RequestMethod.GET)
	public String listPlatform(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		return "statis/payment-platform";
	}

	/**
	 * 支付流水统计(支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/channel/list", method=RequestMethod.GET)
	public String listChannel(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		return "statis/payment-channel";
	}

	/**
	 * 支付流水按日统计查询
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/order/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchOrder(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>(request);
		HttpResult<PaymentStatis> result = paymentService.getPaymentOrderStatis(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 支付流水统计导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/order/export", method=RequestMethod.POST)
	public ModelAndView exportOrder(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentStatis> result = paymentService.getPaymentOrderStatis(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new StatisPayorderCsvView(), model);
	}
	
	/**
	 * 支付对账查询
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/recon/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchRecon(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		
		//数据权限控制
		if(StringUtils.isBlank(cmd.getPlatformId())){
			cmd.setPlatformId(dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM));
		}
		if(StringUtils.isBlank(cmd.getChannelId())){
			cmd.setChannelId(dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL));
		}
		
		HttpResult<PaymentStatis> result = paymentService.getPaymentReconStatis(cmd);
		return result.getData();
	}
	
	/**
	 * 支付对账导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/recon/export", method=RequestMethod.POST)
	public ModelAndView exportRecon(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentStatis> result = paymentService.getPaymentReconStatis(cmd);
		List<PaymentStatis> data = result.getData();
		model.put(BmcConstants.CSV_DATA, data);
		model.put(BmcConstants.CSV_TOTAL, data!=null ? data.size() : 0);
		
        return new ModelAndView(new PaymentReconCsvView(), model);
	}

	/**
	 * 财务报表
	 * @Author：XuYanbo
	 * @Date：2015年2月4日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/finanrecon/list", method=RequestMethod.GET)
	public String listFinancialRecon(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		List<SystemData> platforms = dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM));
		model.addAttribute("platforms", platforms);
		model.addAttribute("platformSize", platforms.size());
		return "statis/payment-financial-recon";
	}
	
	/**
	 * 财务报表查询(按账号)
	 * @Author：XuYanbo
	 * @Date：2015年2月4日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/finanrecon/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchFinancialRecon(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		JSONObject result = paymentService.getPaymentFinancialReconStatis(cmd);
		return result;
	}
	
	/**
	 * 财务报表查询(按支付渠道)
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/finanrecon/channel", method=RequestMethod.POST)
	@ResponseBody
	public Object searchFinancialReconChannel(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		JSONObject result = paymentService.getPaymentFinancialChannelStatis(cmd);
		return result;
	}

	/**
	 * 支付流水按日统计查询(业务平台)
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/platform/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchPlatform(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>(request);
		HttpResult<PaymentStatis> result = paymentService.getPaymentPlatformStatis(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 支付业务平台统计导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/platform/export", method=RequestMethod.POST)
	public ModelAndView exportPlatform(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentStatis> result = paymentService.getPaymentPlatformStatis(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new StatisPlatformCsvView(), model);
	}

	/**
	 * 支付流水按日统计查询(支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param cmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/channel/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchChannel(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>(request);
		HttpResult<PaymentStatis> result = paymentService.getPaymentChannelStatis(cmd, page);
		page.setData(result);
		return page.toString();
	}

	/**
	 * 支付渠道统计导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/channel/export", method=RequestMethod.POST)
	public ModelAndView exportChannel(@ModelAttribute("command") PaymentStatisCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentStatis> result = paymentService.getPaymentChannelStatis(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new StatisChannelCsvView(), model);
	}
}
