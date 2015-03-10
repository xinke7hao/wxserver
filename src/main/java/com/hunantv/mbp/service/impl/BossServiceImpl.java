/************************************************************************************
 * @File name   :      BossServiceImpl.java
 *
 * @Author      :      XuYanbo
 *
 * @Date        :      2015年1月12日
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
 * XuYanbo				1.0				Initial Version				2015年1月12日 上午11:44:38
 ************************************************************************************/
package com.hunantv.mbp.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.hunantv.mbp.command.BossAccountCommand;
import com.hunantv.mbp.command.BossAssetCommand;
import com.hunantv.mbp.command.BossChartCommand;
import com.hunantv.mbp.command.BossContentCommand;
import com.hunantv.mbp.command.BossPurchaseCommand;
import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.BmcUpdateException;
import com.hunantv.mbp.commons.HttpResult;
import com.hunantv.mbp.commons.MbpHttpLog;
import com.hunantv.mbp.commons.ModuleConstants;
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
import com.hunantv.mbp.service.BossService;
import com.hunantv.mbp.utils.BmcHttpClient;

/**
 * @author XuYanbo
 *
 */
@Service
public class BossServiceImpl implements BossService {
	
	private static final Logger logger = LoggerFactory.getLogger(BossServiceImpl.class);

	/**
	 * 获取厂商列表
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_PURCHASE, type=BmcConstants.LOG_TYPE_QUERY, desc="Boss统计提取厂商列表")
	public List<Manufacturner> getAllManufacturners() throws BmcException {
		HttpResult<Manufacturner> result = BmcHttpClient.sendGetForListWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/platform/part-platform", null, Manufacturner.class);
		return result.getData();
	}
	
	/**
	 * 获取支付渠道列表
	 * @Author：XuYanbo
	 * @Date：2015年1月29日
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_PURCHASE, type=BmcConstants.LOG_TYPE_QUERY, desc="提取Boss支付渠道列表")
	public List<BossSimpleChannel> getAllChannels() throws BmcException {
		HttpResult<BossSimpleChannel> result = BmcHttpClient.sendGetForListWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/channel/all", null, BossSimpleChannel.class);
		return result.getData();
	}
	
	/**
	 * 获取VIP产品列表
	 * @Author：XuYanbo
	 * @Date：2015年1月12日
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_PURCHASE, type=BmcConstants.LOG_TYPE_QUERY, desc="提取Boss-VIP产品列表")
	public List<VipProduct> getAllVipProducts() throws BmcException {
		HttpResult<VipProduct> result = BmcHttpClient.sendGetForListWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/product/vip/all-valid-vip", null, VipProduct.class);
		return result.getData();
	}
	
	/**
	 * OTT-BOSS-账户管理
	 * @Author：XuYanbo
	 * @Date：2015年1月26日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_ACCOUNT, type=BmcConstants.LOG_TYPE_QUERY, desc="获取Boss账户管理列表")
	public HttpResult<BossAccount> searchAccounts(BossAccountCommand cmd, PageInfo<BossAccount> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/account/list");
		
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getAccountId())){
				params.append("&account_id=").append(cmd.getAccountId());
			}
			if(StringUtils.isNotBlank(cmd.getAccountName())){
				params.append("&account_passport=").append(cmd.getAccountName());
			}
			if(StringUtils.isNotBlank(cmd.getAccountType())){
				params.append("&vip_type=").append(cmd.getAccountType());
			}
		}
		
		HttpResult<BossAccount> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossAccount.class);
		return result;
	}
	
	/**
	 * OTT-BOSS-订购关系管理
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PURCHASE, type=BmcConstants.LOG_TYPE_QUERY, desc="获取Boss订购关系列表")
	public HttpResult<BossPurchase> searchPurchases(BossPurchaseCommand cmd, PageInfo<BossPurchase> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/invoice/");
		
		uri.append(BmcConstants.YES.equals(cmd.getOnlyValid()) ? "valid-list" : "list");
		
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getAccount())){
				params.append("&account_passport=").append(cmd.getAccount());
			}
			if(StringUtils.isNotBlank(cmd.getOrderId())){
				params.append("&order_uuid=").append(cmd.getOrderId());
			}
		}
		
		HttpResult<BossPurchase> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossPurchase.class);
		return result;
	}
	
	/**
	 * 频道源内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SOURCE, type=BmcConstants.LOG_TYPE_QUERY, desc="获取频道源内容列表")
	public HttpResult<BossSourceContent> searchChannelSources(BossContentCommand cmd, PageInfo<BossSourceContent> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/tv-file/list");

		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());

		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getCname())){
				params.append("&channel_name=").append(cmd.getCname());
			}
			if(StringUtils.isNotBlank(cmd.getCstatus())){
				params.append("&status=").append(cmd.getCstatus());
			}
		}

		HttpResult<BossSourceContent> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossSourceContent.class);
		return result;
	}
	
	/**
	 * 获取媒资文件一级分类
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="获取媒资文件一级分类列表")
	public JSONArray getAllAssetKind() throws BmcException {
		String uri = ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/get-asset-kind-all";
		return BmcHttpClient.sendGetForJsonArray(uri, null, false);
	}
	
	/**
	 * VIP内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="获取VIP内容列表")
	public HttpResult<BossVipContent> searchVipContents(BossContentCommand cmd, PageInfo<BossVipContent> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/");
		uri.append(BmcConstants.YES.equals(cmd.getOnlyValid()) ? "valid-vip-list-asset" : "list-asset");
		
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());

		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getCid())){
				params.append("&asset_id=").append(cmd.getCid());
			}
			if(StringUtils.isNotBlank(cmd.getCname())){
				params.append("&name=").append(cmd.getCname());
			}
			if(StringUtils.isNotBlank(cmd.getCtype())){
				params.append("&kind=").append(cmd.getCtype());
			}
			if(StringUtils.isNotBlank(cmd.getCharge())){
				params.append("&is_charge=").append(cmd.getCharge());
			}
			if(StringUtils.isNotBlank(cmd.getCstatus())){
				params.append("&status=").append(cmd.getCstatus());
			}
			if(StringUtils.isNotBlank(cmd.getStarttime())){
				params.append("&start_date=").append(cmd.getStarttime());
			}
			if(StringUtils.isNotBlank(cmd.getEndtime())){
				params.append("&end_date=").append(cmd.getEndtime());
			}
		}

		HttpResult<BossVipContent> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossVipContent.class);
		return result;
	}
	
	/**
	 * 查看媒资文件列表
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param cmd
	 * @param page
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="查看媒资文件列表")
	public HttpResult<BossAsset> searchAssetFiles(BossAssetCommand cmd, PageInfo<BossAsset> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/asset-file/list");

		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getId())){
				params.append("&id=").append(cmd.getId());
			}
			if(StringUtils.isNotBlank(cmd.getAsset_id())){
				params.append("&asset_id=").append(cmd.getAsset_id());
			}
			if(StringUtils.isNotBlank(cmd.getAsset_name())){
				params.append("&asset_name=").append(cmd.getAsset_name());
			}
			if(StringUtils.isNotBlank(cmd.getHashcode())){
				params.append("&hashcode=").append(cmd.getHashcode());
			}
		}

		HttpResult<BossAsset> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossAsset.class);
		return result;
	}
	
	/**
	 * 集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_QUERY, desc="获取集合内容列表")
	public HttpResult<BossPackContent> searchPackContents(PageInfo<BossPackContent> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/list-collection");

		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());

		HttpResult<BossPackContent> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossPackContent.class);
		return result;
	}
	
	/**
	 * 单点内容
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SINGLE, type=BmcConstants.LOG_TYPE_QUERY, desc="获取单点内容列表")
	public HttpResult<BossSingleContent> searchSingleContents(BossContentCommand cmd, PageInfo<BossSingleContent> page) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/list-asset");

		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getCname())){
				params.append("&name=").append(cmd.getCname());
			}
			if(StringUtils.isNotBlank(cmd.getCtype())){
				params.append("&kind=").append(cmd.getCtype());
			}
			if(StringUtils.isNotBlank(cmd.getSingle())){
				params.append("&is_single=").append(cmd.getSingle());
			}
			if(StringUtils.isNotBlank(cmd.getCharge())){
				params.append("&is_charge=").append(cmd.getCharge());
			}
			if(StringUtils.isNotBlank(cmd.getCstatus())){
				params.append("&status=").append(cmd.getCstatus());
			}
			if(StringUtils.isNotBlank(cmd.getStarttime())){
				params.append("&start_date=").append(cmd.getStarttime());
			}
			if(StringUtils.isNotBlank(cmd.getEndtime())){
				params.append("&end_date=").append(cmd.getEndtime());
			}
		}
		
		HttpResult<BossSingleContent> result = BmcHttpClient.sendGetForPageWithoutSignature(uri.toString(), params.toString(), BossSingleContent.class);
		return result;
	}
	
	/**
	 * VIP身份产品
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="获取VIP身份产品列表")
	public HttpResult<BossVipProduct> searchVipProducts(BossContentCommand cmd, PageInfo<BossVipProduct> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());

		if(cmd!=null && StringUtils.isNotBlank(cmd.getCname())){
			params.append("&name=").append(cmd.getCname());
		}

		HttpResult<BossVipProduct> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/product/vip/list", params.toString(), BossVipProduct.class);
		return result;
	}
	
	/**
	 * 媒资单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_SINGLE, type=BmcConstants.LOG_TYPE_QUERY, desc="获取媒资单点产品列表")
	public HttpResult<BossSingleProduct> searchSingleProducts(BossContentCommand cmd, PageInfo<BossSingleProduct> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());

		if(cmd!=null && StringUtils.isNotBlank(cmd.getCname())){
			params.append("&name=").append(cmd.getCname());
		}

		HttpResult<BossSingleProduct> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/product/single/list", 
				params.toString(), BossSingleProduct.class);
		return result;
	}
	
	/**
	 * 角标
	 * @Author：XuYanbo
	 * @Date：2015年2月11日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_TAG, type=BmcConstants.LOG_TYPE_QUERY, desc="获取角标列表")
	public HttpResult<BossSystemTag> searchSystemTags(PageInfo<BossSystemTag> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		HttpResult<BossSystemTag> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/mark/list", 
				params.toString(), BossSystemTag.class);
		return result;
	}
	
	/**
	 * 获取所有角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_TAG, type=BmcConstants.LOG_TYPE_QUERY, desc="获取所有角标列表")
	public HttpResult<BossSystemTag> getSystemTags() throws BmcException {
		return BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/mark/list", null, BossSystemTag.class);
	}
	
	/**
	 * VIP身份
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="获取VIP身份列表")
	public HttpResult<BossSystemVip> searchSystemVips(PageInfo<BossSystemVip> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		HttpResult<BossSystemVip> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/vip/list", 
				params.toString(), BossSystemVip.class);
		return result;
	}
	
	/**
	 * VIP身份列表
	 * @Author：XuYanbo
	 * @Date：2015年2月17日
	 * @return
	 */
	@Override
	@Cacheable(value="bossVipCache")
	public List<BossSystemVip> loadSystemVips() throws BmcException {
		logger.debug("***** Load System VIP List *****");
		HttpResult<BossSystemVip> vips = this.getSystemVips();
		return vips.getData();
	}
	
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="获取VIP身份列表")
	public HttpResult<BossSystemVip> getSystemVips() throws BmcException {
		return BmcHttpClient.sendGetForListWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/vip/all", null, BossSystemVip.class);
	}
	
	/**
	 * 获取平台
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_PLATFORM, type=BmcConstants.LOG_TYPE_QUERY, desc="获取平台列表")
	public HttpResult<BossSystemPlatform> searchSystemPlatforms(PageInfo<BossSystemPlatform> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		HttpResult<BossSystemPlatform> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/platform/list", 
				params.toString(), BossSystemPlatform.class);
		return result;
	}
	
	/**
	 * 获取清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param cmd
	 * @param page
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_BITRATE, type=BmcConstants.LOG_TYPE_QUERY, desc="获取清晰度列表")
	public HttpResult<BossSystemBitrate> searchSystemBitrates(PageInfo<BossSystemBitrate> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		HttpResult<BossSystemBitrate> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/quality/list", 
				params.toString(), BossSystemBitrate.class);
		return result;
	}
	
	/**
	 * 获取BOSS购买统计数据(包含VIP购买金额统计)
	 * @Author：XuYanbo
	 * @Date：2015年1月14日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_PURCHASE, type=BmcConstants.LOG_TYPE_QUERY, desc="Boss购买统计")
	public HttpResult<PaytimeStatis> getPayChartStatis(BossChartCommand cmd) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/audit-for-mbc/");
		uri.append(BmcConstants.PRODUCT_TYPE_VIP==cmd.getProductType()?"vip":"all");
		
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getChannelId())){
				params.append("&channel_ids=").append(cmd.getChannelId());
			}
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_date=").append(cmd.getStartDay());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_date=").append(cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getTimeType())){
				params.append("&by_date=").append(cmd.getTimeType());
			}
			if(StringUtils.isNotBlank(cmd.getProductId())){
				params.append(BmcConstants.PRODUCT_TYPE_VIP==cmd.getProductType() ? "&product_ids=" : "&cate_ids=").append(cmd.getProductId());
			}
			if(StringUtils.isNotBlank(cmd.getFactId())){
				params.append("&platform_ids=").append(cmd.getFactId());
			}
		}
		
		HttpResult<PaytimeStatis> result = BmcHttpClient.sendGetForListWithoutSignature(uri.toString(), params.toString(), PaytimeStatis.class);
		return result;
	}

	/**
	 * 获取BOSS充值统计数据
	 * @Author：XuYanbo
	 * @Date：2015年1月15日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_RECHARGE, type=BmcConstants.LOG_TYPE_QUERY, desc="Boss充值统计")
	public HttpResult<PaytimeStatis> getRechargeChartStatis(BossChartCommand cmd) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/audit-for-mbc/vip");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_date=").append(cmd.getStartDay());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_date=").append(cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getTimeType())){
				params.append("&by_date=").append(cmd.getTimeType());
			}
			if(StringUtils.isNotBlank(cmd.getProductId())){
				params.append("&product_ids=").append(cmd.getProductId());
			}
			if(StringUtils.isNotBlank(cmd.getChannelId())){
				params.append("&channel_ids=").append(cmd.getChannelId());
			}
		}
		
		HttpResult<PaytimeStatis> result = BmcHttpClient.sendGetForListWithoutSignature(uri.toString(), params.toString(), PaytimeStatis.class);
//		HttpResult<PaytimeStatis> result = BmcHttpClient.sendGetForListTest(PaytimeStatis.class);
		return result;
	}
	
	/**
	 * 获取BOSS-VIP购买人数统计数据
	 * @Author：XuYanbo
	 * @Date：2015年1月27日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_VIP_PAYCOUNT, type=BmcConstants.LOG_TYPE_QUERY, desc="Boss-VIP购买人数统计")
	public HttpResult<PaycountStatis> getVipPaycountChartStatis(BossChartCommand cmd) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/audit-for-mbc/vip-person-count");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_date=").append(cmd.getStartDay());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_date=").append(cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getTimeType())){
				params.append("&by_date=").append(cmd.getTimeType());
			}
		}
		
		HttpResult<PaycountStatis> result = BmcHttpClient.sendGetForListWithoutSignature(uri.toString(), params.toString(), PaycountStatis.class);
		return result;
	}
	
	/**
	 * 获取BOSS-付费用户统计数据
	 * @Author：XuYanbo
	 * @Date：2015年1月29日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_STATIS_PAIDUSER, type=BmcConstants.LOG_TYPE_QUERY, desc="Boss-付费用户统计")
	public HttpResult<PaidUserStatis> getPaidUserChartStatis(BossChartCommand cmd) throws BmcException {
		StringBuffer uri = new StringBuffer(ApplicationConfigs.BOSS_SERVER_URI + "/audit-for-mbc/person-count-by-product-cate");
		StringBuffer params = new StringBuffer();
		
		if(cmd!=null){
			if(StringUtils.isNotBlank(cmd.getStartDay())){
				params.append("&start_date=").append(cmd.getStartDay());
			}
			if(StringUtils.isNotBlank(cmd.getEndDay())){
				params.append("&end_date=").append(cmd.getEndDay());
			}
			if(StringUtils.isNotBlank(cmd.getTimeType())){
				params.append("&by_date=").append(cmd.getTimeType());
			}
			if(StringUtils.isNotBlank(cmd.getFactId())){
				params.append("&platform_ids=").append(cmd.getFactId());
			}
		}
		
		HttpResult<PaidUserStatis> result = BmcHttpClient.sendGetForListWithoutSignature(uri.toString(), params.toString(), PaidUserStatis.class);
		return result;
	}
	
	/**
	 * 查询用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_ACCOUNT, type=BmcConstants.LOG_TYPE_QUERY, desc="查询指定用户账户")
	public BossAccount getAccountById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/account/show?id=" + id, null, BossAccount.class, false);
	}
	
	/**
	 * 根据ID获取单点收费产品详情
	 * @Author：XuYanbo
	 * @Date：2015年3月5日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SINGLE, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取单点收费产品详情")
	public BossPackAssetContent getSingleContentById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/accord-asset-id-find-product-msg?asset_id=" + id, null, BossPackAssetContent.class, false);
	}
	
	/**
	 * 获取角标信息
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @return
	 * @throws BmcUpdateException 
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_TAG, type=BmcConstants.LOG_TYPE_QUERY, desc="系统配置-根据ID查询角标")
	public BossSystemTag getSystemTagById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/mark/show?id=" + id, null, BossSystemTag.class, false);
	}

	/**
	 * 获取VIP身份
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="系统配置-根据ID查询VIP身份")
	public BossSystemVip getSystemVipById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/vip/show?id=" + id, null, BossSystemVip.class, false);
	}

	/**
	 * 获取清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_BITRATE, type=BmcConstants.LOG_TYPE_QUERY, desc="系统配置-根据ID查询清晰度")
	public BossSystemBitrate getSystemBitrateById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/quality/show?id=" + id, null, BossSystemBitrate.class, false);
	}

	/**
	 * 获取所有清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_BITRATE, type=BmcConstants.LOG_TYPE_QUERY, desc="系统配置-获取所有清晰度")
	public HttpResult<BossSystemBitrate> getAllBitrates() throws BmcException {
		return BmcHttpClient.sendGetForListWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/quality/all", null, BossSystemBitrate.class);
	}

	/**
	 * 根据ID获取厂商信息
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_PLATFORM, type=BmcConstants.LOG_TYPE_QUERY, desc="系统配置-根据ID获取厂商信息")
	public BossSystemPlatform getSystemPlatformById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/platform/show?id=" + id, null, BossSystemPlatform.class, false);
	}

	/**
	 * 根据ID获取VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_VIP, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取VIP产品")
	public BossVipProduct getVipProductById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/product/vip/show?id=" + id, null, BossVipProduct.class, false);
	}

	/**
	 * 根据ID获取单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_SINGLE, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取单点产品")
	public BossSingleProduct getSingleProductById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/product/single/show?id=" + id, null, BossSingleProduct.class, false);
	}

	/**
	 * 根据ID获取集合产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取集合产品")
	public BossPackContent getPackContentById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/product/collection/show?id=" + id, null, BossPackContent.class, false);
	}
	
	/**
	 * 根据ID获取集合产品(包含媒资文件信息)
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取集合产品媒资文件")
	public BossPackAssetContent getPackAssetsContentById(String pid) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/product/collection/show?id=" + pid, null, BossPackAssetContent.class, false);
	}

	/**
	 * 根据ID获取支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_CHANNEL, type=BmcConstants.LOG_TYPE_QUERY, desc="根据ID获取支付渠道")
	public BossChannel getSystemChannelById(String id) throws BmcException {
		return BmcHttpClient.sendGetForObject(ApplicationConfigs.BOSS_SERVER_URI + "/channel/show?id=" + id, null, BossChannel.class, false);
	}

	/**
	 * 搜索支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param channelPage
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_CHANNEL, type=BmcConstants.LOG_TYPE_QUERY, desc="获取支付渠道列表")
	public HttpResult<BossChannel> searchSystemChannels(PageInfo<BossChannel> page) throws BmcException {
		StringBuffer params = new StringBuffer();
		params.append("&page=").append(page.getPage());
		params.append("&num_per_page=").append(page.getPageSize());
		HttpResult<BossChannel> result = BmcHttpClient.sendGetForPageWithoutSignature(ApplicationConfigs.BOSS_SERVER_URI + "/channel/list", params.toString(), BossChannel.class);
		return result;
	}
}
