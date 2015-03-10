/************************************************************************************
 * @File name   :      PaymentServiceTest.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2014年12月4日
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
 * XuYanbo				1.0				Initial Version				2014年12月4日 下午1:28:11
 ************************************************************************************/
package com.hunantv.mbp.unit.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.hunantv.mbp.command.MonitorCommand;
import com.hunantv.mbp.command.PaymentOrderCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.HttpServiceConstants;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.PaymentApi;
import com.hunantv.mbp.dto.PaymentChannel;
import com.hunantv.mbp.dto.PaymentMonitor;
import com.hunantv.mbp.dto.PaymentOrder;
import com.hunantv.mbp.dto.PaymentPlatform;
import com.hunantv.mbp.dto.PaymentStatis;
import com.hunantv.mbp.service.PaymentService;
import com.hunantv.mbp.unit.BaseJUnitTest;
import com.hunantv.mbp.utils.PassGenerator;

/**
 * @author XuYanbo
 */
public class PaymentServiceTest extends BaseJUnitTest {

	@Resource
	private PaymentService paymentService;
	
	@Test
	public void testSavePaymentOrder() throws Exception {
		PaymentOrder order = new PaymentOrder();
		order.setId("2014100817574966190041");
		order.setRecon_code(HttpServiceConstants.PAY_RECO_STATUS_HOUR_SUCCESS);
		order.setRecon_time("2014-12-17 15:53:00");
		String result = paymentService.savePaymentOrder(order);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testFixPaymentOrder() throws Exception {
		String id = "2014110714522032504526";	//同步返回,异步返回
		String type = "order";	//order,business
		String result = paymentService.fixPaymentOrder(id, type);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testGetAllPaymentOrders() throws Exception {
		PageInfo<PaymentOrder> page = new PageInfo<PaymentOrder>();
		PaymentOrderCommand cmd = new PaymentOrderCommand();
		HttpResult<PaymentOrder> result = paymentService.getAllPaymentOrders(cmd, page);
		List<PaymentOrder> orders = result.getData();
		assertTrue(orders!=null && orders.size()>0);
	}

	@Test
	public void testGetPaymentOrderById() throws Exception {
		String id = "2014110714522032504526";
		PaymentOrder order = paymentService.getPaymentOrderById(id);
		assertTrue(order!=null);
	}

	@Test
	public void testDeletePaymentOrderById() throws Exception {
		String id = "201410081625177881800";
		String result = paymentService.deletePaymentOrderById(id);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testGetAllPaymentChannels() throws Exception {
		PageInfo<PaymentChannel> page = new PageInfo<PaymentChannel>();
		HttpResult<PaymentChannel> result = paymentService.getAllPaymentChannels(page, "10,11,12");
		List<PaymentChannel> channels = result.getData();
		assertTrue(channels!=null && channels.size()>0);
	}

	@Test
	public void testGetPaymentChannelById() throws Exception {
		PageInfo<PaymentChannel> page = new PageInfo<PaymentChannel>();
		HttpResult<PaymentChannel> res = paymentService.getAllPaymentChannels(page, "10,11,12");
		List<PaymentChannel> channels = res.getData();
		Integer channelId = null;
		if(channels!=null && channels.size()>0){
			channelId = channels.get(0).getChannel_id();
		}
		PaymentChannel channel = paymentService.getPaymentChannelById(channelId+"");
		assertTrue(channel!=null);
	}

	@Test
	public void testSavePaymentChannel() throws Exception {
		PaymentChannel channel = new PaymentChannel();
		
		channel.setAgent_name("agent_name");
		channel.setChannel_alias("test"+PassGenerator.next());
		channel.setChannel_code("test"+PassGenerator.next());
		channel.setChannel_name("test"+PassGenerator.next());
		channel.setChannel_type("location");	//location/ott/qrcode/wait
		channel.setPay_url("http://unittest.hunantv.com/pay");
		channel.setReturn_url("http://unittest.hunantv.com/return");
		channel.setNotify_url("http://unittest.hunantv.com/notify");
		channel.setIs_available(HttpServiceConstants.PAY_PLATFORM_STATUS_VALID);
		channel.setSeller("seller");
		
		String result = paymentService.savePaymentChannel(channel);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testDeletePaymentChannel() throws Exception {
		String channelId = "42";
		paymentService.deletePaymentChannel(channelId);
		assertTrue(true);
	}

	@Test
	public void testChangePaymentChannelStatus() throws Exception {
		String channelId = "14";
		String result = paymentService.changePaymentChannelStatus(channelId, HttpServiceConstants.PAY_OPERATION_OPEN);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testGetAllPaymentPlatforms() throws Exception {
		PageInfo<PaymentPlatform> page = new PageInfo<PaymentPlatform>();
		HttpResult<PaymentPlatform> result = paymentService.getAllPaymentPlatforms(page, "10,11,12");
		List<PaymentPlatform> platforms = result.getData();
		assertTrue(platforms!=null && platforms.size()>0);
	}

	@Test
	public void testGetPaymentPlatformById() throws Exception {
		PageInfo<PaymentPlatform> page = new PageInfo<PaymentPlatform>();
		HttpResult<PaymentPlatform> res = paymentService.getAllPaymentPlatforms(page, "10,11,12");
		List<PaymentPlatform> platforms = res.getData();
		Integer platformId = null;
		if(platforms!=null && platforms.size()>0){
			platformId = platforms.get(0).getPlatform_id();
		}
		PaymentPlatform platform = paymentService.getPaymentPlatformById(platformId+"");
		assertTrue(platform!=null);
	}

	@Test
	public void testSavePaymentPlatform() throws Exception {
		PaymentPlatform platform = new PaymentPlatform();
		platform.setPlatform_name("test"+PassGenerator.next());
		platform.setContact("contact");
		platform.setExt_data("ext_data");
		platform.setRemark("remark");
		platform.setSecret(PassGenerator.next());
		platform.setStatus(HttpServiceConstants.PAY_PLATFORM_STATUS_VALID);
		String result = paymentService.savePaymentPlatform(platform);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testDeletePaymentPlatform() throws Exception {
		String platformId = "1009";
		String result = paymentService.deletePaymentPlatform(platformId);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testChangePaymentPlatformStatus() throws Exception {
		String platformId = "3";
		String result = paymentService.changePaymentPlatformStatus(platformId, HttpServiceConstants.PAY_OPERATION_OPEN);
		assertTrue(BmcConstants.YES.equals(result));
	}

	@Test
	public void testGetPaymentOrderStatis() throws Exception {
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		HttpResult<PaymentStatis> result = paymentService.getPaymentOrderStatis(cmd, page);
		List<PaymentStatis> statis = result.getData();
		assertTrue(statis!=null && statis.size()>0);
	}

	@Test
	public void testGetPaymentPlatformStatis() throws Exception {
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		HttpResult<PaymentStatis> result = paymentService.getPaymentPlatformStatis(cmd, page);
		List<PaymentStatis> statis = result.getData();
		assertTrue(statis!=null && statis.size()>0);
	}

	@Test
	public void testGetPaymentChannelStatis() throws Exception {
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		PageInfo<PaymentStatis> page = new PageInfo<PaymentStatis>();
		HttpResult<PaymentStatis> result = paymentService.getPaymentChannelStatis(cmd, page);
		List<PaymentStatis> statis = result.getData();
		assertTrue(statis!=null && statis.size()>0);
	}

	@Test
	public void testSearchPaymentApiLogs() throws Exception {
		MonitorCommand cmd = new MonitorCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		PageInfo<PaymentApi> page = new PageInfo<PaymentApi>();
		HttpResult<PaymentApi> result = paymentService.searchPaymentApiLogs(cmd, page);
		List<PaymentApi> apiLogs = result.getData();
		assertTrue(apiLogs!=null && apiLogs.size()>0);
	}

	@Test
	public void testSearchPaymentOrderLogs() throws Exception {
		MonitorCommand cmd = new MonitorCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		PageInfo<PaymentMonitor> page = new PageInfo<PaymentMonitor>();
		HttpResult<PaymentMonitor> result = paymentService.searchPaymentOrderLogs(cmd, page);
		List<PaymentMonitor> orderLogs = result.getData();
		assertTrue(orderLogs!=null && orderLogs.size()>0);
	}

	@Test
	public void testGetPaymentReconStatis() throws Exception {
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-30");
		HttpResult<PaymentStatis> result = paymentService.getPaymentReconStatis(cmd);
		List<PaymentStatis> statis = result.getData();
		assertTrue(statis!=null && statis.size()>0);
	}
	
}
