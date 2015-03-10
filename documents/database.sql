/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50704
Source Host           : localhost:3306
Source Database       : webserver

Target Server Type    : MYSQL
Target Server Version : 50704
File Encoding         : 65001

Date: 2015-03-10 16:04:57
*/
create database webserver DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tl_login_log
-- ----------------------------
DROP TABLE IF EXISTS `tl_login_log`;
CREATE TABLE `tl_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_time` datetime DEFAULT NULL,
  `login_user` varchar(20) DEFAULT NULL,
  `login_ip` varchar(20) DEFAULT NULL,
  `login_desc` varchar(500) DEFAULT NULL,
  `login_status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tl_login_log
-- ----------------------------
INSERT INTO `tl_login_log` VALUES ('78', '2015-03-10 15:43:08', 'admin', '0:0:0:0:0:0:0:1', '登录成功', '0');
INSERT INTO `tl_login_log` VALUES ('79', '2015-03-10 15:57:58', 'admin', '127.0.0.1', '登录成功', '0');

-- ----------------------------
-- Table structure for tl_system_log
-- ----------------------------
DROP TABLE IF EXISTS `tl_system_log`;
CREATE TABLE `tl_system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` int(11) DEFAULT NULL,
  `log_type` varchar(2) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `log_user` varchar(20) DEFAULT NULL,
  `log_ip` varchar(20) DEFAULT NULL,
  `log_desc` varchar(500) DEFAULT NULL,
  `log_status` varchar(2) DEFAULT NULL,
  `log_style` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=596 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tl_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for tm_department
-- ----------------------------
DROP TABLE IF EXISTS `tm_department`;
CREATE TABLE `tm_department` (
  `depart_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `depart_code` varchar(20) DEFAULT NULL,
  `depart_name` varchar(50) DEFAULT NULL,
  `depart_level` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_department
-- ----------------------------
INSERT INTO `tm_department` VALUES ('1', null, 'test', '开发部门', null, '2014-12-12 13:27:05', '2014-12-12 13:27:05');
INSERT INTO `tm_department` VALUES ('3', null, 'TEST', '测试部门', null, '2014-12-19 16:42:32', '2014-12-19 16:42:32');
INSERT INTO `tm_department` VALUES ('4', null, 'D03', '行政部门', null, '2014-12-19 16:42:43', '2014-12-19 16:42:43');

-- ----------------------------
-- Table structure for tm_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tm_dictionary`;
CREATE TABLE `tm_dictionary` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_code` varchar(50) DEFAULT NULL,
  `dict_value` varchar(100) DEFAULT NULL,
  `dict_desc` varchar(100) DEFAULT NULL,
  `is_display` varchar(2) DEFAULT NULL,
  `dict_group` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_dictionary
