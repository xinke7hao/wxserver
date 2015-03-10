/************************************************************************************
 * @File name   :      ChartServiceImpl.java
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
 * XuYanbo				1.0				Initial Version				2015年2月12日 下午2:50:34
 ************************************************************************************/
package com.hunantv.mbp.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hunantv.mbp.command.BossChartCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.MbpHttpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.dto.ItemValue;
import com.hunantv.mbp.dto.LineChartData;
import com.hunantv.mbp.dto.LineValue;
import com.hunantv.mbp.dto.PaidUserStatis;
import com.hunantv.mbp.dto.PaycountStatis;
import com.hunantv.mbp.dto.PaytimeStatis;
import com.hunantv.mbp.dto.PieChartData;
import com.hunantv.mbp.dto.StackChartData;
import com.hunantv.mbp.dto.StackValue;
import com.hunantv.mbp.service.BossService;
import com.hunantv.mbp.service.ChartService;
import com.hunantv.mbp.utils.BmcHttpClient;

/**
 * @author XuYanbo
 *
 */
@Service
public class ChartServiceImpl implements ChartService {

	@Resource
	private BossService bossService;
	
	/**
	 * 支付数来源饼图统计
	 * @Author：XuYanbo
	 * @Date：2014年12月10日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_CHART, type=BmcConstants.LOG_TYPE_QUERY, desc="支付数来源饼图统计")
	public PieChartData getPaymentOrderPieStatis(PaymentStatisCommand cmd, String type) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/orderchart");
		StringBuffer params = new StringBuffer();

		if(StringUtils.isNotBlank(cmd.getChannelId())){
			params.append("&channel_id=" + cmd.getChannelId());
		}
		if(StringUtils.isNotBlank(cmd.getEndDay())){
			params.append("&end_day=" + cmd.getEndDay());
		}
		if(StringUtils.isNotBlank(cmd.getPlatformId())){
			params.append("&platform_id=" + cmd.getPlatformId());
		}
		if(StringUtils.isNotBlank(cmd.getStartDay())){
			params.append("&start_day=" + cmd.getStartDay());
		}
		
		if(StringUtils.isBlank(type)){
			type = "platform";
		}
		params.append("&type=" + type);
		
		return BmcHttpClient.sendGetForObject(uri.toString(), params.toString(), PieChartData.class, true);
	}
	
	/**
	 * 支付综合统计
	 * @Author：XuYanbo
	 * @Date：2014年12月11日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.STATIS_PAYMENT_CHART, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付综合统计数据")
	public LineChartData getPaymentChartStatis(PaymentStatisCommand cmd) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.PAYMENT_SERVER_URI + "/statis/chart");
		StringBuffer params = new StringBuffer();
		
		if(StringUtils.isNotBlank(cmd.getChannelId())){
			params.append("&channel_id=" + cmd.getChannelId());
		}
		if(StringUtils.isNotBlank(cmd.getEndDay())){
			params.append("&end_day=" + cmd.getEndDay());
		}
		if(StringUtils.isNotBlank(cmd.getFields())){
			params.append("&fields=" + cmd.getFields());
		}
		if(StringUtils.isNotBlank(cmd.getPlatformId())){
			params.append("&platform_id=" + cmd.getPlatformId());
		}
		if(StringUtils.isNotBlank(cmd.getStartDay())){
			params.append("&start_day=" + cmd.getStartDay());
		}
		
		return BmcHttpClient.sendGetForObject(uri.toString(), params.toString(), LineChartData.class, true);
	}
	
	/**
	 * 组装Chart对象
	 * @Author：XuYanbo
	 * @Date：2014年12月19日
	 * @param cmd
	 * @return
	 */
	@Override
	public Map<String, LineChartData> getPaymentChartStatisMap(PaymentStatisCommand cmd) throws BmcException {
		
		String[] fs = cmd.getFields().split(BmcConstants.COMMA);
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
		
		LineChartData data = this.getPaymentChartStatis(cmd);
		
		//构建Legend
		List<String> legends = data.getLegend();
		legends.forEach(lg -> {
			String groupKey = this.getFieldMapKey(fieldMap, lg);
			legendMap.get(groupKey).add(lg.substring(lg.indexOf(BmcConstants.COLON)+1));
		});
		
		//构建Series
		List<LineValue> values = data.getSeries();
		values.forEach(v -> {
			String groupKey = this.getFieldMapKey(fieldMap, v.getName());
			v.setName(v.getName().substring(v.getName().indexOf(BmcConstants.COLON)+1));
			seriesMap.get(groupKey).add(v);
		});
		
		//组装LineChartData[]
		List<String> category = data.getCategory();
		Map<String, LineChartData> dataMap = new HashMap<String, LineChartData>();
		for(Entry<String, StringBuffer> entry: fieldMap.entrySet()){
			LineChartData chartData = new LineChartData(category);
			chartData.setLegend(legendMap.get(entry.getKey()));
			chartData.setSeries(seriesMap.get(entry.getKey()));
			dataMap.put(entry.getKey(), chartData);
		}
		
		return dataMap;
	}
	
