/************************************************************************************
 * @File name   :      PaymentChannelAction.java
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
 * Date								Who					Version				Comments
 * 2014年11月17日 下午5:24:45			XuYanbo				1.0				Initial Version
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
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.controller.csv.PaymentChannelCsvView;
import com.hunantv.mbp.dto.PaymentChannel;
import com.hunantv.mbp.service.PaymentService;

/**
 * 支付渠道管理
 */
@Controller
@RequestMapping("/payment/channel")
public class PaymentChannelAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	/**
	 * 支付渠道列表主页
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(){
		return "payment/payment-channel";
	}

	/**
	 * 支付渠道查询
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param usercmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(HttpServletRequest request) throws BmcException {
		PageInfo<PaymentChannel> channelPage = new PageInfo<PaymentChannel>(request);
		Map<String, String> dataMap = getUserDataMap(request);
		HttpResult<PaymentChannel> result = paymentService.getAllPaymentChannels(channelPage, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL));
		channelPage.setData(result);
		return channelPage.toString();
	}

	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = paymentService.deletePaymentChannel(id);
		}
		return result;
	}

	/**
	 * 开启支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/open/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String open(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = paymentService.changePaymentChannelStatus(id, HttpServiceConstants.PAY_OPERATION_OPEN);
		}
		return result;
	}

	/**
	 * 关闭支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月18日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/close/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String close(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = paymentService.changePaymentChannelStatus(id, HttpServiceConstants.PAY_OPERATION_CLOSE);
		}
		return result;
	}

	/**
	 * 弹出添加窗口(添加权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("channelForm", new PaymentChannel());
		return "payment/payment-channel-create";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) throws BmcException {
		if(StringUtils.isNotBlank(id)){
			PaymentChannel result = paymentService.getPaymentChannelById(id);
			model.addAttribute("channelForm", result);
		}
		return "payment/payment-channel-edit";
	}

	/**
	 * 保存支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param payment
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("channelForm") PaymentChannel channel) throws BmcException {
		paymentService.savePaymentChannel(channel);
		return BmcConstants.YES;
	}
	
	/**
	 * 导出业务平台
	 * @Author：XuYanbo
	 * @Date：2015年1月23日
	 * @return
	 */
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export(HttpServletRequest request, HttpServletResponse response) throws BmcException {
		PageInfo<PaymentChannel> channelPage = new PageInfo<PaymentChannel>(BmcConstants.CSV_PAGE_TYPE);
		Map<String, String> dataMap = getUserDataMap(request);
		HttpResult<PaymentChannel> result = paymentService.getAllPaymentChannels(channelPage, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL));
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new PaymentChannelCsvView(), model);
	}
}
