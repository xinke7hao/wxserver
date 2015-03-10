/************************************************************************************
 * @File name   :      ChartService.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年2月12日
 *
 * @Copyright Notice: 
 * Copyright (c) 2015 Hunantv.com. All  Rights Reserved.
 * This software is published under the terms of the HunanTV Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Who					Version				Comments				Date				
 * XuYanbo				1.0				Initial Version				2015年2月12日 下午2:49:10
 ************************************************************************************/
package com.hunantv.mbp.service;

import java.util.Map;

import com.hunantv.mbp.command.BossChartCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.dto.LineChartData;
import com.hunantv.mbp.dto.PieChartData;
import com.hunantv.mbp.dto.StackChartData;

/**
 * @author XuYanbo
 *
 */
public interface ChartService {

	/**
	 * 支付数来源饼图统计
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public PieChartData getPaymentOrderPieStatis(PaymentStatisCommand cmd, String type) throws BmcException;
	
	/**
	 * 支付综合统计
	 * @Author：XuYanbo
	 * @Date：2014年12月11日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	public LineChartData getPaymentChartStatis(PaymentStatisCommand cmd) throws BmcException;

	/**
	 * 组装Chart对象
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @param cmd
	 * @return
	 */
	public Map<String, LineChartData> getPaymentChartStatisMap(PaymentStatisCommand cmd) throws BmcException;
	
	/**
	 * OTT-BOSS-购买统计
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	public StackChartData getPayStatis(BossChartCommand cmd) throws BmcException;
	
	/**
	 * OTT-BOSS-充值统计
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	public StackChartData getRechargeStatis(BossChartCommand cmd) throws BmcException;
	
	/**
	 * OTT-BOSS-VIP购买人数统计
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @return
	 */
	public StackChartData getVipPaycountStatis(BossChartCommand cmd) throws BmcException;

	/**
	 * OTT-BOSS-付费用户统计
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @return
	 */
	public StackChartData getPaidUserStatis(BossChartCommand cmd) throws BmcException;
}