	/**
	 * 根据value获取map中对应的key值
	 * @Author：XuYanbo
	 * @Date：2014年12月15日
	 * @param map
	 * @param value
	 * @return
	 */
	private String getFieldMapKey(Map<String, StringBuffer> map, String value){
		String prefix = value.substring(0, value.indexOf(BmcConstants.COLON));
		StringBuffer key = new StringBuffer();
		map.forEach((k,v) -> { if(v.toString().contains(prefix)) key.append(k); });
		return key.toString();
	}
	
	/**
	 * OTT-BOSS-购买统计
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	public StackChartData getPayStatis(BossChartCommand cmd) throws BmcException {

		StackChartData chart = null;
		HttpResult<PaytimeStatis> result = bossService.getPayChartStatis(cmd);
		List<PaytimeStatis> res = result.getData();
		
		if(res!=null && res.size()>0){
			chart = new StackChartData();

			String[] legends = cmd.getProductName().split(BmcConstants.COMMA);	//产品分类
			List<String> category = new ArrayList<String>();					//X轴坐标
			List<StackValue> series = new ArrayList<StackValue>();				//购买次数堆积数据
			List<StackValue> moneySeries = new ArrayList<StackValue>();			//购买金额堆积数据
			
			Map<String, List<ItemValue>> itemMap = new HashMap<String, List<ItemValue>>();	// 20150101 -> [{name:'A', value:10}, {name:'B', value:15}]
			
			DecimalFormat df = new DecimalFormat("#.00");
			
			//form map from response data (key for time)
			for(PaytimeStatis statis: res){
				if(!category.contains(statis.getDate())){
					category.add(statis.getDate());
				}
				if(!itemMap.containsKey(statis.getDate())){
					itemMap.put(statis.getDate(), new ArrayList<ItemValue>());
				}
				itemMap.get(statis.getDate()).add(new ItemValue(statis.getName(), statis.getTimes(), statis.getMoney()));
			}
			
			//generate chart data
			for(String legend: legends){
				StackValue stackValue = new StackValue(legend);
				StackValue moneyStackValue = new StackValue(legend);
				List<Double> data = new ArrayList<Double>();
				List<Double> moneyData = new ArrayList<Double>();
				for(String cat: category){
					boolean found = false;
					for(Entry<String, List<ItemValue>> entry: itemMap.entrySet()){
						if(entry.getKey().equals(cat)){
							List<ItemValue> temp = entry.getValue();
							for(ItemValue t: temp){
								if(t.getLabel().equals(legend)){
									data.add(t.getValue());
									moneyData.add(Double.parseDouble(df.format(t.getMoney()/100)));
									found = true;
									break;
								}
							}
							if(found) break;
						}
					}
					if(!found){
						data.add(0.0);
						moneyData.add(0.0);
					}
				}
				
				stackValue.setData(data);
				moneyStackValue.setData(moneyData);
				series.add(stackValue);
				moneySeries.add(moneyStackValue);
			}
			
			//计算堆积总和
			int len = category.size();
			double totalCount = 0.0, totalMoney = 0.0;
			double[] totals = new double[len], totalMoneys = new double[len];
			for(int i=0;i<len;i++){
				for(int j=0;j<legends.length;j++){
					totals[i] += series.get(j).getData().get(i);
					totalMoneys[i] += moneySeries.get(j).getData().get(i);	//金额单位分转元
				}
				totalCount += totals[i];
				totalMoney += totalMoneys[i];
				totalMoneys[i] = Double.parseDouble(df.format(totalMoneys[i]));
			}
			
			chart.setLegend(legends);
			chart.setCategory(category);
			chart.setSeries(series);
			chart.setMoneySeries(moneySeries);
			chart.setTotal(totalCount);
			chart.setTotals(totals);
			chart.setTotalMoney(Double.parseDouble(df.format(totalMoney)));
			chart.setTotalMoneys(totalMoneys);
		}
		
		return chart;
	}
	
	/**
	 * OTT-BOSS-购买统计
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	public StackChartData getRechargeStatis(BossChartCommand cmd) throws BmcException {

		StackChartData chart = null;
		HttpResult<PaytimeStatis> result = bossService.getRechargeChartStatis(cmd);
		List<PaytimeStatis> res = result.getData();
		
		if(res!=null && res.size()>0){
			chart = new StackChartData();

			String[] legends = cmd.getProductName().split(BmcConstants.COMMA);	//产品分类
			List<String> category = new ArrayList<String>();					//X轴坐标
			List<StackValue> series = new ArrayList<StackValue>();				//购买次数堆积数据
			List<StackValue> moneySeries = new ArrayList<StackValue>();			//购买金额堆积数据
			
			Map<String, List<ItemValue>> itemMap = new HashMap<String, List<ItemValue>>();	// 20150101 -> [{name:'A', value:10}, {name:'B', value:15}]
			
			DecimalFormat df = new DecimalFormat("#.00");
			
			//form map from response data (key for time)
			for(PaytimeStatis statis: res){
				if(!category.contains(statis.getDate())){
					category.add(statis.getDate());
				}
				if(!itemMap.containsKey(statis.getDate())){
					itemMap.put(statis.getDate(), new ArrayList<ItemValue>());
				}
				itemMap.get(statis.getDate()).add(new ItemValue(statis.getName(), statis.getTimes(), statis.getMoney()));
			}
			
			//generate chart data
			for(String legend: legends){
				StackValue stackValue = new StackValue(legend);
				StackValue moneyStackValue = new StackValue(legend);
				List<Double> data = new ArrayList<Double>();
				List<Double> moneyData = new ArrayList<Double>();
				for(String cat: category){
					boolean found = false;
					for(Entry<String, List<ItemValue>> entry: itemMap.entrySet()){
						if(entry.getKey().equals(cat)){
							List<ItemValue> temp = entry.getValue();
							for(ItemValue t: temp){
								if(t.getLabel().equals(legend)){
									data.add(t.getValue());
									moneyData.add(Double.parseDouble(df.format(t.getMoney()/100)));
									found = true;
									break;
								}
							}
							if(found) break;
						}
					}
					if(!found){
						data.add(0.0);
						moneyData.add(0.0);
					}
				}
				
				stackValue.setData(data);
				moneyStackValue.setData(moneyData);
				series.add(stackValue);
				moneySeries.add(moneyStackValue);
			}
			
			//计算堆积总和
			int len = category.size();
			double totalCount = 0.0, totalMoney = 0.0;
			double[] totals = new double[len], totalMoneys = new double[len];
			for(int i=0;i<len;i++){
				for(int j=0;j<legends.length;j++){
					totals[i] += series.get(j).getData().get(i);
					totalMoneys[i] += moneySeries.get(j).getData().get(i);	//金额单位分转元
				}
				totalCount += totals[i];
				totalMoney += totalMoneys[i];
				totalMoneys[i] = Double.parseDouble(df.format(totalMoneys[i]));
			}
			
			chart.setLegend(legends);
			chart.setCategory(category);
			chart.setSeries(series);
			chart.setMoneySeries(moneySeries);
			chart.setTotal(totalCount);
			chart.setTotals(totals);
			chart.setTotalMoney(Double.parseDouble(df.format(totalMoney)));
			chart.setTotalMoneys(totalMoneys);
		}
		
		return chart;
	}
	
	/**
	 * OTT-BOSS-VIP购买人数统计
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @return
	 */
	@Override
	public StackChartData getVipPaycountStatis(BossChartCommand cmd) throws BmcException {
		
		StackChartData chart = null;
		HttpResult<PaycountStatis> result = bossService.getVipPaycountChartStatis(cmd);
		List<PaycountStatis> res = result.getData();
		
		if(res!=null && res.size()>0){
			chart = new StackChartData();

			String[] legends = {"首次", "再次"};	//产品分类
			List<String> category = new ArrayList<String>();					//X轴坐标
			List<StackValue> series = new ArrayList<StackValue>();				//堆积数据
			
			int len = res.size(), index = 0, totalCount = 0;
			double[] totals = new double[len];
			StackValue firstStackValue = new StackValue(legends[0]);
			List<Double> firstData = new ArrayList<Double>();
			StackValue againStackValue = new StackValue(legends[1]);
			List<Double> againData = new ArrayList<Double>();
			
			for(PaycountStatis s: res){
				category.add(s.getDate());
				double again = (double)s.getAgain_cnt(), first = (double)s.getFirst_cnt(), total = (double)s.getAll_cnt();
				if(first > total){
					first = total; again = 0.0;
				}
				firstData.add(first);
				againData.add(again);
				totals[index++] = (double)s.getAll_cnt();
				totalCount += s.getAll_cnt();
			}

			firstStackValue.setData(firstData);
			againStackValue.setData(againData);
			series.add(firstStackValue);
			series.add(againStackValue);
			
			chart.setLegend(legends);
			chart.setCategory(category);
			chart.setSeries(series);
			chart.setTotal(totalCount);
			chart.setTotals(totals);
		}
		
		return chart;
	}
	
