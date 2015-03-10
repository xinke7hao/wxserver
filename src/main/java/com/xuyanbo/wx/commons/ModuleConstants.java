package com.xuyanbo.wx.commons;

//Module ID Code
public interface ModuleConstants {
	
	/*
	10	用户管理
	46	菜单管理
	31	数据角色管理
	32	部门管理
	22	系统日志
	12	功能管理
	11	功能角色管理
	59	登录日志
	*/
	int SYSTEM_DEPARTMENT = 32;
	int SYSTEM_USER = 10;
	int SYSTEM_ROLE = 11;
	int SYSTEM_GROUP = 31;
	int SYSTEM_RIGHT = 12;
	int SYSTEM_LOG = 22;
	int SYSTEM_MENU = 46;
	int SYSTEM_LOGIN = 59;

	/*
	35	手机号查询
	21	支付渠道配置
	13	流水查询
	23	业务平台配置
	34	9588异步通知
	33	黑名单配置
	41	苹果支付查询
	57	一键补单
	*/
	int PAYMENT_ORDER = 13;
	int PAYMENT_CHANNEL = 21;
	int PAYMENT_PLATFORM = 23;
	int PAYMENT_BLACKLIST = 33;
	int PAYMENT_SMS_NOTIFY = 34;
	int PAYMENT_MOBILE_QUERY = 35;
	int PAYMENT_APPLE_QUERY = 41;
	int PAYMENT_ORDER_FIX = 57;
	
	/*
	30	基础对账
	45	财务报表
	28	流水监控
	18	流水统计
	24	业务平台统计
	25	支付渠道统计
	26	图表统计
	27	API监控
	42	用户分布
	*/
	int STATIS_PAYMENT_PLATFORM = 24;
	int STATIS_PAYMENT_CHANNEL = 25;
	int STATIS_PAYMENT_RECON = 30;
	int STATIS_PAYMENT_ORDER = 18;
	int STATIS_PAYMENT_CHART = 26;
	int STATIS_MONITOR_PAYAPI = 27;
	int STATIS_MONITOR_PAYORDER = 28;
	int STATIS_PAYMENT_USER = 42;
	int STATIS_FINANCIAL_RECON = 45;
	
	/*
	36	用户账户管理
	37	订购关系管理
	47	频道源收费
	48	VIP收费内容
	49	集合收费内容
	50	单点收费内容
	51	VIP产品配置
	52	单点产品配置
	53	角标配置
	54	VIP配置
	55	厂商配置
	56	清晰度配置
	58	支付渠道配置
	*/
	int OTT_BOSS_ACCOUNT = 36;
	int OTT_BOSS_PURCHASE = 37;
	int OTT_BOSS_CONTENT_SOURCE = 47;
	int OTT_BOSS_CONTENT_VIP = 48;
	int OTT_BOSS_CONTENT_PACK = 49;
	int OTT_BOSS_CONTENT_SINGLE = 50;
	int OTT_BOSS_PRODUCT_VIP = 51;
	int OTT_BOSS_PRODUCT_SINGLE = 52;
	int OTT_BOSS_SYSTEM_TAG = 53;
	int OTT_BOSS_SYSTEM_VIP = 54;
	int OTT_BOSS_SYSTEM_PLATFORM = 55;
	int OTT_BOSS_SYSTEM_BITRATE = 56;
	int OTT_BOSS_SYSTEM_CHANNEL = 58;
	
	/*
	38	购买金额统计
	39	充值金额统计
	40	用户统计
	43	VIP购买金额统计
	44	VIP购买人数统计
	*/
	int OTT_BOSS_STATIS_PURCHASE = 38;
	int OTT_BOSS_STATIS_VIP_PURCHASE = 38;
	int OTT_BOSS_STATIS_RECHARGE = 39;
	int OTT_BOSS_STATIS_PAIDUSER = 40;
	int OTT_BOSS_STATIS_VIP_PAYCOUNT = 44;
}
