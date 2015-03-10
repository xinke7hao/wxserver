package com.hunantv.mbp.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.hunantv.mbp.command.BossAccountCommand;
import com.hunantv.mbp.command.BossAssetCommand;
import com.hunantv.mbp.command.BossChartCommand;
import com.hunantv.mbp.command.BossContentCommand;
import com.hunantv.mbp.command.BossPurchaseCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.PageInfo;
import com.hunantv.mbp.dto.BossAccount;
import com.hunantv.mbp.dto.BossAsset;
import com.hunantv.mbp.dto.BossChannel;
import com.hunantv.mbp.dto.BossPackAssetContent;
import com.hunantv.mbp.dto.BossSimpleChannel;
import com.hunantv.mbp.dto.BossSourceContent;
import com.hunantv.mbp.dto.BossPackContent;
import com.hunantv.mbp.dto.BossPurchase;
import com.hunantv.mbp.dto.BossSingleContent;
import com.hunantv.mbp.dto.BossSingleProduct;
import com.hunantv.mbp.dto.BossSystemBitrate;
import com.hunantv.mbp.dto.BossSystemPlatform;
import com.hunantv.mbp.dto.BossSystemTag;
import com.hunantv.mbp.dto.BossSystemVip;
import com.hunantv.mbp.dto.BossVipContent;
import com.hunantv.mbp.dto.BossVipProduct;
import com.hunantv.mbp.dto.Manufacturner;
import com.hunantv.mbp.dto.PaidUserStatis;
import com.hunantv.mbp.dto.PaycountStatis;
import com.hunantv.mbp.dto.PaytimeStatis;
import com.hunantv.mbp.dto.VipProduct;

public interface BossService {

	/**
	 * 获取厂商列表
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @return
	 * @throws BmcException
	 */
	public List<Manufacturner> getAllManufacturners() throws BmcException;
	
	/**
	 * 获取支付渠道列表
	 * @Author：XuYanbo
	 * @Date：2015年1月29日
	 * @return
	 * @throws BmcException
	 */
	public List<BossSimpleChannel> getAllChannels() throws BmcException;
	
	/**
	 * 获取VIP产品列表
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @return
	 * @throws BmcException
	 */
	public List<VipProduct> getAllVipProducts() throws BmcException;
	
	/**
	 * OTT-BOSS-账户管理
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossAccount> searchAccounts(BossAccountCommand cmd, PageInfo<BossAccount> page) throws BmcException;

	/**
	 * OTT-BOSS-订购关系
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossPurchase> searchPurchases(BossPurchaseCommand cmd, PageInfo<BossPurchase> page) throws BmcException;

	/**
	 * 频道源内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossSourceContent> searchChannelSources(BossContentCommand cmd, PageInfo<BossSourceContent> page) throws BmcException;

	/**
	 * VIP内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossVipContent> searchVipContents(BossContentCommand cmd, PageInfo<BossVipContent> page) throws BmcException;

	/**
	 * 集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param page
	 * @return
	 */
	public HttpResult<BossPackContent> searchPackContents(PageInfo<BossPackContent> page) throws BmcException;

	/**
	 * 单点内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossSingleContent> searchSingleContents(BossContentCommand cmd, PageInfo<BossSingleContent> page) throws BmcException;

	/**
	 * 获取媒资文件一级分类列表
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @return
	 */
	public JSONArray getAllAssetKind() throws BmcException;

	/**
	 * VIP身份产品
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossVipProduct> searchVipProducts(BossContentCommand cmd, PageInfo<BossVipProduct> page) throws BmcException;
	
	/**
	 * 媒资单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossSingleProduct> searchSingleProducts(BossContentCommand cmd, PageInfo<BossSingleProduct> page) throws BmcException;

	/**
	 * 角标
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param page
	 * @return
	 */
	public HttpResult<BossSystemTag> searchSystemTags(PageInfo<BossSystemTag> page) throws BmcException;

	/**
	 * 获取所有角标
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param page
	 * @return
	 */
	public HttpResult<BossSystemTag> getSystemTags() throws BmcException;

	/**
	 * VIP身份
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param page
	 * @return
	 */
	public HttpResult<BossSystemVip> searchSystemVips(PageInfo<BossSystemVip> page) throws BmcException;
	
	/**
	 * VIP身份列表
	 * @Author：XuYanbo
	 * @Date：2015年2月17日
	 * @return
	 */
	public List<BossSystemVip> loadSystemVips() throws BmcException;
	public HttpResult<BossSystemVip> getSystemVips() throws BmcException;
	
	/**
	 * 平台搜索
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<BossSystemPlatform> searchSystemPlatforms(PageInfo<BossSystemPlatform> page) throws BmcException;
	
	/**
	 * 获取清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @param page
	 * @return
	 */
	public HttpResult<BossSystemBitrate> searchSystemBitrates(PageInfo<BossSystemBitrate> page) throws BmcException;

	/**
	 * 查询用户帐户信息
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 */
	public BossAccount getAccountById(String id) throws BmcException;
	
	/**
	 * 根据ID获取单点收费产品详情
	 * @Author：XuYanbo
	 * @Date：2015年3月5日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	public BossPackAssetContent getSingleContentById(String id) throws BmcException;
	
	/**
	 * 查看媒资文件列表
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	public HttpResult<BossAsset> searchAssetFiles(BossAssetCommand cmd, PageInfo<BossAsset> page) throws BmcException;

	/**
	 * 获取BOSS购买统计数据(包含VIP购买金额统计)
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @return
	 */
	public HttpResult<PaytimeStatis> getPayChartStatis(BossChartCommand cmd) throws BmcException;

	/**
	 * 获取BOSS充值统计数据
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @return
	 */
	public HttpResult<PaytimeStatis> getRechargeChartStatis(BossChartCommand cmd) throws BmcException;

	/**
	 * 获取BOSS-VIP购买人数统计数据
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @return
	 */
	public HttpResult<PaycountStatis> getVipPaycountChartStatis(BossChartCommand cmd) throws BmcException;

	/**
	 * 获取BOSS-付费用户统计数据
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @return
	 */
	public HttpResult<PaidUserStatis> getPaidUserChartStatis(BossChartCommand cmd) throws BmcException;

	/**
	 * 获取角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @return
	 */
	public BossSystemTag getSystemTagById(String id) throws BmcException;

	/**
	 * 获取VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 */
	public BossSystemVip getSystemVipById(String id) throws BmcException;

	/**
	 * 获取清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 */
	public BossSystemBitrate getSystemBitrateById(String id) throws BmcException;

	/**
	 * 获取所有清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @return
	 */
	public HttpResult<BossSystemBitrate> getAllBitrates() throws BmcException;

	/**
	 * 获取厂商详情
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 */
	public BossSystemPlatform getSystemPlatformById(String id) throws BmcException;

	/**
	 * 获取VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 */
	public BossVipProduct getVipProductById(String id) throws BmcException;

	/**
	 * 获取单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @return
	 */
	public BossSingleProduct getSingleProductById(String id) throws BmcException;

	/**
	 * 获取集合产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @return
	 */
	public BossPackContent getPackContentById(String id) throws BmcException;

	/**
	 * 获取支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 */
	public BossChannel getSystemChannelById(String id) throws BmcException;

	/**
	 * 支付渠道列表
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param channelPage
	 * @return
	 */
	public HttpResult<BossChannel> searchSystemChannels(PageInfo<BossChannel> channelPage) throws BmcException;

	/**
	 * 获取集合包信息(包含媒资文件)
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param pid
	 * @return
	 */
	public BossPackAssetContent getPackAssetsContentById(String pid) throws BmcException;

}