	/**
	 * OTT-BOSS-付费用户统计
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @return
	 */
	@Override
	public StackChartData getPaidUserStatis(BossChartCommand cmd) throws BmcException {
		
		StackChartData chart = new StackChartData();
		HttpResult<PaidUserStatis> result = bossService.getPaidUserChartStatis(cmd);
		List<PaidUserStatis> res = result.getData();
		
		if(res!=null && res.size()>0){

			String[] legends = {"VIP","单点","集合"};
			List<String> category = new ArrayList<String>();					//X轴坐标
			Map<String, List<PaidUserStatis>> itemMap = new HashMap<String, List<PaidUserStatis>>();
			
			//form map from response data (key for time)
			for(PaidUserStatis statis: res){
				if(!category.contains(statis.getDate())){
					category.add(statis.getDate());
				}
				if(!itemMap.containsKey(statis.getDate())){
					itemMap.put(statis.getDate(), new ArrayList<PaidUserStatis>());
				}
				itemMap.get(statis.getDate()).add(statis);
			}

			List<StackValue> series = new ArrayList<StackValue>();				//累计数堆积数据
			List<StackValue> moneySeries = new ArrayList<StackValue>();			//有效数堆积数据
			
			for(String legend: legends){
				StackValue stackValue = new StackValue(legend);
				StackValue moneyStackValue = new StackValue(legend);
				List<Double> data = new ArrayList<Double>();
				List<Double> moneyData = new ArrayList<Double>();
				for(String cat: category){
					boolean found = false;
					for(Entry<String, List<PaidUserStatis>> entry: itemMap.entrySet()){
						if(entry.getKey().equals(cat)){
							List<PaidUserStatis> temp = entry.getValue();
							for(PaidUserStatis t: temp){
								if(t.getData_name().equals(legend)){
									data.add((double)t.getAll_cnt());
									moneyData.add((double)t.getValid_cnt());
									found = true;
									break;
								}
							}
							if(found) break;
						}
					}
					if(!found){
						data.add(0.0);
						moneyData.add(0.0);
					}
				}
				
				stackValue.setData(data);
				moneyStackValue.setData(moneyData);
				series.add(stackValue);
				moneySeries.add(moneyStackValue);
			}
			
			//计算堆积总和
			int len = category.size();
			double[] totals = new double[len], totalMoneys = new double[len];
			for(int i=0;i<len;i++){
				for(int j=0;j<legends.length;j++){
					totals[i] += series.get(j).getData().get(i);
					totalMoneys[i] += moneySeries.get(j).getData().get(i);
				}
			}
			
			chart.setLegend(legends);
			chart.setCategory(category);
			chart.setSeries(series);
			chart.setMoneySeries(moneySeries);
			chart.setTotal(totals[len-1]);
			chart.setTotals(totals);
			chart.setTotalMoney(totalMoneys[len-1]);
			chart.setTotalMoneys(totalMoneys);
		}
		
		return chart;
	}
}
