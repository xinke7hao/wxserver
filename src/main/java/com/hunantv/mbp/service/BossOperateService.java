package com.hunantv.mbp.service;

import com.hunantv.mbp.command.BossPackCommand;
import com.hunantv.mbp.command.BossSingleCommand;
import com.hunantv.mbp.commons.BmcException;
import com.hunantv.mbp.dto.BossAccount;
import com.hunantv.mbp.dto.BossChannel;
import com.hunantv.mbp.dto.BossPackContent;
import com.hunantv.mbp.dto.BossSystemBitrate;
import com.hunantv.mbp.dto.BossSystemPlatform;
import com.hunantv.mbp.dto.BossSystemTag;
import com.hunantv.mbp.dto.BossSystemVip;
import com.hunantv.mbp.dto.BossVipProduct;

/**
 * 管理boss写操作接口
 * @author XuYanbo
 */
public interface BossOperateService {

	/**
	 * 删除用户账户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 */
	public String deleteAccount(String id) throws BmcException;

	/**
	 * 取消用户帐户服务
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param id
	 * @return
	 */
	public String deleteAccountInvoices(String id) throws BmcException;

	/**
	 * 编辑保存用户帐户
	 * @Author：XuYanbo
	 * @Date：2015年2月12日
	 * @param account
	 */
	public String saveAccount(BossAccount account) throws BmcException;

	/**
	 * 设置VIP收费
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param status
	 */
	public String setVip(String id, String status) throws BmcException;

	/**
	 * VIP内容-收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param min_vip_id
	 * @param mark_id
	 * @param preview
	 */
	public String saveVipPaySettings(String id, String min_vip_id, String mark_id, String preview) throws BmcException;

	/**
	 * VIP内容-按集收费
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param start_charges
	 * @param mark_id
	 * @throws BmcException
	 */
	public String saveVipEpisodeSettings(String id, String start_charges, String mark_id) throws BmcException;
	
	/**
	 * VIP内容-设置运营方式
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @param operation_modes
	 * @throws BmcException
	 */
	public String saveVipOperateCodeSettings(String id, String operation_modes) throws BmcException;
	
	/**
	 * 媒资文件手工同步
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param id
	 * @throws BmcException
	 */
	public String syncAssetFiles(String id) throws BmcException;

	/**
	 * 保存角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param tag
	 */
	public String saveSystemTag(BossSystemTag tag) throws BmcException;

	/**
	 * 删除角标
	 * @Author：XuYanbo
	 * @Date：2015年2月13日
	 * @param ids
	 * @return
	 */
	public String deleteTags(String ids) throws BmcException;

	/**
	 * 保存VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param vip
	 */
	public BossSystemVip saveSystemVip(BossSystemVip vip) throws BmcException;

	/**
	 * 删除VIP
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 */
	public String deleteVips(String ids) throws BmcException;

	/**
	 * 保存清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param bitrate
	 */
	public String saveSystemBitrate(BossSystemBitrate bitrate) throws BmcException;

	/**
	 * 删除清晰度
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 */
	public String deleteBitrates(String ids) throws BmcException;

	/**
	 * 保存厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param platform
	 */
	public String saveSystemPlatform(BossSystemPlatform platform) throws BmcException;

	/**删除厂商
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 */
	public String deletePlatforms(String ids) throws BmcException;

	/**
	 * 保存VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param vipForm
	 */
	public String saveVipProduct(BossVipProduct vipForm) throws BmcException;

	/**
	 * 删除VIP产品
	 * @Author：XuYanbo
	 * @Date：2015年2月15日
	 * @param ids
	 * @return
	 */
	public String deleteVipProducts(String ids) throws BmcException;

	/**
	 * 保存单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param singleForm
	 */
	public String saveSingleProduct(BossVipProduct singleForm) throws BmcException;

	/**
	 * 删除单点产品
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 */
	public String deleteSingleProducts(String ids) throws BmcException;

	/**
	 * 保存单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param cmd
	 */
	public String saveSinglePay(BossSingleCommand cmd) throws BmcException;

	/**
	 * 取消单点收费设置
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param id
	 */
	public String cancelSinglePay(String ids) throws BmcException;

	/**
	 * 保存集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param packForm
	 */
	public String savePackContent(BossPackContent packForm) throws BmcException;

	/**
	 * 删除集合内容
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param ids
	 * @return
	 */
	public String deletePackContents(String ids) throws BmcException;

	/**
	 * 集合产品导入集合包
	 * @Author：XuYanbo
	 * @Date：2015年2月25日
	 * @param pid
	 * @param ids
	 */
	public String savePackImports(String pid, String ids) throws BmcException;

	/**
	 * 删除支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param id
	 * @return
	 */
	public String deleteSystemChannel(String id) throws BmcException;

	/**
	 * 保存支付渠道
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param channel
	 */
	public String saveSystemChannel(BossChannel channel) throws BmcException;

	/**
	 * 保存集合模式设置(打包)
	 * @Author：XuYanbo
	 * @Date：2015年2月26日
	 * @param cmd
	 */
	public String savePackMode(BossPackCommand cmd) throws BmcException;
}
