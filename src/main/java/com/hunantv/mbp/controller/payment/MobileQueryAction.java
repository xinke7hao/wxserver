/************************************************************************************
 * @File name   :      MobileQueryAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月5日
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
 * 2015年1月5日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.payment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.service.PaymentService;

/**
 * 手机号查询
 */
@Controller
@RequestMapping("/payment/mobile")
public class MobileQueryAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	/**
	 * 手机号查询
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) throws BmcException{
		return "payment/mobile-query";
	}

	/**
	 * 支付状态详情查询
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/pay", method=RequestMethod.GET)
	@ResponseBody
	public Object payQuery(@RequestParam("mobile") String mobile) throws BmcException {
		JSONObject result = paymentService.getPaymentDetailByMobile(mobile);
		return result;
	}
	
	/**
	 * 9588异步通知状态查询
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sms", method=RequestMethod.GET)
	@ResponseBody
	public Object smsQuery(@RequestParam("mobile") String mobile) throws BmcException {
		JSONObject result = paymentService.getSmsStatusByMobile(mobile);
		return result;
	}
	
	/**
	 * 支付流水补充发货
	 * @Author：XuYanbo
	 * @Date：2015年1月6日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deliver", method=RequestMethod.POST)
	@ResponseBody
	public String deliver(@RequestParam("orderid") String orderId) throws BmcUpdateException {
		try {
			paymentService.deliverPaymentOrder(orderId);
		} catch (Exception e){
			throw new BmcUpdateException(e);
		}
		return BmcConstants.YES;
	}

}
