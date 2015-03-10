package com.hunantv.mbp.service;

import com.alibaba.fastjson.JSONObject;
import com.hunantv.mbp.command.AppleQueryCommand;
import com.hunantv.mbp.command.MonitorCommand;
import com.hunantv.mbp.command.PaymentOrderCommand;
import com.hunantv.mbp.command.PaymentStatisCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.Blacklist;
import com.hunantv.mbp.dto.PaymentApi;
import com.hunantv.mbp.dto.PaymentChannel;
import com.hunantv.mbp.dto.PaymentMonitor;
import com.hunantv.mbp.dto.PaymentOrder;
import com.hunantv.mbp.dto.PaymentPlatform;
import com.hunantv.mbp.dto.PaymentStatis;
import com.hunantv.mbp.dto.SmsAsyncNotify;

public interface PaymentService {

	/**
	 * 查询支付流水列表
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param cmd
	 * @param pageInfo
	 * @return
	 */
	public HttpResult<PaymentOrder> getAllPaymentOrders(PaymentOrderCommand cmd, PageInfo<PaymentOrder> pageInfo) throws BmcException;
	
	/**
	 * 查询单条支付流水列表
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param cmd
	 * @param pageInfo
	 * @return
	 */
	public PaymentOrder getPaymentOrderById(String orderId) throws BmcException;
	
	/**
	 * 根据ID删除单条支付流水
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param id
	 */
	public String deletePaymentOrderById(String id) throws BmcException;
	
	/**
	 * 更新支付流水信息
	 * @Author：XuYanbo
	 * @Date：2014年11月14日
	 * @param payment
	 */
	public String savePaymentOrder(PaymentOrder payment) throws BmcException;
	
	/**
	 * 支付流水补单
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param id
	 * @param type
	 * @return
	 * @throws BmcException
	 */
	public String fixPaymentOrder(String id, String type) throws BmcException;
	
	/**
	 * 获取支付渠道列表
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<PaymentChannel> getAllPaymentChannels(PageInfo<PaymentChannel> page, String channelIds) throws BmcException;
	
	/**
	 * 查询单条支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param channelId
	 * @return
	 * @throws BmcException
	 */
	public PaymentChannel getPaymentChannelById(String channelId) throws BmcException;
	
	/**
	 * 保存支付渠道(创建/修改共用)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channel
	 * @return
	 * @throws BmcException
	 */
	public String savePaymentChannel(PaymentChannel channel) throws BmcException;
	
	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channelId
	 * @return
	 * @throws BmcException
	 */
	public String deletePaymentChannel(String channelId) throws BmcException;
	
	/**
	 * 修改支付渠道状态(关闭/开启)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param channelId
	 * @param operation
	 * @return
	 * @throws BmcException
	 */
	public String changePaymentChannelStatus(String channelId, int operation) throws BmcException;
	
	/**
	 * 获取业务平台列表
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<PaymentPlatform> getAllPaymentPlatforms(PageInfo<PaymentPlatform> page, String platformIds) throws BmcException;

	/**
	 * 查询单条业务平台
	 * @Author：XuYanbo
	 * @Date：2014年11月17日
	 * @param platformId
	 * @return
	 * @throws BmcException
	 */
	public PaymentPlatform getPaymentPlatformById(String platformId) throws BmcException;
	
	/**
	 * 保存业务平台信息(创建/修改共用)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platform
	 * @return
	 * @throws BmcException
	 */
	public String savePaymentPlatform(PaymentPlatform platform) throws BmcException;
	
	/**
	 * 删除业务平台信息
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platformId
	 * @return
	 * @throws BmcException
	 */
	public String deletePaymentPlatform(String platformId) throws BmcException;
	
	/**
	 * 修改业务平台状态(开启/关闭)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param platformId
	 * @param operation
	 * @return
	 * @throws BmcException
	 */
	public String changePaymentPlatformStatus(String platformId, int operation) throws BmcException;
	
