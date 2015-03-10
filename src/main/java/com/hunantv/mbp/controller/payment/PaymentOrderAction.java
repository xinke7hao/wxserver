/************************************************************************************
 * @File name   :      PaymentOrderAction.java
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hunantv.mbp.command.PaymentOrderCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.controller.BaseAction;
import com.hunantv.mbp.controller.csv.PaymentOrderCsvView;
import com.hunantv.mbp.dto.PaymentOrder;
import com.hunantv.mbp.entity.admin.Dictionary;
import com.hunantv.mbp.service.DictionaryService;
import com.hunantv.mbp.service.PaymentService;

/**
 * 支付流水管理
 */
@Controller
@RequestMapping("/payment/order")
public class PaymentOrderAction extends BaseAction {

	@Resource
	private PaymentService paymentService;

	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 支付流水列表主页
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) throws Exception {
		Map<String, String> dataMap = getUserDataMap(request);
		model.addAttribute("channels", dictionaryService.getSystemDatas(getCurrentUser(request), BmcConstants.SYSTEM_DATA_TYPE_CHANNEL, dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL)));
		return "payment/payment-order";
	}

	/**
	 * 支付流水查询
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param usercmd
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public Object search(@ModelAttribute("payment") PaymentOrderCommand payment, HttpServletRequest request) throws BmcException {
		PageInfo<PaymentOrder> paymentOrderPage = new PageInfo<PaymentOrder>(request);

		if(StringUtils.isBlank(payment.getChannelId())){
			Map<String, String> dataMap = getUserDataMap(request);
			payment.setChannelId(dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL));
		}
		
		HttpResult<PaymentOrder> result = paymentService.getAllPaymentOrders(payment, paymentOrderPage);
		paymentOrderPage.setData(result);
		return paymentOrderPage.toString();
	}

	/**
	 * 删除支付流水
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable("id") String id) throws BmcException {
		String result = BmcConstants.NO;
		if(id!=null && !"".equals(id)){
			result = paymentService.deletePaymentOrderById(id);
		}
		return result;
	}

	/**
	 * 弹出详情窗口
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/view/{id}", method=RequestMethod.GET)
	public String detail(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			PaymentOrder result = paymentService.getPaymentOrderById(id);
			model.addAttribute("orderForm", result);
		}
		return "payment/payment-order-detail";
	}

	/**
	 * 弹出编辑窗口(编辑权限)
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) throws BmcException {
		if(id!=null){
			PaymentOrder result = paymentService.getPaymentOrderById(id);
			model.addAttribute("orderForm", result);

			//读取支付流水状态,对账状态字典
			List<Dictionary> payStatusList = dictionaryService.getDictionariesByCode(HttpServiceConstants.DICT_PAY_STATUS);
			List<Dictionary> reconStatusList = dictionaryService.getDictionariesByCode(HttpServiceConstants.DICT_PAY_RECO_STATUS);
			model.addAttribute("payStatusList", payStatusList);
			model.addAttribute("reconStatusList", reconStatusList);
		}
		return "payment/payment-order-edit";
	}

	/**
	 * 保存用户信息
	 * @Author：XuYanbo
	 * @Date：2014年11月7日
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String save(@ModelAttribute("orderForm") PaymentOrder payment) throws BmcException {
		paymentService.savePaymentOrder(payment);
		return BmcConstants.YES;
	}
	
	/**
	 * 导出支付流水
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @return
	 */
	@RequestMapping(value="/export", method=RequestMethod.POST)
	public ModelAndView export(@ModelAttribute("payment") PaymentOrderCommand payment, HttpServletRequest request, HttpServletResponse response) throws BmcException {
		
		PageInfo<PaymentOrder> page = new PageInfo<PaymentOrder>(BmcConstants.CSV_PAGE_TYPE);
		Map<String, Object> model = new HashMap<String, Object>();
		if(StringUtils.isBlank(payment.getChannelId())){
			Map<String, String> dataMap = getUserDataMap(request);
			payment.setChannelId(dataMap.get(BmcConstants.SYSTEM_DATA_TYPE_CHANNEL));
		}
		
		HttpResult<PaymentOrder> result = paymentService.getAllPaymentOrders(payment, page);
		model.put(BmcConstants.CSV_DATA, result.getData());
		model.put(BmcConstants.CSV_TOTAL, result.getTotalCount());
		
        return new ModelAndView(new PaymentOrderCsvView(), model);
	}
}
