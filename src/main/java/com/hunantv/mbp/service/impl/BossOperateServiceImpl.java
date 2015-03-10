/************************************************************************************
 * @File name   :      BossOperateServiceImpl.java
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
 * XuYanbo				1.0				Initial Version				2015年2月12日 下午3:06:30
 ************************************************************************************/
package com.hunantv.mbp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.hunantv.mbp.command.BossPackCommand;
import com.hunantv.mbp.command.BossSingleCommand;
import com.hunantv.mbp.commons.ApplicationConfigs;
import com.hunantv.mbp.commons.BmcConstants;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.commons.MbpHttpLog;
import com.hunantv.mbp.commons.ModuleConstants;
import com.hunantv.mbp.dto.BossAccount;
import com.hunantv.mbp.dto.BossChannel;
import com.hunantv.mbp.dto.BossPackContent;
import com.hunantv.mbp.dto.BossSystemBitrate;
import com.hunantv.mbp.dto.BossSystemPlatform;
import com.hunantv.mbp.dto.BossSystemTag;
import com.hunantv.mbp.dto.BossSystemVip;
import com.hunantv.mbp.dto.BossVipProduct;
import com.hunantv.mbp.service.BossOperateService;
import com.hunantv.mbp.utils.BmcHttpClient;

/**
 * Boss写操作服务
 * @author XuYanbo
 *
 */
@Service
public class BossOperateServiceImpl implements BossOperateService {