	/**
	 * 支付流水按日统计汇总(维度包含业务平台,支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年11月15日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<PaymentStatis> getPaymentOrderStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException;
	
	/**
	 * 支付流水按日统计汇总(维度业务平台)
	 * @Author：XuYanbo
	 * @Date：2014年11月20日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<PaymentStatis> getPaymentPlatformStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException;
	
	/**
	 * 支付流水按日统计汇总(维度支付渠道)
	 * @Author：XuYanbo
	 * @Date：2014年11月20日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<PaymentStatis> getPaymentChannelStatis(PaymentStatisCommand cmd, PageInfo<PaymentStatis> page) throws BmcException;
	
	/**
	 * 支付系统API调用监控
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<PaymentApi> searchPaymentApiLogs(MonitorCommand cmd, PageInfo<PaymentApi> page) throws BmcException;

	/**
	 * 支付流水监控
	 * @Author：XuYanbo
	 * @Date：2014年11月21日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	HttpResult<PaymentMonitor> searchPaymentOrderLogs(MonitorCommand cmd, PageInfo<PaymentMonitor> page) throws BmcException;

	/**
	 * 支付流水对账
	 * @Author：XuYanbo
	 * @Date：2014年11月24日
	 * @param cmd
	 * @return
	 */
	public HttpResult<PaymentStatis> getPaymentReconStatis(PaymentStatisCommand cmd) throws BmcException;
	
	/**
	 * 财务报表(账号)
	 * @Author：XuYanbo
	 * @Date：2015年2月4日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public JSONObject getPaymentFinancialReconStatis(PaymentStatisCommand cmd) throws BmcException;
	
	/**
	 * 财务报表(支付渠道)
	 * @Author：XuYanbo
	 * @Date：2015年2月6日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public JSONObject getPaymentFinancialChannelStatis(PaymentStatisCommand cmd) throws BmcException;
	
	/**
	 * 根据ID查询黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @return
	 */
	public Blacklist getBlacklistById(String id) throws BmcException;

	/**
	 * 获取黑名单列表
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param cmd
	 * @param blacklistPage
	 * @return
	 */
	public HttpResult<Blacklist> getAllBlacklists(Blacklist cmd, PageInfo<Blacklist> blacklistPage) throws BmcException;

	/**
	 * 删除黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param id
	 * @return
	 */
	public String deleteBlacklist(String id) throws BmcException;

	/**
	 * 新增/修改黑名单
	 * @Author：XuYanbo
	 * @Date：2014年12月30日
	 * @param blacklist
	 */
	public String saveBlacklist(Blacklist blacklist) throws BmcException;

	/**
	 * 9588异步通知查询
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param sms
	 * @param smsPage
	 * @return
	 */
	public HttpResult<SmsAsyncNotify> getAllSmsAsyncNotifys(SmsAsyncNotify sms, PageInfo<SmsAsyncNotify> smsPage) throws BmcException;

	/**
	 * 根据手机号获取支付详情信息
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param mobile
	 * @return
	 */
	public JSONObject getPaymentDetailByMobile(String mobile) throws BmcException;

	/**
	 * 根据手机号获取9588状态
	 * @Author：XuYanbo
	 * @Date：2015年1月5日
	 * @param mobile
	 * @return
	 */
	public JSONObject getSmsStatusByMobile(String mobile) throws BmcException;

	/**
	 * 订单发货
	 * @Author：XuYanbo
	 * @Date：2015年1月6日
	 * @param orderId
	 * @return
	 */
	public String deliverPaymentOrder(String orderId) throws BmcException;

	/**
	 * 苹果支付查询
	 * @Author：XuYanbo
	 * @Date：2015年1月13日
	 * @param cmd
	 * @return
	 */
	public JSONObject searchApplePayDetail(AppleQueryCommand cmd) throws BmcException;

	/**
	 * 用户分布地图数据
	 * @Author：XuYanbo
	 * @Date：2015年1月21日
	 * @return
	 */
	public JSONObject getPayUserChartStatis() throws BmcException;
}
