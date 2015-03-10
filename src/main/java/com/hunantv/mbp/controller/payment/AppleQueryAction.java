/************************************************************************************
 * @File name   :      AppleQueryAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月13日
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
 * 2015年1月13日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.payment;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.command.AppleQueryCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.service.PaymentService;

/**
 * 苹果支付查询
 */
@Controller
@RequestMapping("/payment/apple")
public class AppleQueryAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	/**
	 * 苹果支付查询主页
	 * @Author：XuYanbo
	 * @Date：2015年1月13日
	 * @param request
	 * @param model
	 * @return
	 * @throws BmcException 
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) throws BmcException{
		return "payment/apple-query";
	}

	/**
	 * 苹果支付查询
	 * @Author：XuYanbo
	 * @Date：2015年1月13日
	 * @param cmd
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object payQuery(@ModelAttribute("appleCmd") AppleQueryCommand cmd) throws BmcException {
		JSONObject result = paymentService.searchApplePayDetail(cmd);
		return result;
	}
	
}