-- ----------------------------
INSERT INTO `tm_dictionary` VALUES ('1', 'PAY_STATUS', '0', '未支付', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('2', 'PAY_STATUS', '1', '支付中', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('3', 'PAY_STATUS', '2', '支付同步返回', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('4', 'PAY_STATUS', '3', '支付异步返回', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('5', 'PAY_STATUS', '8', '交易关闭', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('6', 'PAY_RECO_STATUS', '0', '未对账', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('7', 'PAY_RECO_STATUS', '1', '小时对账成功', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('8', 'PAY_RECO_STATUS', '2', '小时对账失败', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('9', 'PAY_RECO_STATUS', '3', '天对账成功', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('10', 'PAY_RECO_STATUS', '4', '天对账失败', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('11', 'PAY_BUSINESS_RESULT', '1', '业务方处理成功', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('12', 'PAY_BUSINESS_RESULT', '2', '业务方处理失败', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('13', 'PAY_CHANNEL_STATUS', '0', '不可用', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('14', 'PAY_CHANNEL_STATUS', '1', '可用', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('15', 'PAY_STATIS_ITEM', 'total', '订单数', 'N', 'A');
INSERT INTO `tm_dictionary` VALUES ('16', 'PAY_STATIS_ITEM', 'finish', '结单数', 'Y', 'A');
INSERT INTO `tm_dictionary` VALUES ('17', 'PAY_STATIS_ITEM', 'amount', '支付金额', 'Y', 'C');
INSERT INTO `tm_dictionary` VALUES ('18', 'PAY_STATIS_ITEM', 'price', '客单价', 'N', 'C');
INSERT INTO `tm_dictionary` VALUES ('19', 'PAY_STATIS_ITEM', 'succ', '成功率', 'N', 'B');
INSERT INTO `tm_dictionary` VALUES ('20', 'PAY_STATIS_ITEM', 'single', '掉单率', 'N', 'B');

-- ----------------------------
-- Table structure for tm_group
-- ----------------------------
DROP TABLE IF EXISTS `tm_group`;
CREATE TABLE `tm_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `group_status` varchar(2) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_group
-- ----------------------------

-- ----------------------------
-- Table structure for tm_menu
-- ----------------------------
DROP TABLE IF EXISTS `tm_menu`;
CREATE TABLE `tm_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `menu_pid` int(11) DEFAULT NULL,
  `menu_code` varchar(50) NOT NULL,
  `menu_name` varchar(50) NOT NULL,
  `menu_url` varchar(200) NOT NULL,
  `menu_status` varchar(2) NOT NULL,
  `menu_index` int(11) NOT NULL,
  `menu_desc` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_menu
-- ----------------------------
INSERT INTO `tm_menu` VALUES ('10', '7', null, 'user', '用户管理', 'user', 'Y', '1', '用户管理');
INSERT INTO `tm_menu` VALUES ('11', '7', null, 'role', '功能角色管理', 'role', 'Y', '4', null);
INSERT INTO `tm_menu` VALUES ('12', '7', null, 'right', '功能管理', 'right', 'Y', '2', '功能管理');
INSERT INTO `tm_menu` VALUES ('22', '7', null, 'syslog', '系统日志', 'admin/log', 'Y', '6', null);
INSERT INTO `tm_menu` VALUES ('31', '7', null, 'group', '数据角色管理', 'group', 'Y', '5', null);
INSERT INTO `tm_menu` VALUES ('32', '7', null, 'depart', '部门管理', 'depart', 'Y', '3', null);
INSERT INTO `tm_menu` VALUES ('46', '7', null, 'menu', '菜单管理', 'menu', 'Y', '2', '支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水\r\n支付流水查询，可以查询每日订单流水');
INSERT INTO `tm_menu` VALUES ('59', '7', null, 'login', '登录日志', 'admin/login', 'Y', '8', '登录日志');

-- ----------------------------
-- Table structure for tm_module
-- ----------------------------
DROP TABLE IF EXISTS `tm_module`;
CREATE TABLE `tm_module` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_code` varchar(50) NOT NULL,
  `module_name` varchar(50) NOT NULL,
  `module_index` int(11) NOT NULL,
  `module_status` varchar(2) NOT NULL,
  `icon` varchar(50) NOT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_module
-- ----------------------------
INSERT INTO `tm_module` VALUES ('7', 'm1', '系统管理', '5', 'Y', 'system.png');

-- ----------------------------
-- Table structure for tm_right
-- ----------------------------
DROP TABLE IF EXISTS `tm_right`;
CREATE TABLE `tm_right` (
  `right_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `right_name` varchar(100) NOT NULL,
  `right_method` varchar(100) NOT NULL,
  `right_status` varchar(2) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`right_id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_right
-- ----------------------------
INSERT INTO `tm_right` VALUES ('23', '7', '10', '查询用户', 'list,search,export', 'Y', '2014-11-24 09:44:02', '2014-11-24 09:44:02');
INSERT INTO `tm_right` VALUES ('24', '7', '10', '编辑用户', 'edit,save', 'Y', '2014-11-24 09:44:02', '2015-01-29 15:50:07');
INSERT INTO `tm_right` VALUES ('25', '7', '10', '删除用户', 'delete', 'Y', '2014-11-24 09:44:02', '2014-11-24 09:44:02');
INSERT INTO `tm_right` VALUES ('26', '7', '11', '查询角色', 'list,search', 'Y', '2014-11-24 09:44:02', '2014-11-24 09:44:02');
INSERT INTO `tm_right` VALUES ('27', '7', '11', '编辑角色', 'edit,save', 'Y', '2014-11-24 09:44:02', '2015-01-29 16:00:01');
INSERT INTO `tm_right` VALUES ('28', '7', '11', '删除角色', 'delete', 'Y', '2014-11-24 09:44:02', '2014-11-24 09:44:02');
INSERT INTO `tm_right` VALUES ('29', '7', '12', '查询功能', 'list,search,loadchild', 'Y', '2014-11-24 09:44:02', '2015-01-30 11:24:41');
INSERT INTO `tm_right` VALUES ('30', '7', '12', '编辑功能', 'edit,save', 'Y', '2014-11-24 09:44:02', '2015-01-29 15:59:04');
INSERT INTO `tm_right` VALUES ('31', '7', '12', '删除功能', 'delete', 'Y', '2014-11-24 09:44:02', '2014-11-24 09:44:02');
INSERT INTO `tm_right` VALUES ('57', '7', '10', '设置用户角色', 'rolelist,roleconfig', 'Y', '2014-12-05 10:52:53', '2015-01-30 11:33:33');
INSERT INTO `tm_right` VALUES ('58', '7', '11', '设置角色功能', 'config,doconfig', 'Y', '2014-12-05 10:55:38', '2015-01-30 11:30:11');
INSERT INTO `tm_right` VALUES ('72', '7', '22', '查询日志', 'list,search', 'Y', '2014-12-05 11:08:57', '2014-12-05 11:08:57');
INSERT INTO `tm_right` VALUES ('73', '7', '22', '删除日志', 'delete', 'Y', '2014-12-05 11:09:11', '2014-12-05 15:56:04');
INSERT INTO `tm_right` VALUES ('74', '7', '31', '查询数据角色', 'list,search', 'Y', '2014-12-09 16:56:07', '2014-12-09 16:56:07');
INSERT INTO `tm_right` VALUES ('75', '7', '10', '设置数据角色', 'grouplist,groupconfig', 'Y', '2014-12-10 14:33:39', '2015-01-30 11:33:19');
INSERT INTO `tm_right` VALUES ('76', '7', '32', '部门管理', 'list,search', 'Y', '2014-12-12 13:13:53', '2014-12-12 13:13:53');
INSERT INTO `tm_right` VALUES ('77', '7', '32', '添加部门', 'create,save', 'Y', '2014-12-12 13:22:56', '2015-01-29 16:02:14');
INSERT INTO `tm_right` VALUES ('78', '7', '32', '编辑部门', 'edit,save', 'Y', '2014-12-12 13:23:11', '2015-01-29 16:02:19');
INSERT INTO `tm_right` VALUES ('79', '7', '32', '删除部门', 'delete', 'Y', '2014-12-12 13:23:23', '2014-12-12 13:23:23');
INSERT INTO `tm_right` VALUES ('80', '7', '31', '添加数据角色', 'create,save', 'Y', '2014-12-12 17:56:20', '2015-01-29 16:00:49');
INSERT INTO `tm_right` VALUES ('81', '7', '31', '修改数据角色', 'edit,save', 'Y', '2014-12-12 17:56:34', '2015-01-29 16:00:58');
INSERT INTO `tm_right` VALUES ('82', '7', '31', '删除数据角色', 'delete', 'Y', '2014-12-12 17:56:49', '2014-12-12 17:56:49');
INSERT INTO `tm_right` VALUES ('83', '7', '31', '设置数据', 'config,doconfig', 'Y', '2014-12-12 17:57:38', '2015-01-30 11:29:33');
INSERT INTO `tm_right` VALUES ('95', '7', '10', '添加用户', 'create,checkcode,genpass,save', 'Y', '2015-01-29 15:50:21', '2015-01-30 11:28:12');
INSERT INTO `tm_right` VALUES ('96', '7', '12', '添加功能', 'create,save', 'Y', '2015-01-29 15:59:16', '2015-01-29 15:59:16');
INSERT INTO `tm_right` VALUES ('97', '7', '11', '添加角色', 'create,save', 'Y', '2015-01-29 16:00:18', '2015-01-29 16:00:18');

-- ----------------------------
-- Table structure for tm_role
-- ----------------------------
DROP TABLE IF EXISTS `tm_role`;
CREATE TABLE `tm_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_status` varchar(2) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_role
-- ----------------------------

-- ----------------------------
-- Table structure for tm_system_data
-- ----------------------------
DROP TABLE IF EXISTS `tm_system_data`;
CREATE TABLE `tm_system_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_type` varchar(2) NOT NULL,
  `data_id` int(11) DEFAULT NULL,
  `data_name` varchar(100) NOT NULL,
  `data_status` varchar(2) NOT NULL,
  `data_code` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_system_data
-- ----------------------------
INSERT INTO `tm_system_data` VALUES ('3', 'P', '110', 'OTT-AAA', 'Y', null);
INSERT INTO `tm_system_data` VALUES ('8', 'P', '103', 'OTT-银鲨', 'Y', null);
INSERT INTO `tm_system_data` VALUES ('13', 'P', '2', '充值中心', 'N', null);
INSERT INTO `tm_system_data` VALUES ('16', 'C', '39', '虚拟网关支付', 'Y', 'DB8857B42066');
INSERT INTO `tm_system_data` VALUES ('17', 'C', '38', '微信支付', 'Y', '837D14E0B693');
INSERT INTO `tm_system_data` VALUES ('18', 'C', '31', '支付宝扫码支付（ITV）', 'Y', 'A8BAE67DF25F');
INSERT INTO `tm_system_data` VALUES ('19', 'C', '28', '支付宝钱包(ITV)', 'Y', '9052E7558869');
INSERT INTO `tm_system_data` VALUES ('20', 'C', '27', '支付宝WAP支付(ITV)', 'Y', '9D5A10B33BCD');
INSERT INTO `tm_system_data` VALUES ('21', 'C', '26', '支付宝钱包', 'N', 'A38DEE5FEF7A');
INSERT INTO `tm_system_data` VALUES ('22', 'C', '25', '支付宝WAP支付', 'N', '6BF25101AB02');
INSERT INTO `tm_system_data` VALUES ('23', 'C', '24', '支付宝浏览器安全支付(ITV)', 'N', '0ABD31263150');
INSERT INTO `tm_system_data` VALUES ('24', 'C', '19', '支付宝网银', 'Y', '2EFDEED9BFD8');
INSERT INTO `tm_system_data` VALUES ('25', 'C', '14', '神州付（手机充值卡）', 'Y', '3FC20DAA766A');
INSERT INTO `tm_system_data` VALUES ('26', 'C', '12', '快钱网银', 'Y', '9A14CE3948FF');
INSERT INTO `tm_system_data` VALUES ('27', 'C', '10', '支付宝', 'Y', '950B79E3C969');
INSERT INTO `tm_system_data` VALUES ('29', 'C', '40', '9588短信代付', 'Y', '18B43C6A536A');
INSERT INTO `tm_system_data` VALUES ('32', 'P', '1018', 'MPP', 'Y', null);
INSERT INTO `tm_system_data` VALUES ('33', 'C', '99', '苹果支付(IAP)', 'Y', '1F3870BE274F');
INSERT INTO `tm_system_data` VALUES ('34', 'C', '100', '芒果币支付', 'N', 'FFFFFFFFFFFF');
INSERT INTO `tm_system_data` VALUES ('35', 'C', '101', '银鲨同步专用', 'N', 'EEEEEEEEEEEEE');
INSERT INTO `tm_system_data` VALUES ('36', 'C', '102', '易联语音支付', 'Y', '5E44CC65144E');
INSERT INTO `tm_system_data` VALUES ('37', 'C', '103', '虚拟二维码支付网关', 'N', '3ABDUI896A12');

-- ----------------------------
-- Table structure for tm_user
-- ----------------------------
DROP TABLE IF EXISTS `tm_user`;
CREATE TABLE `tm_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(20) NOT NULL,
  `user_name` varchar(60) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `user_status` varchar(2) NOT NULL,
  `user_pass` varchar(100) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `depart_id` int(11) NOT NULL,
  `is_admin` varchar(2) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tm_user
-- ----------------------------
INSERT INTO `tm_user` VALUES ('1', 'admin', '管理员', 'xuyanbo@e.hunantv.com', 'Y', '202cb962ac59075b964b07152d234b70', '2014-11-24 09:43:51', '2014-12-05 13:22:42', null, '1', 'Y', '1');

-- ----------------------------
-- Table structure for tr_group_data
-- ----------------------------
DROP TABLE IF EXISTS `tr_group_data`;
CREATE TABLE `tr_group_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) NOT NULL,
  `data_type` varchar(2) DEFAULT NULL,
  `data_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_group_data
-- ----------------------------

-- ----------------------------
-- Table structure for tr_role_right
-- ----------------------------
DROP TABLE IF EXISTS `tr_role_right`;
CREATE TABLE `tr_role_right` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `right_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=484 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_role_right
-- ----------------------------

-- ----------------------------
-- Table structure for tr_user_group
-- ----------------------------
DROP TABLE IF EXISTS `tr_user_group`;
CREATE TABLE `tr_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for tr_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tr_user_role`;
CREATE TABLE `tr_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tr_user_role
-- ----------------------------
