/************************************************************************************
 * @File name   :      PaymentApiAction.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年11月21日
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
 * 2014年11月21日 下午5:24:45			XuYanbo				1.0				Initial Version
 ************************************************************************************/
package com.hunantv.mbp.controller.monitor;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hunantv.mbp.command.MonitorCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.csv.MonitorPayapiCsvView;
import com.hunantv.mbp.controller.csv.MonitorPayorderCsvView;
import com.hunantv.mbp.dto.PaymentApi;
import com.hunantv.mbp.dto.PaymentMonitor;
import com.hunantv.mbp.service.PaymentService;

/**
 * 支付系统监控
 * 1. 支付系统API调用监控
 * 2. 支付流水监控
 * @author XuYanbo
 */
@Controller
@RequestMapping("/monitor")
public class PaymentMonitorAction {

	@Resource
	private PaymentService paymentService;

	/**
	 * 加载支付系统API调用监控主页面
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/payment/list", method=RequestMethod.GET)
	public String listApi(){
		return "monitor/payment-api";
	}

	/**
	 * 支付系统API调用监控搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/payment/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchApi(@ModelAttribute("cmd") MonitorCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentApi> page = new PageInfo<PaymentApi>(request);
		HttpResult<PaymentApi> result = paymentService.searchPaymentApiLogs(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 支付系统API调用导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/payment/export", method=RequestMethod.POST)
	public ModelAndView exportApi(@ModelAttribute("command") MonitorCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentApi> page = new PageInfo<PaymentApi>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentApi> result = paymentService.searchPaymentApiLogs(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new MonitorPayapiCsvView(), model);
	}
	
	/**
	 * 支付流水监控主页面
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/payorder/list", method=RequestMethod.GET)
	public String listOrder(){
		return "monitor/payment-order";
	}

	/**
	 * 支付流水监控搜索
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/payorder/search", method=RequestMethod.POST)
	@ResponseBody
	public Object searchOrder(@ModelAttribute("cmd") MonitorCommand cmd, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentMonitor> page = new PageInfo<PaymentMonitor>(request);
		HttpResult<PaymentMonitor> result = paymentService.searchPaymentOrderLogs(cmd, page);
		page.setData(result);
		return page.toString();
	}
	
	/**
	 * 支付流水监控搜导出CSV
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/payorder/export", method=RequestMethod.POST)
	public ModelAndView exportOrder(@ModelAttribute("command") MonitorCommand cmd, HttpServletResponse response) throws BmcException {
		Map<String, Object> model = new HashMap<String, Object>();
		PageInfo<PaymentMonitor> page = new PageInfo<PaymentMonitor>();
		
		page.setStartRow(BmcConstants.CSV_START_ROW);
		page.setPageSize(BmcConstants.CSV_EXPORT_MAX);
		
		HttpResult<PaymentMonitor> result = paymentService.searchPaymentOrderLogs(cmd, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new MonitorPayorderCsvView(), model);
	}

}
