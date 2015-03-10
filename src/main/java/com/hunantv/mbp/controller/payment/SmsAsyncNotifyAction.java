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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.controller.csv.PaymentNotifyCsvView;
import com.hunantv.mbp.dto.SmsAsyncNotify;
import com.hunantv.mbp.service.PaymentService;

/**
 * 9588异步通知
 */
@Controller
@RequestMapping("/payment/sms")
public class SmsAsyncNotifyAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

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
		return "payment/sms-notify";
	}

	/**
	 * 9588异步通知查询
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("cmd") SmsAsyncNotify sms, HttpServletRequest request) throws BmcException {
		PageInfo<SmsAsyncNotify> smsPage = new PageInfo<SmsAsyncNotify>(request);
		HttpResult<SmsAsyncNotify> result = paymentService.getAllSmsAsyncNotifys(sms, smsPage);
		smsPage.setData(result);
		return smsPage.toString();
	}

	/**
	 * 导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export(@ModelAttribute("cmd") SmsAsyncNotify sms, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<SmsAsyncNotify> page = new PageInfo<SmsAsyncNotify>(BmcConstants.CSV_PAGE_TYPE);
		HttpResult<SmsAsyncNotify> result = paymentService.getAllSmsAsyncNotifys(sms, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new PaymentNotifyCsvView(), model);
	}
}
