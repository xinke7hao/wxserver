/************************************************************************************
 * @File name   :      ChartAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月9日
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
 * XuYanbo				1.0				Initial Version				2015年1月9日 下午2:48:14
 ************************************************************************************/
package com.hunantv.mbp.controller.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.command.BossChartCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.dto.BaseDataObject;
import com.hunantv.mbp.dto.BossSimpleChannel;
import com.hunantv.mbp.dto.LineChartData;
import com.hunantv.mbp.dto.Manufacturner;
import com.hunantv.mbp.dto.PieChartData;
import com.hunantv.mbp.dto.StackChartData;
import com.hunantv.mbp.dto.VipProduct;
import com.hunantv.mbp.service.BossService;
import com.hunantv.mbp.service.ChartService;
import com.hunantv.mbp.service.DictionaryService;
import com.hunantv.mbp.service.PaymentService;

@Controller
@RequestMapping("/chart")
public class ChartAction extends BaseAction {

	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private PaymentService paymentService;
	
	@Resource
	private BossService bossService;
	
	@Resource
	private ChartService chartService;

	/**
	 * 支付综合统计图表
	 * @Author：XuYanbo
	 * @Date：2015年1月9日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/main/list", method=RequestMethod.GET)
	public String mainChart(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		model.addAttribute("dataitems", dictionaryService.getDictionariesByCode(HttpServiceConstants.DICT_PAY_STATIS_ITEM));
		return "chart/payment-chart";
	}
	
	/**
	 * 支付组成统计饼图
	 * @Author：XuYanbo
	 * @Date：2015年3月9日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/paypie/list", method=RequestMethod.GET)
	public String pieChart(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		return "chart/payment-pie-chart";
	}
	
	/**
	 * 实时趋势图
	 * @Author：XuYanbo
	 * @Date：2015年1月9日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/realtime/list", method=RequestMethod.GET)
	public String realtimeChart(HttpServletRequest request, Model model) throws BmcException {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("platforms", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_PLATFORM, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_PLATFORM)));
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		model.addAttribute("dataitems", dictionaryService.getDictionariesByCode(HttpServiceConstants.DICT_PAY_STATIS_ITEM));
		return "chart/payment-realtime-chart";
	}

	@RequestMapping(value="/main/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadMainChart(@ModelAttribute("command") PaymentStatisCommand cmd, Model model) throws BmcException {
		Map<String, LineChartData> dataMap = chartService.getPaymentChartStatisMap(cmd);
		return dataMap;
	}

	@RequestMapping(value="/realtime/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadRealtime(@ModelAttribute("command") PaymentStatisCommand cmd, Model model) throws BmcException {
		Map<String, LineChartData> dataMap = chartService.getPaymentChartStatisMap(cmd);
		return dataMap;
	}

	@RequestMapping(value="/paypie/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPieChart(@ModelAttribute("command") PaymentStatisCommand cmd, Model model) throws BmcException {

		List<BaseDataObject> chartDatas = new ArrayList<BaseDataObject>();
		
		//Pie Charts
		PieChartData platformResult = chartService.getPaymentOrderPieStatis(cmd, BmcConstants.CHART_PIE_PLATFORM);
		PieChartData channelResult = chartService.getPaymentOrderPieStatis(cmd, BmcConstants.CHART_PIE_CHANNEL);
		chartDatas.add(platformResult);
		chartDatas.add(channelResult);

		return chartDatas;
	}
	
	/**
	 * OTT-BOSS-购买次数统计主页
	 * @Author：XuYanbo
	 * @Date：2015年1月9日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/pay/list", method=RequestMethod.GET)
	public String payTimeChart(Model model) throws BmcException {
		List<BossSimpleChannel> channels = bossService.getAllChannels();
		model.addAttribute("channels", channels);
		List<Manufacturner> facts = bossService.getAllManufacturners();
		model.addAttribute("facts", facts);
		return "chart/paytime-chart";
	}

	/**
	 * OTT-BOSS-购买次数统计执行
	 * @Author：XuYanbo
	 * @Date：2015年1月14日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/pay/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPaytimeChart(@ModelAttribute("command") BossChartCommand cmd, Model model) throws BmcException {
		StackChartData data = chartService.getPayStatis(cmd);
		return data;
	}
	
	/**
	 * OTT-BOSS-VIP购买次数统计主页
	 * @Author：XuYanbo
	 * @Date：2015年1月22日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/payvip/list", method=RequestMethod.GET)
	public String payvipChart(Model model) throws BmcException {
		List<Manufacturner> facts = bossService.getAllManufacturners();
		List<VipProduct> vips = bossService.getAllVipProducts();
		List<BossSimpleChannel> channels = bossService.getAllChannels();
		model.addAttribute("channels", channels);
		model.addAttribute("facts", facts);
		model.addAttribute("vips", vips);
		return "chart/payvip-chart";
	}

	/**
	 * OTT-BOSS-VIP购买次数统计执行
	 * @Author：XuYanbo
	 * @Date：2015年1月22日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/payvip/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPayvipChart(@ModelAttribute("command") BossChartCommand cmd, Model model) throws BmcException {
		StackChartData data = chartService.getPayStatis(cmd);
		return data;
	}
	
	/**
	 * OTT-BOSS-重置统计主页
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/recharge/list", method=RequestMethod.GET)
	public String rechargeChart(Model model) throws BmcException {
		List<BossSimpleChannel> channels = bossService.getAllChannels();
		List<Manufacturner> facts = bossService.getAllManufacturners();
		model.addAttribute("channels", channels);
		model.addAttribute("facts", facts);
		return "chart/recharge-chart";
	}

	/**
	 * OTT-BOSS-重置统计执行
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/recharge/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadRechargeChart(@ModelAttribute("command") BossChartCommand cmd, Model model) throws BmcException {
		StackChartData data = chartService.getRechargeStatis(cmd);
		return data;
	}
	
	/**
	 * 支付统计-用户分布统计
	 * @Author：XuYanbo
	 * @Date：2015年1月20日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/payuser/list", method=RequestMethod.GET)
	public String payuserChart(Model model) throws BmcException {
		return "chart/payuser-chart";
	}

	/**
	 * 支付统计-用户分布统计
	 * @Author：XuYanbo
	 * @Date：2015年1月20日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/payuser/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPayuserChart() throws BmcException {
		JSONObject result = paymentService.getPayUserChartStatis();
		return result;
	}
	
	/**
	 * OTT-BOSS-VIP购买人数统计主页
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/paycount/list", method=RequestMethod.GET)
	public String paycountChart(Model model) throws BmcException {
		return "chart/paycount-chart";
	}

	/**
	 * OTT-BOSS-VIP购买人数统计执行
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/paycount/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPaycountChart(@ModelAttribute("command") BossChartCommand cmd, Model model) throws BmcException {
		StackChartData data = chartService.getVipPaycountStatis(cmd);
		return data;
	}
	
	/**
	 * OTT-BOSS-付费用户统计主页
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/paiduser/list", method=RequestMethod.GET)
	public String paiduserChart(Model model) throws BmcException {
		List<Manufacturner> facts = bossService.getAllManufacturners();
		model.addAttribute("facts", facts);
		return "chart/paiduser-chart";
	}

	/**
	 * OTT-BOSS-付费用户统计执行
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @param model
	 * @return
	 * @throws BmcException
	 */
	@RequestMapping(value="/paiduser/search", method=RequestMethod.POST)
	@ResponseBody
	public Object loadPaiduserChart(@ModelAttribute("command") BossChartCommand cmd, Model model) throws BmcException {
		StackChartData data = chartService.getPaidUserStatis(cmd);
		return data;
	}
}