	/**
	 * 删除用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_ACCOUNT, type=BmcConstants.LOG_TYPE_DELETE, desc="删除用户账户")
	public String deleteAccount(String id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", id));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/account/delete", fields, false);
	}

	/**
	 * 用户账户取消服务
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_ACCOUNT, type=BmcConstants.LOG_TYPE_UPDATE, desc="用户账户取消服务")
	public String deleteAccountInvoices(String id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("account_id", id));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/account/delete-all-invoice", fields, false);
	}

	/**
	 * 编辑用户帐户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param account
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_ACCOUNT, type=BmcConstants.LOG_TYPE_UPDATE, desc="编辑用户帐户")
	public String saveAccount(BossAccount account) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		if(account!=null){
			if(StringUtils.isNotBlank(account.getId())){
				fields.add(new BasicNameValuePair("id", account.getId()));
			}
			if(StringUtils.isNotBlank(account.getStatus())){
				fields.add(new BasicNameValuePair("status", account.getStatus()));
			}
		}
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/account/update", fields, false);
	}

	/**
	 * 设置VIP收费
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param status
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SOURCE, type=BmcConstants.LOG_TYPE_UPDATE, desc="频道源-VIP收费设置")
	public String setVip(String id, String status) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("tv_file_ids", id));
		fields.add(new BasicNameValuePair("vip_id", status));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/tv-file/set-vip", fields, false);
	}

	/**
	 * VIP内容-收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param min_vip_id
	 * @param mark_id
	 * @param preview
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="VIP内容-收费设置")
	public String saveVipPaySettings(String id, String min_vip_id, String mark_id, String preview) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", id));
		fields.add(new BasicNameValuePair("mark_id", mark_id));
		fields.add(new BasicNameValuePair("min_vip_id", min_vip_id));
		fields.add(new BasicNameValuePair("preview", preview));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/batch-set-min-vip", fields, false);
	}
	
	/**
	 * VIP内容-按集收费
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param start_charges
	 * @param mark_id
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="VIP内容-按集收费设置")
	public String saveVipEpisodeSettings(String id, String start_charges, String mark_id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", id));
		fields.add(new BasicNameValuePair("mark_id", mark_id));
		fields.add(new BasicNameValuePair("start_charges", start_charges));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/set-start-charges", fields, false);
	}
	
	/**
	 * VIP内容-设置运营方式
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param operation_modes
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="VIP内容-运营方式设置")
	public String saveVipOperateCodeSettings(String id, String operation_modes) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", id));
		fields.add(new BasicNameValuePair("operation_mode", operation_modes));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/set-operation-mode", fields, false);
	}
	
	/**
	 * 媒资文件手工同步
	 * @throws BmcException 
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="VIP内容-手工同步")
	public String syncAssetFiles(String id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("asset_id", id));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/manually-sync-asset-file", fields, false);
	}

	/**
	 * 保存角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param tag
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_TAG, type=BmcConstants.LOG_TYPE_UPDATE, desc="系统配置-保存角标")
	public String saveSystemTag(BossSystemTag tag) throws BmcException {

		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(tag.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", tag.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(tag.getName())){
			fields.add(new BasicNameValuePair("name", tag.getName()));
		}
		if(StringUtils.isNotBlank(tag.getKind())){
			fields.add(new BasicNameValuePair("kind", tag.getKind()));
		}
		if(StringUtils.isNotBlank(tag.getDefault_flag())){
			fields.add(new BasicNameValuePair("default_flag", tag.getDefault_flag()));
		}
		
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/mark/" + method, fields, false);
	}

	/**
	 * 删除角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param ids
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_TAG, type=BmcConstants.LOG_TYPE_DELETE, desc="系统配置-删除角标")
	public String deleteTags(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/mark/delete", fields, false);
	}

	/**
	 * 系统配置-保存VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param vip
	 */
	@Override
	@CachePut(value="bossVipCache", key="#vip.id")
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="系统配置-保存VIP")
	public BossSystemVip saveSystemVip(BossSystemVip vip) throws BmcException {
		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(vip.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", vip.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(vip.getName())){
			fields.add(new BasicNameValuePair("name", vip.getName()));
		}
		if(StringUtils.isNotBlank(vip.getWeight())){
			fields.add(new BasicNameValuePair("weight", vip.getWeight()));
		}
		if(StringUtils.isNotBlank(vip.getQuality_ids())){
			fields.add(new BasicNameValuePair("quality_ids", vip.getQuality_ids()));
		}
		
		String res = BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/vip/" + method, fields, false);
		return vip;
	}

	/**
	 * 系统配置-删除VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * beforeInvocation是为了防止业务方法抛异常，导致没有对缓存内容进行处理
	 */
	@Override
	@CacheEvict(value="bossVipCache", allEntries=true, beforeInvocation=true)
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_VIP, type=BmcConstants.LOG_TYPE_DELETE, desc="系统配置-删除VIP")
	public String deleteVips(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/vip/delete", fields, false);
	}

	/**
	 * 系统配置-保存清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param bitrate
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_BITRATE, type=BmcConstants.LOG_TYPE_UPDATE, desc="系统配置-保存清晰度")
	public String saveSystemBitrate(BossSystemBitrate bitrate) throws BmcException {
		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(bitrate.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", bitrate.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(bitrate.getName())){
			fields.add(new BasicNameValuePair("name", bitrate.getName()));
		}
		
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/quality/" + method, fields, false);
	}

	/**
	 * 系统配置-删除清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_BITRATE, type=BmcConstants.LOG_TYPE_DELETE, desc="系统配置-删除清晰度")
	public String deleteBitrates(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/quality/delete", fields, false);
	}

	/**
	 * 系统配置-保存厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param platform
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_PLATFORM, type=BmcConstants.LOG_TYPE_UPDATE, desc="系统配置-保存厂商")
	public String saveSystemPlatform(BossSystemPlatform platform) throws BmcException {
		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(platform.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", platform.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(platform.getName())){
			fields.add(new BasicNameValuePair("name", platform.getName()));
		}
		if(StringUtils.isNotBlank(platform.getUuid())){
			fields.add(new BasicNameValuePair("uuid", platform.getUuid()));
		}
		
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/platform/" + method, fields, false);
	}

	/**
	 * 系统配置-删除厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_PLATFORM, type=BmcConstants.LOG_TYPE_DELETE, desc="系统配置-删除厂商")
	public String deletePlatforms(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/platform/delete", fields, false);
	}

	/**
	 * 保存VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param vipForm
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_VIP, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存VIP产品")
	public String saveVipProduct(BossVipProduct vipForm) throws BmcException {
		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(vipForm.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", vipForm.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(vipForm.getName())){
			fields.add(new BasicNameValuePair("name", vipForm.getName()));
		}
		if(StringUtils.isNotBlank(vipForm.getVip_id())){
			fields.add(new BasicNameValuePair("vip_id", vipForm.getVip_id()));
		}
		if(StringUtils.isNotBlank(vipForm.getKind())){
			fields.add(new BasicNameValuePair("kind", vipForm.getKind()));
		}
		if(StringUtils.isNotBlank(vipForm.getPrice())){
			fields.add(new BasicNameValuePair("price", vipForm.getPrice()));
		}
		if(StringUtils.isNotBlank(vipForm.getDays())){
			fields.add(new BasicNameValuePair("days", vipForm.getDays()));
		}
		if(StringUtils.isNotBlank(vipForm.getShow())){
			fields.add(new BasicNameValuePair("show", vipForm.getShow()));
		}

		//角标权重(页面表单没有)
		fields.add(new BasicNameValuePair("mark", "default"));
		fields.add(new BasicNameValuePair("mark_weight", "1000"));
		
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/vip/" + method, fields, false);
	}

	/**
	 * 删除VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_VIP, type=BmcConstants.LOG_TYPE_DELETE, desc="删除VIP产品")
	public String deleteVipProducts(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/vip/delete", fields, false);
	}
	
	/**
	 * 保存单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param singleForm
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_SINGLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="编辑单点产品")
	public String saveSingleProduct(BossVipProduct singleForm) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		fields.add(new BasicNameValuePair("id", singleForm.getId()));
		if(StringUtils.isNotBlank(singleForm.getName())){
			fields.add(new BasicNameValuePair("name", singleForm.getName()));
		}
		if(StringUtils.isNotBlank(singleForm.getDays())){
			fields.add(new BasicNameValuePair("days", singleForm.getDays()));
		}
		if(StringUtils.isNotBlank(singleForm.getYuan_price())){
			double yuan = Double.parseDouble(singleForm.getYuan_price());
			fields.add(new BasicNameValuePair("price", String.valueOf((int)(yuan*100))));
		}

		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/single/update", fields, false);
	}

	/**
	 * 删除单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_PRODUCT_SINGLE, type=BmcConstants.LOG_TYPE_DELETE, desc="删除单点产品")
	public String deleteSingleProducts(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/single/delete", fields, false);
	}

	/**
	 * 保存单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param cmd
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SINGLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存单点产品收费设置")
	public String saveSinglePay(BossSingleCommand cmd) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		
		String method = "generate";
		fields.add(new BasicNameValuePair("ids", cmd.getIds()));
		if(StringUtils.isNotBlank(cmd.getAsset_id())){
			method = "update";
			fields.add(new BasicNameValuePair("asset_id", cmd.getAsset_id()));
		}
		if(StringUtils.isNotBlank(cmd.getProduct_id())){
			fields.add(new BasicNameValuePair("product_id", cmd.getProduct_id()));
		}
		if(StringUtils.isNotBlank(cmd.getPrice())){
			double yuan = Double.parseDouble(cmd.getPrice());
			fields.add(new BasicNameValuePair("price", String.valueOf((int)(yuan*100))));
		}
		if(StringUtils.isNotBlank(cmd.getDays())){
			fields.add(new BasicNameValuePair("days", cmd.getDays()));
		}
		if(StringUtils.isNotBlank(cmd.getOpercodes())){
			fields.add(new BasicNameValuePair("operation_mode", cmd.getOpercodes()));
		}
		fields.add(new BasicNameValuePair("mark_id", cmd.getMarkid()));
		if(StringUtils.isNotBlank(cmd.getSync_sid())){
			fields.add(new BasicNameValuePair("sync_sid", cmd.getSync_sid()));
		}
		if(StringUtils.isNotBlank(cmd.getPreview())){
			fields.add(new BasicNameValuePair("preview", cmd.getPreview()));
		}

		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/" + method + "-single-product", fields, false);
	}

	/**
	 * 取消单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_SINGLE, type=BmcConstants.LOG_TYPE_UPDATE, desc="取消单点产品收费")
	public String cancelSinglePay(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset/batch-cancel-single-product", fields, false);
	}

	/**
	 * 保存集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param packForm
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存集合收费内容")
	public String savePackContent(BossPackContent packForm) throws BmcException {
		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(packForm.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", packForm.getId()));
		} else {
			method = "create";
		}

		if(StringUtils.isNotBlank(packForm.getName())){
			fields.add(new BasicNameValuePair("name", packForm.getName()));
		}
		if(StringUtils.isNotBlank(packForm.getYuan_price())){
			double yuan = Double.parseDouble(packForm.getYuan_price());
			fields.add(new BasicNameValuePair("price", String.valueOf((int)(yuan*100))));
		}
		if(StringUtils.isNotBlank(packForm.getDays())){
			fields.add(new BasicNameValuePair("days", packForm.getDays()));
		}
		fields.add(new BasicNameValuePair("sync_sid", packForm.getSync_sid()));

		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/collection/" + method, fields, false);
	}

	/**
	 * 删除集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_DELETE, desc="删除集合收费内容")
	public String deletePackContents(String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/product/collection/delete", fields, false);
	}

	/**
	 * 集合产品导入集合包
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param pid
	 * @param ids
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_UPDATE, desc="集合产品导入集合包")
	public String savePackImports(String pid, String ids) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("id", pid));
		fields.add(new BasicNameValuePair("ids", ids));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/import-collection-to-another", fields, false);
	}

	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_CHANNEL, type=BmcConstants.LOG_TYPE_DELETE, desc="删除支付渠道")
	public String deleteSystemChannel(String id) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		fields.add(new BasicNameValuePair("ids", id));
		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/channel/delete", fields, false);
	}

	/**
	 * 保存支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param channel
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_SYSTEM_CHANNEL, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存支付渠道")
	public String saveSystemChannel(BossChannel channel) throws BmcException {

		String method = "create";
		List<NameValuePair> fields = new ArrayList<NameValuePair>();

		if(StringUtils.isNotBlank(channel.getId())){
			method = "update";
			fields.add(new BasicNameValuePair("id", channel.getId()));
		}

		if(StringUtils.isNotBlank(channel.getName())){
			fields.add(new BasicNameValuePair("name", channel.getName()));
		}
		if(StringUtils.isNotBlank(channel.getUuid())){
			fields.add(new BasicNameValuePair("uuid", channel.getUuid()));
		}
		if(StringUtils.isNotBlank(channel.getFee_percent())){
			fields.add(new BasicNameValuePair("fee_percent", channel.getFee_percent()));
		}
		if(StringUtils.isNotBlank(channel.getFee_per_trans())){
			fields.add(new BasicNameValuePair("fee_per_trans", channel.getFee_per_trans()));
		}
		if(StringUtils.isNotBlank(channel.getDiscount())){
			fields.add(new BasicNameValuePair("discount", channel.getDiscount()));
		}

		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/channel/" + method, fields, false);
	}

	/**
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param cmd
	 * @return
	 * @throws BmcException
	 */
	@Override
	@MbpHttpLog(module=ModuleConstants.OTT_BOSS_CONTENT_PACK, type=BmcConstants.LOG_TYPE_UPDATE, desc="保存集合产品模式设置(打包)")
	public String savePackMode(BossPackCommand cmd) throws BmcException {
		List<NameValuePair> fields = new ArrayList<NameValuePair>();
		
		fields.add(new BasicNameValuePair("id", cmd.getPid()));
		fields.add(new BasicNameValuePair("asset_ids", cmd.getAids()));
		fields.add(new BasicNameValuePair("operation_mode", cmd.getOpercodes()));
		fields.add(new BasicNameValuePair("preview", cmd.getPreview()));
		fields.add(new BasicNameValuePair("mark_id", cmd.getMarkid()));

		return BmcHttpClient.sendPost(ApplicationConfigs.BOSS_SERVER_URI + "/asset-functional/import-asset-to-collection", fields, false);
	}
}
