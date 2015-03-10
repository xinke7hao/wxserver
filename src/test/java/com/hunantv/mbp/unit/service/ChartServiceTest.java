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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.dto.LineChartData;
import com.hunantv.mbp.dto.LineValue;
import com.hunantv.mbp.dto.PieChartData;
import com.hunantv.mbp.service.ChartService;
import com.hunantv.mbp.unit.BaseJUnitTest;

/**
 * @author XuYanbo
 */
public class ChartServiceTest extends BaseJUnitTest {

	@Resource
	private ChartService chartService;
	
	@Test
	public void testGetPaymentOrderPieStatis() throws Exception {
		String day = "2014-11-1", type = "platform";
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setEndDay(day);
		PieChartData data = chartService.getPaymentOrderPieStatis(cmd, type);
		System.out.println(data.getSeries().size());
		assertTrue(true);
	}
	
	@Test
	public void testGetPaymentChartStatis() throws Exception {
		PaymentStatisCommand cmd = new PaymentStatisCommand();
		cmd.setStartDay("2014-11-1");
		cmd.setEndDay("2014-11-7");
		cmd.setChannelId("10");
		cmd.setPlatformId("105,101");
		String field = "A-finish,C-price,B-succ";
		String[] fs = field.split(",");
		int len = fs.length;
		String[] fields = new String[len];
		
		Map<String, StringBuffer> fieldMap = new HashMap<String, StringBuffer>();
		Map<String, List<String>> legendMap = new HashMap<String, List<String>>();
		Map<String, List<LineValue>> seriesMap = new HashMap<String, List<LineValue>>();
		
		for(int i=0;i<len;i++){
			String[] ps = fs[i].split("-");
			fields[i] = ps[1];
			if(!fieldMap.containsKey(ps[0])){
				fieldMap.put(ps[0], new StringBuffer(ps[1]));
				legendMap.put(ps[0], new ArrayList<String>());
				seriesMap.put(ps[0], new ArrayList<LineValue>());
			} else {
				fieldMap.put(ps[0], fieldMap.get(ps[0]).append(BmcConstants.COMMA).append(ps[1]));
			}	
		}
		cmd.setFields(StringUtils.join(fields, BmcConstants.COMMA));
		
		LineChartData data = chartService.getPaymentChartStatis(cmd);
		
		//构建Legend
		List<String> legends = data.getLegend();
		for(String lg: legends){
			String groupKey = this.getFieldMapKey(fieldMap, lg);
			legendMap.get(groupKey).add(lg);
		}
		
		//构建Series
		List<LineValue> values = data.getSeries();
		int size = values.size();
		for(int i=size-1;i>=0;i--){
			LineValue val = values.get(i);
			String groupKey = this.getFieldMapKey(fieldMap, val.getName());
			seriesMap.get(groupKey).add(val);
		}
		
		//组装LineChartData[]
		List<String> category = data.getCategory();
		Map<String, LineChartData> dataMap = new HashMap<String, LineChartData>();
		for(Entry<String, StringBuffer> entry: fieldMap.entrySet()){
			LineChartData chartData = new LineChartData(category);
			chartData.setLegend(legendMap.get(entry.getKey()));
			chartData.setSeries(seriesMap.get(entry.getKey()));
			dataMap.put(entry.getKey(), chartData);
		}

		System.out.println(dataMap);
		
		assertTrue(true);
	}

	//Map中根据value查找key值
	private String getFieldMapKey(Map<String, StringBuffer> map, String value){
		String prefix = value.substring(0, value.indexOf(BmcConstants.COLON));
		String key = null;
		for(Entry<String, StringBuffer> entry: map.entrySet()){
			if(prefix.equals(entry.getValue().toString())){
				key = entry.getKey();
				break;
			}
		}
		return key;
	}
}
