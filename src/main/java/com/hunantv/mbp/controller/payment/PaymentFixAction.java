/************************************************************************************
 * @File name   :      PaymentFixAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年10月28日
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
 * 2014年11月13日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.payment;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.service.PaymentService;

/**
 * 支付流水管理
 */
@Controller
@RequestMapping("/payment/fix")
public class PaymentFixAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	/**
	 * 一键补单页面
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list() throws BmcException {
		return "payment/payment-order-fix";
	}

	/**
	 * 执行一键补单操作
	 * @Author：XuYanbo
	 * @Date：2014年11月19日
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/dofix", method=RequestMethod.POST)
	@ResponseBody
	public String fix(@RequestParam("id") String id) throws BmcException {
		paymentService.fixPaymentOrder(id, "order");
		return BmcConstants.YES; 
	}

}
