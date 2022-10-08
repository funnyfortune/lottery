/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : draw-server

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 08/10/2022 17:06:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for draw_action
-- ----------------------------
DROP TABLE IF EXISTS `draw_action`;
CREATE TABLE `draw_action`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theme_id` bigint(11) NULL DEFAULT 0 COMMENT '主题Id',
  `gift_id` bigint(11) NULL DEFAULT 0 COMMENT '主题奖项Id',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽奖行为表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for draw_customer
-- ----------------------------
DROP TABLE IF EXISTS `draw_customer`;
CREATE TABLE `draw_customer`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '公司',
  `theme_id` bigint(11) NULL DEFAULT 0 COMMENT '主题id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '名称',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机',
  `probability` double(10, 3) NULL DEFAULT 0.000 COMMENT '概率',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0=正常,1=停用）',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '创建者id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作者id',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1758 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽奖客户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draw_customer
-- ----------------------------
INSERT INTO `draw_customer` VALUES (1000, '222', 14, '222', '333', 0.000, '0', '', 1, 1, 1, 1615787029900, 1665219245132);
INSERT INTO `draw_customer` VALUES (1001, '231232', 14, '213', '13660781279', 0.000, '0', '', 1, 0, 1, 0, 1665219242744);
INSERT INTO `draw_customer` VALUES (1719, 'A', 14, 'Aa', '18888888888', 0.000, '0', '', 1, 1, 1, 1615962574939, 1665219247815);
INSERT INTO `draw_customer` VALUES (1720, 'B', 14, 'Bb', '18777777777', 0.000, '0', '', 1, 1, 1, 1615962592006, 1665219250090);
INSERT INTO `draw_customer` VALUES (1721, 'C', 14, 'Cc', '18666666666', 0.000, '0', '', 1, 1, 1, 1615962607215, 1665219252261);
INSERT INTO `draw_customer` VALUES (1722, '1', 21, '1', '1', 0.000, '0', '', 0, 1, 1, 1615963842556, 1665219261689);
INSERT INTO `draw_customer` VALUES (1723, '2', 21, '2', '2', 0.000, '0', '', 0, 1, 1, 1615963846832, 1665219294365);
INSERT INTO `draw_customer` VALUES (1724, '3', 14, '3', '3', 0.000, '0', '', 1, 1, 1, 1615963850630, 1665219232390);
INSERT INTO `draw_customer` VALUES (1725, '4', 14, '4', '4', 0.000, '0', '', 1, 1, 1, 1615963856052, 1665219230067);
INSERT INTO `draw_customer` VALUES (1726, '5', 14, '5', '5', 0.000, '0', '', 1, 1, 1, 1615963860659, 1665219234551);
INSERT INTO `draw_customer` VALUES (1727, '6', 21, '6', '6', 0.000, '0', '', 0, 1, 1, 1615963866486, 1665219290287);
INSERT INTO `draw_customer` VALUES (1728, '7', 21, '7', '7', 0.000, '0', '', 0, 1, 1, 1615963871805, 1665219286395);
INSERT INTO `draw_customer` VALUES (1729, '8', 21, '8', '8', 0.000, '0', '', 0, 1, 1, 1615963877133, 1665219282352);
INSERT INTO `draw_customer` VALUES (1730, '9', 21, '9', '9', 0.000, '0', '', 0, 1, 1, 1615963908282, 1665219278029);
INSERT INTO `draw_customer` VALUES (1731, '11', 21, '11', '11', 0.000, '0', '', 0, 1, 1, 1615963914688, 1665219266542);
INSERT INTO `draw_customer` VALUES (1732, '123', 21, '123', '1234123', 0.000, '0', '', 0, 1, 1, 1615972241238, 1665219227143);
INSERT INTO `draw_customer` VALUES (1733, '321', 21, '321', '12312412312', 0.000, '0', '', 0, 1, 1, 1615972247295, 1665219222569);
INSERT INTO `draw_customer` VALUES (1734, '1234', 21, '12341232', '12431412312', 0.000, '0', '', 0, 1, 1, 1615972271749, 1665219273095);

-- ----------------------------
-- Table structure for draw_gift
-- ----------------------------
DROP TABLE IF EXISTS `draw_gift`;
CREATE TABLE `draw_gift`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theme_id` bigint(11) NULL DEFAULT 0 COMMENT '主题Id',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '名称',
  `num` int(11) NULL DEFAULT 0 COMMENT '数量',
  `completed` tinyint(1) NULL DEFAULT 0 COMMENT '0未完成，1完成',
  `complete_time` bigint(11) NULL DEFAULT 0 COMMENT '时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '创建者id',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1578665635489435650 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽奖礼品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draw_gift
-- ----------------------------
INSERT INTO `draw_gift` VALUES (1578665635489435649, 21, '一等奖', 2, 0, 0, 1, 1665218192346);

-- ----------------------------
-- Table structure for draw_result
-- ----------------------------
DROP TABLE IF EXISTS `draw_result`;
CREATE TABLE `draw_result`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theme_id` bigint(11) NOT NULL COMMENT '主题ID',
  `gift_id` bigint(11) NOT NULL COMMENT '奖项ID',
  `customer_id` bigint(11) NOT NULL COMMENT '客户表Id',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1578659364078014467 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽奖中奖表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for draw_theme
-- ----------------------------
DROP TABLE IF EXISTS `draw_theme`;
CREATE TABLE `draw_theme`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '活动名称',
  `draw_type` tinyint(1) NULL DEFAULT 0 COMMENT '0随机，1概率',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '创建者id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作者id',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抽奖主题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draw_theme
-- ----------------------------
INSERT INTO `draw_theme` VALUES (1, '2222', 0, 1, 1, 1, 1615777479119, 1615961948767);
INSERT INTO `draw_theme` VALUES (3, '优先级抽取', 1, 1, 1, 1, 1615884165656, 1615961993415);
INSERT INTO `draw_theme` VALUES (5, '222', 0, 1, 1, 1, 1615941324848, 1615942910445);
INSERT INTO `draw_theme` VALUES (7, '抽奖测试', 0, 1, 1, 1, 1615942919342, 1615962533065);
INSERT INTO `draw_theme` VALUES (8, 'ces', 0, 1, 1, 1, 1615943099104, 1615943581800);
INSERT INTO `draw_theme` VALUES (9, '223', 0, 1, 1, 1, 1615943228664, 1615943580033);
INSERT INTO `draw_theme` VALUES (12, '2222', 0, 1, 1, 1, 1615944340388, 1615944350172);
INSERT INTO `draw_theme` VALUES (13, '测试', 0, 1, 1, 1, 1615944794697, 1615962533065);
INSERT INTO `draw_theme` VALUES (16, 'test1', 0, 0, 1, 1, 1616036166325, 1616036166325);
INSERT INTO `draw_theme` VALUES (17, '44111', 0, 1, 1, 1, 1616053105289, 1616053423843);
INSERT INTO `draw_theme` VALUES (18, '444222', 0, 1, 1, 1, 1616053428376, 1616053527193);
INSERT INTO `draw_theme` VALUES (19, 'eeee', 0, 1, 1, 1, 1616053442974, 1616053524822);
INSERT INTO `draw_theme` VALUES (20, '222', 0, 1, 1, 1, 1616053533662, 1616053859873);
INSERT INTO `draw_theme` VALUES (21, '测试主题', 0, 0, 1, 1, 1616391198229, 1616391198229);
INSERT INTO `draw_theme` VALUES (23, '1234', 0, 0, 1, 1, 1616563166761, 1616563166761);
INSERT INTO `draw_theme` VALUES (25, '测试02', 0, 0, 1, 1, 1660895869439, 1660895869439);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '用户id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `value_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '参数类型。0文本，1json',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow', 0, 0, 0, 0, 0, '0');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', 'abc123', 'Y', '初始化密码 abc123', 0, 1605680610309, 0, 1, 0, '0');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', '深色主题theme-dark，浅色主题theme-light', 0, 0, 0, 0, 0, '0');
INSERT INTO `sys_config` VALUES (100, '客户信息OA地址', 'url', 'http://localhost:8080/ekp/datasocure', 'Y', NULL, 1590058553714, 1590551712757, 1, 1, 1, '0');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '祖级列表',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '联系电话',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '邮箱',
  `dept_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader_id` bigint(11) NULL DEFAULT 0 COMMENT '负责人',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否有效',
  `memo` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '组织表id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  `parent_id` bigint(11) NULL DEFAULT 0 COMMENT '所在部门',
  `name_pinyin` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '拼音名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1492 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1491, '0', NULL, '0', '', '测试', 1, 0, 0, '', 1616054388191, 1, 1, 1616054388191, 0, 'cs');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '用户id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 229 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', '性别男', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', '性别女', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', '性别未知', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', '显示菜单', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', '隐藏菜单', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', '正常状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', '停用状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', '系统默认是', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', '系统默认否', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', '正常状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', '停用状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (100, 0, '正常', '0', 'sys_service_status', NULL, NULL, 'N', '0', NULL, 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (101, 0, '停用', '1', 'sys_service_status', NULL, NULL, 'N', '0', NULL, 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_data` VALUES (107, 0, '文本', '0', 'sys_text_type', NULL, NULL, 'N', '0', NULL, 0, 1590400561260, 1590457244058, 1, 1);
INSERT INTO `sys_dict_data` VALUES (108, 0, 'JSON', '1', 'sys_text_type', NULL, NULL, 'N', '0', NULL, 0, 1590400568363, 1590457251315, 1, 1);
INSERT INTO `sys_dict_data` VALUES (109, 0, '未删除', '0', 'sys_is_delete', NULL, NULL, 'N', '0', NULL, 0, 1590980587246, 1590980587246, 1, 1);
INSERT INTO `sys_dict_data` VALUES (110, 0, '已删除', '1', 'sys_is_delete', NULL, NULL, 'N', '0', NULL, 0, 1590980594974, 1590980594974, 1, 1);
INSERT INTO `sys_dict_data` VALUES (225, 0, '随机', '0', 'draw_type', NULL, NULL, 'N', '0', NULL, 0, 1615775453493, 1615775453493, 1, 1);
INSERT INTO `sys_dict_data` VALUES (226, 0, '优先级', '1', 'draw_type', NULL, NULL, 'N', '0', NULL, 0, 1615775463018, 1615775463018, 1, 1);
INSERT INTO `sys_dict_data` VALUES (227, 0, '未开始', '0', 'draw_complete', NULL, NULL, 'N', '0', NULL, 0, 1615778903628, 1615778903628, 1, 1);
INSERT INTO `sys_dict_data` VALUES (228, 0, '已结束', '1', 'draw_complete', NULL, NULL, 'N', '0', NULL, 0, 1615778916829, 1615778916829, 1, 1);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '用户id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', '用户性别列表', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', '菜单状态列表', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', '系统开关列表', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', '系统是否列表', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', '登录状态列表', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (100, '业务状态', 'sys_service_status', '0', '业务使用的状态', 0, 0, 0, 0, 0);
INSERT INTO `sys_dict_type` VALUES (104, '文本类型', 'sys_text_type', '0', '', 0, 1590400541406, 1590457205292, 1, 1);
INSERT INTO `sys_dict_type` VALUES (105, '是否删除', 'sys_is_delete', '0', NULL, 0, 1590980571454, 1590980571454, 1, 1);
INSERT INTO `sys_dict_type` VALUES (107, '阅读状态', 'sys_look_status', '0', NULL, 0, 1591684042663, 1591684042663, 1, 1);
INSERT INTO `sys_dict_type` VALUES (128, '概率类型', 'draw_type', '0', NULL, 0, 1615775439880, 1615775439880, 1, 1);
INSERT INTO `sys_dict_type` VALUES (129, '奖项状态', 'draw_complete', '0', NULL, 0, 1615778882153, 1615778882153, 1, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '#' COMMENT '菜单图标',
  `creator_id` bigint(11) NULL DEFAULT 1 COMMENT '创建者',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `operator_id` bigint(11) NULL DEFAULT 1 COMMENT '更新者',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2319 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 6, 'system', '', 1, 'M', '0', '0', '', 'system', 1, 20180316113300, 1, 1589966208518, '系统管理目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 8, 'tool', '', 1, 'M', '0', '0', '', 'tool', 1, 20180316113300, 1, 1597133006156, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 2016, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'user', 1, 20180316113300, 1, 20200512185807, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 2016, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'peoples', 1, 20180316113300, 1, 20200512185813, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', 1, 'C', '0', '0', 'system:menu:list', 'tree-table', 1, 20180316113300, 1, 20180316113300, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 2016, 4, 'dept', 'system/dept/index', 1, 'C', '0', '0', 'system:dept:list', 'tree', 1, 20180316113300, 1, 20200512185821, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 2016, 5, 'post', 'system/post/index', 1, 'C', '0', '0', 'system:post:list', 'post', 1, 20180316113300, 1, 20200512185834, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', 1, 'C', '0', '0', 'system:dict:list', 'dict', 1, 20180316113300, 1, 20180316113300, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '0', '0', 'system:config:list', 'edit', 1, 20180316113300, 1, 20180316113300, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (115, '系统接口', 3, 3, 'apidoc', 'apidoc/index', 1, 'C', '0', '0', 'tool:swagger:list', 'swagger', 1, 20180316113300, 1, 1594965493576, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (1001, '查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:list', '#', 1, 20180316113300, 1, 1594709040427, '');
INSERT INTO `sys_menu` VALUES (1002, '新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:save', '#', 1, 20180316113300, 1, 1600314332932, '');
INSERT INTO `sys_menu` VALUES (1003, '修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:update', '#', 1, 20180316113300, 1, 1594709061681, '');
INSERT INTO `sys_menu` VALUES (1004, '删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:delete', '#', 1, 20180316113300, 1, 1594709051089, '');
INSERT INTO `sys_menu` VALUES (1005, '导出', 100, 5, '', '', 1, 'F', '0', '0', 'system:user:export', '#', 1, 20180316113300, 1, 1594709056947, '');
INSERT INTO `sys_menu` VALUES (1006, '导入', 100, 6, '', '', 1, 'F', '0', '0', 'system:user:import', '#', 1, 20180316113300, 1, 1594709067258, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1008, '查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:list', '#', 1, 20180316113300, 1, 1594709109940, '');
INSERT INTO `sys_menu` VALUES (1009, '新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:save', '#', 1, 20180316113300, 1, 1600314321355, '');
INSERT INTO `sys_menu` VALUES (1010, '修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:update', '#', 1, 20180316113300, 1, 1594709119764, '');
INSERT INTO `sys_menu` VALUES (1011, '删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:delete', '#', 1, 20180316113300, 1, 1594709124084, '');
INSERT INTO `sys_menu` VALUES (1012, '导出', 101, 5, '', '', 1, 'F', '0', '0', 'system:role:export', '#', 1, 20180316113300, 1, 1594709128385, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:list', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:save', '#', 1, 20180316113300, 1, 1600314397730, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:update', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:delete', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1017, '查询', 103, 1, '', '', 1, 'F', '0', '0', 'system:dept:list', '#', 1, 20180316113300, 1, 1594709148238, '');
INSERT INTO `sys_menu` VALUES (1018, '新增', 103, 2, '', '', 1, 'F', '0', '0', 'system:dept:save', '#', 1, 20180316113300, 1, 1600314309295, '');
INSERT INTO `sys_menu` VALUES (1019, '修改', 103, 3, '', '', 1, 'F', '0', '0', 'system:dept:update', '#', 1, 20180316113300, 1, 1594709160953, '');
INSERT INTO `sys_menu` VALUES (1020, '删除', 103, 4, '', '', 1, 'F', '0', '0', 'system:dept:delete', '#', 1, 20180316113300, 1, 1594709166103, '');
INSERT INTO `sys_menu` VALUES (1021, '查询', 104, 1, '', '', 1, 'F', '0', '0', 'system:post:list', '#', 1, 20180316113300, 1, 1594709177148, '');
INSERT INTO `sys_menu` VALUES (1022, '新增', 104, 2, '', '', 1, 'F', '0', '0', 'system:post:save', '#', 1, 20180316113300, 1, 1600314362035, '');
INSERT INTO `sys_menu` VALUES (1023, '修改', 104, 3, '', '', 1, 'F', '0', '0', 'system:post:update', '#', 1, 20180316113300, 1, 1594709187170, '');
INSERT INTO `sys_menu` VALUES (1024, '删除', 104, 4, '', '', 1, 'F', '0', '0', 'system:post:delete', '#', 1, 20180316113300, 1, 1594709191753, '');
INSERT INTO `sys_menu` VALUES (1025, '导出', 104, 5, '', '', 1, 'F', '0', '0', 'system:post:export', '#', 1, 20180316113300, 1, 1594709196610, '');
INSERT INTO `sys_menu` VALUES (1026, '查询', 105, 1, '#', '', 1, 'F', '0', '0', 'system:dict:list', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1027, '新增', 105, 2, '#', '', 1, 'F', '0', '0', 'system:dict:save', '#', 1, 20180316113300, 1, 1600314409293, '');
INSERT INTO `sys_menu` VALUES (1028, '修改', 105, 3, '#', '', 1, 'F', '0', '0', 'system:dict:update', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1029, '删除', 105, 4, '#', '', 1, 'F', '0', '0', 'system:dict:delete', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1030, '导出', 105, 5, '#', '', 1, 'F', '0', '0', 'system:dict:export', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1031, '查询', 106, 1, '#', '', 1, 'F', '0', '0', 'system:config:list', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1032, '新增', 106, 2, '#', '', 1, 'F', '0', '0', 'system:config:save', '#', 1, 20180316113300, 1, 1600314418591, '');
INSERT INTO `sys_menu` VALUES (1033, '修改', 106, 3, '#', '', 1, 'F', '0', '0', 'system:config:update', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1034, '删除', 106, 4, '#', '', 1, 'F', '0', '0', 'system:config:delete', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (1035, '导出', 106, 5, '#', '', 1, 'F', '0', '0', 'system:config:export', '#', 1, 20180316113300, 1, 20180316113300, '');
INSERT INTO `sys_menu` VALUES (2016, '组织管理', 0, 2, 'org', '', 1, 'M', '0', '0', '', 'tree', 1, 20200512185756, 1, 1615645707188, '');
INSERT INTO `sys_menu` VALUES (2286, '抽奖管理', 0, 1, 'draw', '', 1, 'M', '0', '0', '', 'exit-fullscreen', 1, 1615701493118, 1, 1615701516572, '');
INSERT INTO `sys_menu` VALUES (2287, '中奖名单', 2286, 2, 'result', 'draw/result/index', 1, 'C', '0', '0', 'draw:result:list', 'checkbox', 1, 20210314141643000, 1, 1615702892729, '抽奖中奖菜单');
INSERT INTO `sys_menu` VALUES (2288, '查询', 2287, 1, '#', '', 1, 'F', '0', '0', 'draw:result:list', '#', 1, 20210314141643000, 1, 20210314141643000, '');
INSERT INTO `sys_menu` VALUES (2292, '导出', 2287, 5, '#', '', 1, 'F', '0', '0', 'draw:result:export', '#', 1, 20210314141643000, 1, 20210314141643000, '');
INSERT INTO `sys_menu` VALUES (2295, '新增', 2316, 9, '#', '', 1, 'F', '0', '0', 'draw:customer:save', '#', 1, 20210314141658000, 1, 1615781373842, '');
INSERT INTO `sys_menu` VALUES (2296, '修改', 2316, 10, '#', '', 1, 'F', '0', '0', 'draw:customer:update', '#', 1, 20210314141658000, 1, 1615781380470, '');
INSERT INTO `sys_menu` VALUES (2297, '删除', 2316, 12, '#', '', 1, 'F', '0', '0', 'draw:customer:delete', '#', 1, 20210314141658000, 1, 1615781390725, '');
INSERT INTO `sys_menu` VALUES (2298, '导出', 2316, 15, '#', '', 1, 'F', '0', '0', 'draw:customer:export', '#', 1, 20210314141658000, 1, 1615781396797, '');
INSERT INTO `sys_menu` VALUES (2299, '抽奖记录', 2286, 3, 'action', 'draw/action/index', 1, 'C', '1', '1', 'draw:action:list', 'dashboard', 1, 20210314141708000, 1, 1615790197760, '抽奖行为菜单');
INSERT INTO `sys_menu` VALUES (2300, '查询', 2299, 1, '#', '', 1, 'F', '0', '0', 'draw:action:list', '#', 1, 20210314141708000, 1, 20210314141708000, '');
INSERT INTO `sys_menu` VALUES (2304, '导出', 2299, 5, '#', '', 1, 'F', '0', '0', 'draw:action:export', '#', 1, 20210314141709000, 1, 20210314141709000, '');
INSERT INTO `sys_menu` VALUES (2305, '抽奖主题', 2286, 0, 'theme', 'draw/theme/index', 1, 'C', '0', '0', 'draw:theme:list', 'list', 1, 20210315095245000, 1, 1615775511594, '抽奖主题菜单');
INSERT INTO `sys_menu` VALUES (2306, '查询', 2305, 1, '#', '', 1, 'F', '0', '0', 'draw:theme:list', '#', 1, 20210315095245000, 1, 20210315095245000, '');
INSERT INTO `sys_menu` VALUES (2307, '新增', 2305, 2, '#', '', 1, 'F', '0', '0', 'draw:theme:save', '#', 1, 20210315095245000, 1, 20210315095245000, '');
INSERT INTO `sys_menu` VALUES (2308, '修改', 2305, 3, '#', '', 1, 'F', '0', '0', 'draw:theme:update', '#', 1, 20210315095245000, 1, 20210315095245000, '');
INSERT INTO `sys_menu` VALUES (2309, '删除', 2305, 4, '#', '', 1, 'F', '0', '0', 'draw:theme:delete', '#', 1, 20210315095245000, 1, 1615777650687, '');
INSERT INTO `sys_menu` VALUES (2310, '查询', 2315, 5, '', NULL, 1, 'F', '0', '0', 'draw:gift:llist', '#', 1, 1615777684965, 1, 1615779460041, '');
INSERT INTO `sys_menu` VALUES (2311, '奖项新增', 2286, 6, '', NULL, 1, 'F', '0', '0', 'draw:gift:save', '#', 1, 1615777769614, 1, 1615777769614, '');
INSERT INTO `sys_menu` VALUES (2312, '新增', 2315, 6, '', NULL, 1, 'F', '0', '0', 'draw:gift:save', '#', 1, 1615777830098, 1, 1615779451672, '');
INSERT INTO `sys_menu` VALUES (2313, '修改', 2315, 7, '', NULL, 1, 'F', '0', '0', 'draw:gift:update', '#', 1, 1615777856466, 1, 1615779443378, '');
INSERT INTO `sys_menu` VALUES (2314, '查询', 2316, 8, '', NULL, 1, 'F', '0', '0', 'draw:customer:list', '#', 1, 1615777911201, 1, 1615781365539, '');
INSERT INTO `sys_menu` VALUES (2315, '奖项管理', 2286, 1, 'gift', 'draw/gift/index', 1, 'C', '0', '0', 'draw:gift:list', 'chart', 1, 1615778244630, 1, 1615779433291, '');
INSERT INTO `sys_menu` VALUES (2316, '客户管理', 2286, 2, 'customer', 'draw/customer/index', 1, 'C', '0', '0', 'draw:customer:list', 'user', 1, 1615781353313, 1, 1615781353313, '');
INSERT INTO `sys_menu` VALUES (2317, '开始抽奖', 2286, 6, '', NULL, 1, 'F', '0', '0', 'draw:start', '#', 1, 1615789952742, 1, 1615789952742, '');
INSERT INTO `sys_menu` VALUES (2318, '导入', 2316, 5, '', NULL, 1, 'F', '0', '0', 'draw:customer:import', '#', 1, 1616402014776, 1, 1616402014776, '');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `post_code` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '岗位编码',
  `post_sort` int(4) NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态（0正常 1停用）',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '创建者',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `post_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '名称',
  `level` int(10) NULL DEFAULT 0 COMMENT '职务等级',
  `remark` varchar(1500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '最后修改时间',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '最后修改时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '组织表id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '备注',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `org_id` bigint(11) NULL DEFAULT 0 COMMENT '企业机构Id',
  `is_sys` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是是系统角色，0不是，1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1010 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'super_admin', 1, '1', '0', 0, 1, 1, '超级管理员', '', 0, 0, 0, 1);
INSERT INTO `sys_role` VALUES (1009, '1', 0, '1', '0', 1616054436656, 1, 1, '测试', '', 1616054436656, 0, 1, 0);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1009, 1);
INSERT INTO `sys_role_menu` VALUES (1009, 2);
INSERT INTO `sys_role_menu` VALUES (1009, 100);
INSERT INTO `sys_role_menu` VALUES (1009, 101);
INSERT INTO `sys_role_menu` VALUES (1009, 102);
INSERT INTO `sys_role_menu` VALUES (1009, 103);
INSERT INTO `sys_role_menu` VALUES (1009, 104);
INSERT INTO `sys_role_menu` VALUES (1009, 105);
INSERT INTO `sys_role_menu` VALUES (1009, 106);
INSERT INTO `sys_role_menu` VALUES (1009, 107);
INSERT INTO `sys_role_menu` VALUES (1009, 108);
INSERT INTO `sys_role_menu` VALUES (1009, 109);
INSERT INTO `sys_role_menu` VALUES (1009, 110);
INSERT INTO `sys_role_menu` VALUES (1009, 111);
INSERT INTO `sys_role_menu` VALUES (1009, 112);
INSERT INTO `sys_role_menu` VALUES (1009, 500);
INSERT INTO `sys_role_menu` VALUES (1009, 501);
INSERT INTO `sys_role_menu` VALUES (1009, 1001);
INSERT INTO `sys_role_menu` VALUES (1009, 1002);
INSERT INTO `sys_role_menu` VALUES (1009, 1003);
INSERT INTO `sys_role_menu` VALUES (1009, 1004);
INSERT INTO `sys_role_menu` VALUES (1009, 1005);
INSERT INTO `sys_role_menu` VALUES (1009, 1006);
INSERT INTO `sys_role_menu` VALUES (1009, 1007);
INSERT INTO `sys_role_menu` VALUES (1009, 1008);
INSERT INTO `sys_role_menu` VALUES (1009, 1009);
INSERT INTO `sys_role_menu` VALUES (1009, 1010);
INSERT INTO `sys_role_menu` VALUES (1009, 1011);
INSERT INTO `sys_role_menu` VALUES (1009, 1012);
INSERT INTO `sys_role_menu` VALUES (1009, 1013);
INSERT INTO `sys_role_menu` VALUES (1009, 1014);
INSERT INTO `sys_role_menu` VALUES (1009, 1015);
INSERT INTO `sys_role_menu` VALUES (1009, 1016);
INSERT INTO `sys_role_menu` VALUES (1009, 1017);
INSERT INTO `sys_role_menu` VALUES (1009, 1018);
INSERT INTO `sys_role_menu` VALUES (1009, 1019);
INSERT INTO `sys_role_menu` VALUES (1009, 1020);
INSERT INTO `sys_role_menu` VALUES (1009, 1021);
INSERT INTO `sys_role_menu` VALUES (1009, 1022);
INSERT INTO `sys_role_menu` VALUES (1009, 1023);
INSERT INTO `sys_role_menu` VALUES (1009, 1024);
INSERT INTO `sys_role_menu` VALUES (1009, 1025);
INSERT INTO `sys_role_menu` VALUES (1009, 1026);
INSERT INTO `sys_role_menu` VALUES (1009, 1027);
INSERT INTO `sys_role_menu` VALUES (1009, 1028);
INSERT INTO `sys_role_menu` VALUES (1009, 1029);
INSERT INTO `sys_role_menu` VALUES (1009, 1030);
INSERT INTO `sys_role_menu` VALUES (1009, 1031);
INSERT INTO `sys_role_menu` VALUES (1009, 1032);
INSERT INTO `sys_role_menu` VALUES (1009, 1033);
INSERT INTO `sys_role_menu` VALUES (1009, 1034);
INSERT INTO `sys_role_menu` VALUES (1009, 1035);
INSERT INTO `sys_role_menu` VALUES (1009, 1036);
INSERT INTO `sys_role_menu` VALUES (1009, 1037);
INSERT INTO `sys_role_menu` VALUES (1009, 1038);
INSERT INTO `sys_role_menu` VALUES (1009, 1039);
INSERT INTO `sys_role_menu` VALUES (1009, 1040);
INSERT INTO `sys_role_menu` VALUES (1009, 1041);
INSERT INTO `sys_role_menu` VALUES (1009, 1042);
INSERT INTO `sys_role_menu` VALUES (1009, 1043);
INSERT INTO `sys_role_menu` VALUES (1009, 1044);
INSERT INTO `sys_role_menu` VALUES (1009, 1045);
INSERT INTO `sys_role_menu` VALUES (1009, 1046);
INSERT INTO `sys_role_menu` VALUES (1009, 1047);
INSERT INTO `sys_role_menu` VALUES (1009, 1048);
INSERT INTO `sys_role_menu` VALUES (1009, 1049);
INSERT INTO `sys_role_menu` VALUES (1009, 1050);
INSERT INTO `sys_role_menu` VALUES (1009, 1051);
INSERT INTO `sys_role_menu` VALUES (1009, 1052);
INSERT INTO `sys_role_menu` VALUES (1009, 1053);
INSERT INTO `sys_role_menu` VALUES (1009, 1054);
INSERT INTO `sys_role_menu` VALUES (1009, 2016);
INSERT INTO `sys_role_menu` VALUES (1009, 2024);
INSERT INTO `sys_role_menu` VALUES (1009, 2025);
INSERT INTO `sys_role_menu` VALUES (1009, 2026);
INSERT INTO `sys_role_menu` VALUES (1009, 2027);
INSERT INTO `sys_role_menu` VALUES (1009, 2028);
INSERT INTO `sys_role_menu` VALUES (1009, 2029);
INSERT INTO `sys_role_menu` VALUES (1009, 2286);
INSERT INTO `sys_role_menu` VALUES (1009, 2287);
INSERT INTO `sys_role_menu` VALUES (1009, 2288);
INSERT INTO `sys_role_menu` VALUES (1009, 2292);
INSERT INTO `sys_role_menu` VALUES (1009, 2295);
INSERT INTO `sys_role_menu` VALUES (1009, 2296);
INSERT INTO `sys_role_menu` VALUES (1009, 2297);
INSERT INTO `sys_role_menu` VALUES (1009, 2298);
INSERT INTO `sys_role_menu` VALUES (1009, 2299);
INSERT INTO `sys_role_menu` VALUES (1009, 2300);
INSERT INTO `sys_role_menu` VALUES (1009, 2304);
INSERT INTO `sys_role_menu` VALUES (1009, 2305);
INSERT INTO `sys_role_menu` VALUES (1009, 2306);
INSERT INTO `sys_role_menu` VALUES (1009, 2307);
INSERT INTO `sys_role_menu` VALUES (1009, 2308);
INSERT INTO `sys_role_menu` VALUES (1009, 2309);
INSERT INTO `sys_role_menu` VALUES (1009, 2310);
INSERT INTO `sys_role_menu` VALUES (1009, 2311);
INSERT INTO `sys_role_menu` VALUES (1009, 2312);
INSERT INTO `sys_role_menu` VALUES (1009, 2313);
INSERT INTO `sys_role_menu` VALUES (1009, 2314);
INSERT INTO `sys_role_menu` VALUES (1009, 2315);
INSERT INTO `sys_role_menu` VALUES (1009, 2316);
INSERT INTO `sys_role_menu` VALUES (1009, 2317);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '头像地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` bigint(11) NULL DEFAULT 0 COMMENT '登录时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '名称',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `login_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '登录密码',
  `sex` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '性别',
  `entry_time` bigint(11) NULL DEFAULT 0 COMMENT '入职时间',
  `work_number` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '工号',
  `org_email` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '邮件地址',
  `create_time` bigint(11) NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(11) NULL DEFAULT 0 COMMENT '修改时间',
  `creator_id` bigint(11) NULL DEFAULT 0 COMMENT '组织表id',
  `operator_id` bigint(11) NULL DEFAULT 0 COMMENT '最后操作id',
  `dept_id` varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '0' COMMENT '所在部门',
  `name_pinyin` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '拼音名称',
  `mobile_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5657 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '00', 'group1/M00/00/01/CgACcV-M8LCAPqo3AABtMElNmX8805.jpg', '0', '', 0, NULL, '超级管理员', 0, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', 0, '', '1232323@11.com', 0, 1590044732551, 0, 1, '1223', '', '13660781279');
INSERT INTO `sys_user` VALUES (5656, '01', '', '0', '', 0, '', '测试', 0, 'test', '$2a$10$aa833rozZR0g72Qj86AUZOHkf9xBWawwObuqlCqh.wI2AVFR1zRLi', '', 1616055678142, '001', '', 1616054420777, 1616054447264, 1, 1, '1491', 'cs', '13000000000');

-- ----------------------------
-- Table structure for sys_user_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_perm`;
CREATE TABLE `sys_user_perm`  (
  `user_id` bigint(11) NOT NULL COMMENT '角色ID',
  `perm_user_id` bigint(11) NOT NULL COMMENT '可查看的用户ID',
  PRIMARY KEY (`user_id`, `perm_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '登录用户和用户权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_perm
-- ----------------------------
INSERT INTO `sys_user_perm` VALUES (3528, 1);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1011, 1539);
INSERT INTO `sys_user_post` VALUES (1016, 1418);
INSERT INTO `sys_user_post` VALUES (1016, 1465);
INSERT INTO `sys_user_post` VALUES (1016, 1551);
INSERT INTO `sys_user_post` VALUES (1016, 1840);
INSERT INTO `sys_user_post` VALUES (1017, 1264);
INSERT INTO `sys_user_post` VALUES (1017, 1386);
INSERT INTO `sys_user_post` VALUES (1017, 1792);
INSERT INTO `sys_user_post` VALUES (1018, 1038);
INSERT INTO `sys_user_post` VALUES (1018, 1138);
INSERT INTO `sys_user_post` VALUES (1018, 1152);
INSERT INTO `sys_user_post` VALUES (1018, 1418);
INSERT INTO `sys_user_post` VALUES (1018, 1581);
INSERT INTO `sys_user_post` VALUES (1018, 1587);
INSERT INTO `sys_user_post` VALUES (1018, 1588);
INSERT INTO `sys_user_post` VALUES (1018, 1769);
INSERT INTO `sys_user_post` VALUES (1018, 1773);
INSERT INTO `sys_user_post` VALUES (1018, 1838);
INSERT INTO `sys_user_post` VALUES (1018, 1839);
INSERT INTO `sys_user_post` VALUES (1020, 1149);
INSERT INTO `sys_user_post` VALUES (1023, 1036);
INSERT INTO `sys_user_post` VALUES (1023, 1143);
INSERT INTO `sys_user_post` VALUES (1023, 1361);
INSERT INTO `sys_user_post` VALUES (1029, 1404);
INSERT INTO `sys_user_post` VALUES (1029, 1592);
INSERT INTO `sys_user_post` VALUES (1029, 1742);
INSERT INTO `sys_user_post` VALUES (1029, 1752);
INSERT INTO `sys_user_post` VALUES (1029, 1754);
INSERT INTO `sys_user_post` VALUES (1029, 1755);
INSERT INTO `sys_user_post` VALUES (1029, 1758);
INSERT INTO `sys_user_post` VALUES (1029, 1759);
INSERT INTO `sys_user_post` VALUES (1029, 1766);
INSERT INTO `sys_user_post` VALUES (1029, 1769);
INSERT INTO `sys_user_post` VALUES (1029, 1772);
INSERT INTO `sys_user_post` VALUES (1029, 1773);
INSERT INTO `sys_user_post` VALUES (1029, 1774);
INSERT INTO `sys_user_post` VALUES (1032, 1289);
INSERT INTO `sys_user_post` VALUES (1033, 1108);
INSERT INTO `sys_user_post` VALUES (1033, 1493);
INSERT INTO `sys_user_post` VALUES (1033, 1498);
INSERT INTO `sys_user_post` VALUES (1033, 1533);
INSERT INTO `sys_user_post` VALUES (1033, 1535);
INSERT INTO `sys_user_post` VALUES (1033, 1538);
INSERT INTO `sys_user_post` VALUES (1034, 1744);
INSERT INTO `sys_user_post` VALUES (1035, 1446);
INSERT INTO `sys_user_post` VALUES (1038, 1115);
INSERT INTO `sys_user_post` VALUES (1038, 1739);
INSERT INTO `sys_user_post` VALUES (1041, 1277);
INSERT INTO `sys_user_post` VALUES (1042, 1289);
INSERT INTO `sys_user_post` VALUES (1042, 1487);
INSERT INTO `sys_user_post` VALUES (1044, 1108);
INSERT INTO `sys_user_post` VALUES (1044, 1506);
INSERT INTO `sys_user_post` VALUES (1044, 1536);
INSERT INTO `sys_user_post` VALUES (1044, 1783);
INSERT INTO `sys_user_post` VALUES (1044, 1785);
INSERT INTO `sys_user_post` VALUES (1044, 1786);
INSERT INTO `sys_user_post` VALUES (1047, 1299);
INSERT INTO `sys_user_post` VALUES (1048, 1353);
INSERT INTO `sys_user_post` VALUES (1048, 1363);
INSERT INTO `sys_user_post` VALUES (1048, 1447);
INSERT INTO `sys_user_post` VALUES (1049, 1033);
INSERT INTO `sys_user_post` VALUES (1049, 1128);
INSERT INTO `sys_user_post` VALUES (1049, 1393);
INSERT INTO `sys_user_post` VALUES (1049, 1757);
INSERT INTO `sys_user_post` VALUES (1049, 1763);
INSERT INTO `sys_user_post` VALUES (1056, 1624);
INSERT INTO `sys_user_post` VALUES (1057, 1118);
INSERT INTO `sys_user_post` VALUES (1057, 1384);
INSERT INTO `sys_user_post` VALUES (1057, 1463);
INSERT INTO `sys_user_post` VALUES (1057, 1559);
INSERT INTO `sys_user_post` VALUES (1057, 1753);
INSERT INTO `sys_user_post` VALUES (1057, 1764);
INSERT INTO `sys_user_post` VALUES (1061, 1277);
INSERT INTO `sys_user_post` VALUES (1071, 1290);
INSERT INTO `sys_user_post` VALUES (1072, 1433);
INSERT INTO `sys_user_post` VALUES (1072, 1543);
INSERT INTO `sys_user_post` VALUES (1072, 1635);
INSERT INTO `sys_user_post` VALUES (1072, 1800);
INSERT INTO `sys_user_post` VALUES (1072, 1803);
INSERT INTO `sys_user_post` VALUES (1073, 1363);
INSERT INTO `sys_user_post` VALUES (1073, 1621);
INSERT INTO `sys_user_post` VALUES (1073, 1664);
INSERT INTO `sys_user_post` VALUES (1073, 1666);
INSERT INTO `sys_user_post` VALUES (1073, 1674);
INSERT INTO `sys_user_post` VALUES (1073, 1677);
INSERT INTO `sys_user_post` VALUES (1075, 1030);
INSERT INTO `sys_user_post` VALUES (1075, 1146);
INSERT INTO `sys_user_post` VALUES (1075, 1155);
INSERT INTO `sys_user_post` VALUES (1075, 1515);
INSERT INTO `sys_user_post` VALUES (1076, 1035);
INSERT INTO `sys_user_post` VALUES (1076, 1140);
INSERT INTO `sys_user_post` VALUES (1076, 1431);
INSERT INTO `sys_user_post` VALUES (1076, 1472);
INSERT INTO `sys_user_post` VALUES (1076, 1502);
INSERT INTO `sys_user_post` VALUES (1076, 1631);
INSERT INTO `sys_user_post` VALUES (1076, 1655);
INSERT INTO `sys_user_post` VALUES (1076, 1656);
INSERT INTO `sys_user_post` VALUES (1076, 1663);
INSERT INTO `sys_user_post` VALUES (1076, 1664);
INSERT INTO `sys_user_post` VALUES (1076, 1781);
INSERT INTO `sys_user_post` VALUES (1076, 1798);
INSERT INTO `sys_user_post` VALUES (1076, 1802);
INSERT INTO `sys_user_post` VALUES (1078, 1160);
INSERT INTO `sys_user_post` VALUES (1079, 1405);
INSERT INTO `sys_user_post` VALUES (1081, 1289);
INSERT INTO `sys_user_post` VALUES (1081, 1487);
INSERT INTO `sys_user_post` VALUES (1081, 1728);
INSERT INTO `sys_user_post` VALUES (1081, 1743);
INSERT INTO `sys_user_post` VALUES (1082, 1479);
INSERT INTO `sys_user_post` VALUES (1082, 1512);
INSERT INTO `sys_user_post` VALUES (1082, 1604);
INSERT INTO `sys_user_post` VALUES (1084, 1135);
INSERT INTO `sys_user_post` VALUES (1085, 1037);
INSERT INTO `sys_user_post` VALUES (1085, 1355);
INSERT INTO `sys_user_post` VALUES (1087, 1496);
INSERT INTO `sys_user_post` VALUES (1093, 1707);
INSERT INTO `sys_user_post` VALUES (1096, 1296);
INSERT INTO `sys_user_post` VALUES (1096, 1746);
INSERT INTO `sys_user_post` VALUES (1098, 1473);
INSERT INTO `sys_user_post` VALUES (1099, 1394);
INSERT INTO `sys_user_post` VALUES (1103, 1456);
INSERT INTO `sys_user_post` VALUES (1107, 1823);
INSERT INTO `sys_user_post` VALUES (1115, 1265);
INSERT INTO `sys_user_post` VALUES (1115, 1500);
INSERT INTO `sys_user_post` VALUES (1115, 1559);
INSERT INTO `sys_user_post` VALUES (1116, 1700);
INSERT INTO `sys_user_post` VALUES (1119, 1151);
INSERT INTO `sys_user_post` VALUES (1120, 1708);
INSERT INTO `sys_user_post` VALUES (1122, 1279);
INSERT INTO `sys_user_post` VALUES (1123, 1205);
INSERT INTO `sys_user_post` VALUES (1123, 1582);
INSERT INTO `sys_user_post` VALUES (1129, 1045);
INSERT INTO `sys_user_post` VALUES (1129, 1380);
INSERT INTO `sys_user_post` VALUES (1130, 1574);
INSERT INTO `sys_user_post` VALUES (1130, 1606);
INSERT INTO `sys_user_post` VALUES (1130, 1689);
INSERT INTO `sys_user_post` VALUES (1130, 1695);
INSERT INTO `sys_user_post` VALUES (1133, 1526);
INSERT INTO `sys_user_post` VALUES (1136, 1041);
INSERT INTO `sys_user_post` VALUES (1136, 1115);
INSERT INTO `sys_user_post` VALUES (1136, 1319);
INSERT INTO `sys_user_post` VALUES (1136, 1739);
INSERT INTO `sys_user_post` VALUES (1137, 1436);
INSERT INTO `sys_user_post` VALUES (1138, 1204);
INSERT INTO `sys_user_post` VALUES (1138, 1215);
INSERT INTO `sys_user_post` VALUES (1139, 1134);
INSERT INTO `sys_user_post` VALUES (1139, 1514);
INSERT INTO `sys_user_post` VALUES (1139, 1646);
INSERT INTO `sys_user_post` VALUES (1139, 1651);
INSERT INTO `sys_user_post` VALUES (1139, 1801);
INSERT INTO `sys_user_post` VALUES (1139, 1806);
INSERT INTO `sys_user_post` VALUES (1142, 1289);
INSERT INTO `sys_user_post` VALUES (1142, 1448);
INSERT INTO `sys_user_post` VALUES (1142, 1487);
INSERT INTO `sys_user_post` VALUES (1142, 1489);
INSERT INTO `sys_user_post` VALUES (1142, 1521);
INSERT INTO `sys_user_post` VALUES (1144, 1590);
INSERT INTO `sys_user_post` VALUES (1144, 1792);
INSERT INTO `sys_user_post` VALUES (1149, 1122);
INSERT INTO `sys_user_post` VALUES (1154, 1699);
INSERT INTO `sys_user_post` VALUES (1157, 1603);
INSERT INTO `sys_user_post` VALUES (1161, 1600);
INSERT INTO `sys_user_post` VALUES (1163, 1524);
INSERT INTO `sys_user_post` VALUES (1163, 1527);
INSERT INTO `sys_user_post` VALUES (1167, 1113);
INSERT INTO `sys_user_post` VALUES (1169, 1697);
INSERT INTO `sys_user_post` VALUES (1179, 1719);
INSERT INTO `sys_user_post` VALUES (1182, 1040);
INSERT INTO `sys_user_post` VALUES (1182, 1139);
INSERT INTO `sys_user_post` VALUES (1182, 1370);
INSERT INTO `sys_user_post` VALUES (1182, 1756);
INSERT INTO `sys_user_post` VALUES (1182, 1760);
INSERT INTO `sys_user_post` VALUES (1203, 1709);
INSERT INTO `sys_user_post` VALUES (1204, 1692);
INSERT INTO `sys_user_post` VALUES (1219, 1520);
INSERT INTO `sys_user_post` VALUES (1219, 1530);
INSERT INTO `sys_user_post` VALUES (1219, 1583);
INSERT INTO `sys_user_post` VALUES (1226, 1263);
INSERT INTO `sys_user_post` VALUES (1226, 1531);
INSERT INTO `sys_user_post` VALUES (1249, 1070);
INSERT INTO `sys_user_post` VALUES (1249, 1093);
INSERT INTO `sys_user_post` VALUES (1249, 1169);
INSERT INTO `sys_user_post` VALUES (1250, 1272);
INSERT INTO `sys_user_post` VALUES (1254, 1691);
INSERT INTO `sys_user_post` VALUES (1272, 1520);
INSERT INTO `sys_user_post` VALUES (1272, 1530);
INSERT INTO `sys_user_post` VALUES (1272, 1583);
INSERT INTO `sys_user_post` VALUES (1274, 1790);
INSERT INTO `sys_user_post` VALUES (1288, 1269);
INSERT INTO `sys_user_post` VALUES (1289, 1261);
INSERT INTO `sys_user_post` VALUES (1290, 1248);
INSERT INTO `sys_user_post` VALUES (1290, 1378);
INSERT INTO `sys_user_post` VALUES (1293, 1274);
INSERT INTO `sys_user_post` VALUES (1300, 1170);
INSERT INTO `sys_user_post` VALUES (1305, 1171);
INSERT INTO `sys_user_post` VALUES (1368, 1683);
INSERT INTO `sys_user_post` VALUES (1374, 1649);
INSERT INTO `sys_user_post` VALUES (1399, 1049);
INSERT INTO `sys_user_post` VALUES (1399, 1574);
INSERT INTO `sys_user_post` VALUES (1406, 1712);
INSERT INTO `sys_user_post` VALUES (1406, 1717);
INSERT INTO `sys_user_post` VALUES (1435, 1252);
INSERT INTO `sys_user_post` VALUES (1435, 1722);
INSERT INTO `sys_user_post` VALUES (1438, 1376);
INSERT INTO `sys_user_post` VALUES (1438, 1516);
INSERT INTO `sys_user_post` VALUES (1438, 1577);
INSERT INTO `sys_user_post` VALUES (1438, 1731);
INSERT INTO `sys_user_post` VALUES (1439, 1252);
INSERT INTO `sys_user_post` VALUES (1448, 1601);
INSERT INTO `sys_user_post` VALUES (1452, 1273);
INSERT INTO `sys_user_post` VALUES (1452, 1379);
INSERT INTO `sys_user_post` VALUES (1452, 1834);
INSERT INTO `sys_user_post` VALUES (1486, 1716);
INSERT INTO `sys_user_post` VALUES (1488, 1688);
INSERT INTO `sys_user_post` VALUES (1493, 1685);
INSERT INTO `sys_user_post` VALUES (1495, 1690);
INSERT INTO `sys_user_post` VALUES (1510, 1696);
INSERT INTO `sys_user_post` VALUES (1535, 1373);
INSERT INTO `sys_user_post` VALUES (1535, 1486);
INSERT INTO `sys_user_post` VALUES (1535, 1503);
INSERT INTO `sys_user_post` VALUES (1536, 1269);
INSERT INTO `sys_user_post` VALUES (1543, 1285);
INSERT INTO `sys_user_post` VALUES (1543, 1812);
INSERT INTO `sys_user_post` VALUES (1545, 1721);
INSERT INTO `sys_user_post` VALUES (1547, 1371);
INSERT INTO `sys_user_post` VALUES (1547, 1377);
INSERT INTO `sys_user_post` VALUES (1547, 1381);
INSERT INTO `sys_user_post` VALUES (1547, 1484);
INSERT INTO `sys_user_post` VALUES (1547, 1542);
INSERT INTO `sys_user_post` VALUES (1547, 1584);
INSERT INTO `sys_user_post` VALUES (1547, 1634);
INSERT INTO `sys_user_post` VALUES (1547, 1650);
INSERT INTO `sys_user_post` VALUES (1547, 1842);
INSERT INTO `sys_user_post` VALUES (1547, 1843);
INSERT INTO `sys_user_post` VALUES (1550, 1109);
INSERT INTO `sys_user_post` VALUES (1550, 1489);
INSERT INTO `sys_user_post` VALUES (1551, 1119);
INSERT INTO `sys_user_post` VALUES (1551, 1366);
INSERT INTO `sys_user_post` VALUES (1551, 1487);
INSERT INTO `sys_user_post` VALUES (1552, 1481);
INSERT INTO `sys_user_post` VALUES (1552, 1554);
INSERT INTO `sys_user_post` VALUES (1554, 1129);
INSERT INTO `sys_user_post` VALUES (1554, 1544);
INSERT INTO `sys_user_post` VALUES (1555, 1113);
INSERT INTO `sys_user_post` VALUES (1555, 1487);
INSERT INTO `sys_user_post` VALUES (1555, 1489);
INSERT INTO `sys_user_post` VALUES (1555, 1729);
INSERT INTO `sys_user_post` VALUES (1556, 1127);
INSERT INTO `sys_user_post` VALUES (1557, 1092);
INSERT INTO `sys_user_post` VALUES (1557, 1111);
INSERT INTO `sys_user_post` VALUES (1557, 1836);
INSERT INTO `sys_user_post` VALUES (1557, 1837);
INSERT INTO `sys_user_post` VALUES (1561, 1580);
INSERT INTO `sys_user_post` VALUES (1561, 1625);
INSERT INTO `sys_user_post` VALUES (1561, 1626);
INSERT INTO `sys_user_post` VALUES (1561, 1793);
INSERT INTO `sys_user_post` VALUES (1562, 1275);
INSERT INTO `sys_user_post` VALUES (1565, 1114);
INSERT INTO `sys_user_post` VALUES (1566, 1810);
INSERT INTO `sys_user_post` VALUES (1568, 1532);
INSERT INTO `sys_user_post` VALUES (1568, 1724);
INSERT INTO `sys_user_post` VALUES (1568, 1727);
INSERT INTO `sys_user_post` VALUES (1568, 1732);
INSERT INTO `sys_user_post` VALUES (1568, 1788);
INSERT INTO `sys_user_post` VALUES (1570, 1277);
INSERT INTO `sys_user_post` VALUES (1573, 1818);
INSERT INTO `sys_user_post` VALUES (1575, 1576);
INSERT INTO `sys_user_post` VALUES (1575, 1628);
INSERT INTO `sys_user_post` VALUES (1575, 1741);
INSERT INTO `sys_user_post` VALUES (1577, 1113);
INSERT INTO `sys_user_post` VALUES (1577, 1487);
INSERT INTO `sys_user_post` VALUES (1577, 1489);
INSERT INTO `sys_user_post` VALUES (1578, 1814);
INSERT INTO `sys_user_post` VALUES (1581, 1125);
INSERT INTO `sys_user_post` VALUES (1583, 1824);
INSERT INTO `sys_user_post` VALUES (1584, 1661);
INSERT INTO `sys_user_post` VALUES (1584, 1670);
INSERT INTO `sys_user_post` VALUES (1588, 1121);
INSERT INTO `sys_user_post` VALUES (1588, 1815);
INSERT INTO `sys_user_post` VALUES (1595, 1105);
INSERT INTO `sys_user_post` VALUES (1596, 1681);
INSERT INTO `sys_user_post` VALUES (1598, 1117);
INSERT INTO `sys_user_post` VALUES (1602, 1682);
INSERT INTO `sys_user_post` VALUES (1612, 1668);
INSERT INTO `sys_user_post` VALUES (1612, 1675);
INSERT INTO `sys_user_post` VALUES (1612, 1676);
INSERT INTO `sys_user_post` VALUES (1614, 1715);
INSERT INTO `sys_user_post` VALUES (1621, 1400);
INSERT INTO `sys_user_post` VALUES (1633, 1599);
INSERT INTO `sys_user_post` VALUES (1643, 1702);
INSERT INTO `sys_user_post` VALUES (1651, 1162);
INSERT INTO `sys_user_post` VALUES (1651, 1545);
INSERT INTO `sys_user_post` VALUES (1651, 1777);
INSERT INTO `sys_user_post` VALUES (1654, 1161);
INSERT INTO `sys_user_post` VALUES (1677, 1713);
INSERT INTO `sys_user_post` VALUES (1686, 1252);
INSERT INTO `sys_user_post` VALUES (1694, 1744);
INSERT INTO `sys_user_post` VALUES (1699, 1737);
INSERT INTO `sys_user_post` VALUES (1701, 1639);
INSERT INTO `sys_user_post` VALUES (1701, 1642);
INSERT INTO `sys_user_post` VALUES (1715, 1252);
INSERT INTO `sys_user_post` VALUES (1720, 1314);
INSERT INTO `sys_user_post` VALUES (1722, 1039);
INSERT INTO `sys_user_post` VALUES (1722, 1356);
INSERT INTO `sys_user_post` VALUES (1722, 1791);
INSERT INTO `sys_user_post` VALUES (1724, 1117);
INSERT INTO `sys_user_post` VALUES (1731, 1316);
INSERT INTO `sys_user_post` VALUES (1735, 1353);
INSERT INTO `sys_user_post` VALUES (1735, 1666);
INSERT INTO `sys_user_post` VALUES (1751, 1662);
INSERT INTO `sys_user_post` VALUES (1751, 1672);
INSERT INTO `sys_user_post` VALUES (1765, 1308);
INSERT INTO `sys_user_post` VALUES (1769, 1308);
INSERT INTO `sys_user_post` VALUES (1769, 1372);
INSERT INTO `sys_user_post` VALUES (1770, 1307);
INSERT INTO `sys_user_post` VALUES (1770, 1471);
INSERT INTO `sys_user_post` VALUES (1770, 1633);
INSERT INTO `sys_user_post` VALUES (1770, 1638);
INSERT INTO `sys_user_post` VALUES (1770, 1645);
INSERT INTO `sys_user_post` VALUES (1770, 1667);
INSERT INTO `sys_user_post` VALUES (1781, 1466);
INSERT INTO `sys_user_post` VALUES (1797, 1513);
INSERT INTO `sys_user_post` VALUES (1820, 1028);
INSERT INTO `sys_user_post` VALUES (1823, 1821);
INSERT INTO `sys_user_post` VALUES (1831, 1454);
INSERT INTO `sys_user_post` VALUES (1833, 1704);
INSERT INTO `sys_user_post` VALUES (1837, 1529);
INSERT INTO `sys_user_post` VALUES (1846, 1745);
INSERT INTO `sys_user_post` VALUES (1847, 1745);
INSERT INTO `sys_user_post` VALUES (1848, 1747);
INSERT INTO `sys_user_post` VALUES (1854, 1308);
INSERT INTO `sys_user_post` VALUES (1904, 1698);
INSERT INTO `sys_user_post` VALUES (1904, 1833);
INSERT INTO `sys_user_post` VALUES (1954, 1826);
INSERT INTO `sys_user_post` VALUES (1954, 1827);
INSERT INTO `sys_user_post` VALUES (1966, 1556);
INSERT INTO `sys_user_post` VALUES (1986, 1170);
INSERT INTO `sys_user_post` VALUES (2004, 1168);
INSERT INTO `sys_user_post` VALUES (2004, 1172);
INSERT INTO `sys_user_post` VALUES (2004, 1346);
INSERT INTO `sys_user_post` VALUES (2004, 1593);
INSERT INTO `sys_user_post` VALUES (2010, 1150);
INSERT INTO `sys_user_post` VALUES (2010, 1768);
INSERT INTO `sys_user_post` VALUES (2010, 1770);
INSERT INTO `sys_user_post` VALUES (2014, 1640);
INSERT INTO `sys_user_post` VALUES (2031, 1359);
INSERT INTO `sys_user_post` VALUES (2031, 1548);
INSERT INTO `sys_user_post` VALUES (2031, 1550);
INSERT INTO `sys_user_post` VALUES (2031, 1636);
INSERT INTO `sys_user_post` VALUES (2033, 1262);
INSERT INTO `sys_user_post` VALUES (2035, 1367);
INSERT INTO `sys_user_post` VALUES (2035, 1560);
INSERT INTO `sys_user_post` VALUES (2043, 1745);
INSERT INTO `sys_user_post` VALUES (2053, 1832);
INSERT INTO `sys_user_post` VALUES (2055, 1141);
INSERT INTO `sys_user_post` VALUES (2055, 1374);
INSERT INTO `sys_user_post` VALUES (2055, 1518);
INSERT INTO `sys_user_post` VALUES (2055, 1585);
INSERT INTO `sys_user_post` VALUES (2069, 1520);
INSERT INTO `sys_user_post` VALUES (2069, 1530);
INSERT INTO `sys_user_post` VALUES (2069, 1583);
INSERT INTO `sys_user_post` VALUES (2085, 1523);
INSERT INTO `sys_user_post` VALUES (2085, 1657);
INSERT INTO `sys_user_post` VALUES (2085, 1660);
INSERT INTO `sys_user_post` VALUES (2085, 1673);
INSERT INTO `sys_user_post` VALUES (2085, 1679);
INSERT INTO `sys_user_post` VALUES (2092, 1511);
INSERT INTO `sys_user_post` VALUES (2092, 1622);
INSERT INTO `sys_user_post` VALUES (2093, 1157);
INSERT INTO `sys_user_post` VALUES (2093, 1360);
INSERT INTO `sys_user_post` VALUES (2093, 1522);
INSERT INTO `sys_user_post` VALUES (2093, 1736);
INSERT INTO `sys_user_post` VALUES (2093, 1751);
INSERT INTO `sys_user_post` VALUES (2095, 1046);
INSERT INTO `sys_user_post` VALUES (2095, 1266);
INSERT INTO `sys_user_post` VALUES (2095, 1738);
INSERT INTO `sys_user_post` VALUES (2102, 1308);
INSERT INTO `sys_user_post` VALUES (2114, 1483);
INSERT INTO `sys_user_post` VALUES (2114, 1490);
INSERT INTO `sys_user_post` VALUES (2114, 1504);
INSERT INTO `sys_user_post` VALUES (2114, 1534);
INSERT INTO `sys_user_post` VALUES (2114, 1594);
INSERT INTO `sys_user_post` VALUES (2114, 1807);
INSERT INTO `sys_user_post` VALUES (2115, 1529);
INSERT INTO `sys_user_post` VALUES (2122, 1308);
INSERT INTO `sys_user_post` VALUES (2170, 1629);
INSERT INTO `sys_user_post` VALUES (2180, 1564);
INSERT INTO `sys_user_post` VALUES (2180, 1565);
INSERT INTO `sys_user_post` VALUES (2240, 1710);
INSERT INTO `sys_user_post` VALUES (2271, 1557);
INSERT INTO `sys_user_post` VALUES (2271, 1570);
INSERT INTO `sys_user_post` VALUES (2271, 1571);
INSERT INTO `sys_user_post` VALUES (2271, 1573);
INSERT INTO `sys_user_post` VALUES (2293, 1487);
INSERT INTO `sys_user_post` VALUES (2294, 1787);
INSERT INTO `sys_user_post` VALUES (2296, 1520);
INSERT INTO `sys_user_post` VALUES (2296, 1530);
INSERT INTO `sys_user_post` VALUES (2296, 1583);
INSERT INTO `sys_user_post` VALUES (2296, 1825);
INSERT INTO `sys_user_post` VALUES (2313, 1509);
INSERT INTO `sys_user_post` VALUES (2328, 1475);
INSERT INTO `sys_user_post` VALUES (2328, 1829);
INSERT INTO `sys_user_post` VALUES (2351, 1771);
INSERT INTO `sys_user_post` VALUES (2382, 1489);
INSERT INTO `sys_user_post` VALUES (2382, 1530);
INSERT INTO `sys_user_post` VALUES (2382, 1825);
INSERT INTO `sys_user_post` VALUES (2386, 1489);
INSERT INTO `sys_user_post` VALUES (2386, 1530);
INSERT INTO `sys_user_post` VALUES (2386, 1825);
INSERT INTO `sys_user_post` VALUES (2398, 1555);
INSERT INTO `sys_user_post` VALUES (2398, 1811);
INSERT INTO `sys_user_post` VALUES (2468, 1558);
INSERT INTO `sys_user_post` VALUES (2468, 1562);
INSERT INTO `sys_user_post` VALUES (2468, 1563);
INSERT INTO `sys_user_post` VALUES (2468, 1572);
INSERT INTO `sys_user_post` VALUES (2473, 1632);
INSERT INTO `sys_user_post` VALUES (2483, 1553);
INSERT INTO `sys_user_post` VALUES (2483, 1647);
INSERT INTO `sys_user_post` VALUES (2483, 1648);
INSERT INTO `sys_user_post` VALUES (2483, 1652);
INSERT INTO `sys_user_post` VALUES (2489, 1830);
INSERT INTO `sys_user_post` VALUES (2492, 1662);
INSERT INTO `sys_user_post` VALUES (2492, 1831);
INSERT INTO `sys_user_post` VALUES (2503, 1714);
INSERT INTO `sys_user_post` VALUES (2504, 1395);
INSERT INTO `sys_user_post` VALUES (2504, 1415);
INSERT INTO `sys_user_post` VALUES (2504, 1421);
INSERT INTO `sys_user_post` VALUES (2504, 1579);
INSERT INTO `sys_user_post` VALUES (2504, 1586);
INSERT INTO `sys_user_post` VALUES (2507, 1591);
INSERT INTO `sys_user_post` VALUES (2509, 1485);
INSERT INTO `sys_user_post` VALUES (2510, 1528);
INSERT INTO `sys_user_post` VALUES (2512, 1705);
INSERT INTO `sys_user_post` VALUES (2515, 1308);
INSERT INTO `sys_user_post` VALUES (2530, 1153);
INSERT INTO `sys_user_post` VALUES (2533, 1614);
INSERT INTO `sys_user_post` VALUES (2534, 1643);
INSERT INTO `sys_user_post` VALUES (2536, 1733);
INSERT INTO `sys_user_post` VALUES (2536, 1782);
INSERT INTO `sys_user_post` VALUES (2557, 1711);
INSERT INTO `sys_user_post` VALUES (2588, 1402);
INSERT INTO `sys_user_post` VALUES (2597, 1308);
INSERT INTO `sys_user_post` VALUES (2600, 1770);
INSERT INTO `sys_user_post` VALUES (2605, 1813);
INSERT INTO `sys_user_post` VALUES (2606, 1308);
INSERT INTO `sys_user_post` VALUES (2607, 1747);
INSERT INTO `sys_user_post` VALUES (2610, 1308);
INSERT INTO `sys_user_post` VALUES (2638, 1308);
INSERT INTO `sys_user_post` VALUES (2640, 1308);
INSERT INTO `sys_user_post` VALUES (2666, 1308);
INSERT INTO `sys_user_post` VALUES (2668, 1746);
INSERT INTO `sys_user_post` VALUES (2676, 1746);
INSERT INTO `sys_user_post` VALUES (2677, 1740);
INSERT INTO `sys_user_post` VALUES (2681, 1029);
INSERT INTO `sys_user_post` VALUES (2681, 1748);
INSERT INTO `sys_user_post` VALUES (2685, 1540);
INSERT INTO `sys_user_post` VALUES (2686, 1826);
INSERT INTO `sys_user_post` VALUES (2686, 1827);
INSERT INTO `sys_user_post` VALUES (2697, 1497);
INSERT INTO `sys_user_post` VALUES (2697, 1735);
INSERT INTO `sys_user_post` VALUES (2771, 1737);
INSERT INTO `sys_user_post` VALUES (2788, 1615);
INSERT INTO `sys_user_post` VALUES (2788, 1617);
INSERT INTO `sys_user_post` VALUES (2823, 1607);
INSERT INTO `sys_user_post` VALUES (2823, 1608);
INSERT INTO `sys_user_post` VALUES (2823, 1618);
INSERT INTO `sys_user_post` VALUES (2828, 1029);
INSERT INTO `sys_user_post` VALUES (2877, 1630);
INSERT INTO `sys_user_post` VALUES (2877, 1822);
INSERT INTO `sys_user_post` VALUES (2890, 1339);
INSERT INTO `sys_user_post` VALUES (2890, 1347);
INSERT INTO `sys_user_post` VALUES (2890, 1350);
INSERT INTO `sys_user_post` VALUES (2890, 1474);
INSERT INTO `sys_user_post` VALUES (2890, 1475);
INSERT INTO `sys_user_post` VALUES (2890, 1476);
INSERT INTO `sys_user_post` VALUES (2890, 1477);
INSERT INTO `sys_user_post` VALUES (2890, 1726);
INSERT INTO `sys_user_post` VALUES (2890, 1779);
INSERT INTO `sys_user_post` VALUES (2890, 1789);
INSERT INTO `sys_user_post` VALUES (2890, 1804);
INSERT INTO `sys_user_post` VALUES (2903, 1686);
INSERT INTO `sys_user_post` VALUES (2919, 1519);
INSERT INTO `sys_user_post` VALUES (2919, 1784);
INSERT INTO `sys_user_post` VALUES (2919, 1788);
INSERT INTO `sys_user_post` VALUES (2919, 1790);
INSERT INTO `sys_user_post` VALUES (2922, 1510);
INSERT INTO `sys_user_post` VALUES (2923, 1653);
INSERT INTO `sys_user_post` VALUES (2924, 1492);
INSERT INTO `sys_user_post` VALUES (2924, 1537);
INSERT INTO `sys_user_post` VALUES (2924, 1805);
INSERT INTO `sys_user_post` VALUES (2924, 1809);
INSERT INTO `sys_user_post` VALUES (2925, 1517);
INSERT INTO `sys_user_post` VALUES (2925, 1620);
INSERT INTO `sys_user_post` VALUES (2930, 1144);
INSERT INTO `sys_user_post` VALUES (2932, 1390);
INSERT INTO `sys_user_post` VALUES (2979, 1761);
INSERT INTO `sys_user_post` VALUES (2979, 1762);
INSERT INTO `sys_user_post` VALUES (2981, 1113);
INSERT INTO `sys_user_post` VALUES (2981, 1487);
INSERT INTO `sys_user_post` VALUES (2995, 1608);
INSERT INTO `sys_user_post` VALUES (2995, 1611);
INSERT INTO `sys_user_post` VALUES (2995, 1612);
INSERT INTO `sys_user_post` VALUES (2995, 1616);
INSERT INTO `sys_user_post` VALUES (2995, 1778);
INSERT INTO `sys_user_post` VALUES (2995, 1780);
INSERT INTO `sys_user_post` VALUES (3006, 1041);
INSERT INTO `sys_user_post` VALUES (3011, 1776);
INSERT INTO `sys_user_post` VALUES (3014, 1796);
INSERT INTO `sys_user_post` VALUES (3015, 1487);
INSERT INTO `sys_user_post` VALUES (3015, 1743);
INSERT INTO `sys_user_post` VALUES (3030, 1487);
INSERT INTO `sys_user_post` VALUES (3038, 1776);
INSERT INTO `sys_user_post` VALUES (3058, 1529);
INSERT INTO `sys_user_post` VALUES (3082, 1541);
INSERT INTO `sys_user_post` VALUES (3228, 1144);
INSERT INTO `sys_user_post` VALUES (3277, 1487);
INSERT INTO `sys_user_post` VALUES (3277, 1743);
INSERT INTO `sys_user_post` VALUES (3355, 1610);
INSERT INTO `sys_user_post` VALUES (3382, 1835);
INSERT INTO `sys_user_post` VALUES (3416, 1109);
INSERT INTO `sys_user_post` VALUES (3416, 1489);
INSERT INTO `sys_user_post` VALUES (3467, 1115);
INSERT INTO `sys_user_post` VALUES (3467, 1746);
INSERT INTO `sys_user_post` VALUES (3477, 1531);
INSERT INTO `sys_user_post` VALUES (3523, 1029);
INSERT INTO `sys_user_post` VALUES (3524, 1029);
INSERT INTO `sys_user_post` VALUES (3526, 1029);
INSERT INTO `sys_user_post` VALUES (3527, 1029);
INSERT INTO `sys_user_post` VALUES (3528, 1070);
INSERT INTO `sys_user_post` VALUES (3617, 1492);
INSERT INTO `sys_user_post` VALUES (3639, 1519);
INSERT INTO `sys_user_post` VALUES (3649, 1144);
INSERT INTO `sys_user_post` VALUES (3649, 1513);
INSERT INTO `sys_user_post` VALUES (4387, 1418);
INSERT INTO `sys_user_post` VALUES (4387, 1465);
INSERT INTO `sys_user_post` VALUES (4387, 1551);
INSERT INTO `sys_user_post` VALUES (4387, 1840);
INSERT INTO `sys_user_post` VALUES (4388, 1539);
INSERT INTO `sys_user_post` VALUES (4389, 1404);
INSERT INTO `sys_user_post` VALUES (4389, 1592);
INSERT INTO `sys_user_post` VALUES (4389, 1742);
INSERT INTO `sys_user_post` VALUES (4389, 1752);
INSERT INTO `sys_user_post` VALUES (4389, 1754);
INSERT INTO `sys_user_post` VALUES (4389, 1755);
INSERT INTO `sys_user_post` VALUES (4389, 1758);
INSERT INTO `sys_user_post` VALUES (4389, 1759);
INSERT INTO `sys_user_post` VALUES (4389, 1766);
INSERT INTO `sys_user_post` VALUES (4389, 1772);
INSERT INTO `sys_user_post` VALUES (4389, 1774);
INSERT INTO `sys_user_post` VALUES (4390, 1038);
INSERT INTO `sys_user_post` VALUES (4390, 1138);
INSERT INTO `sys_user_post` VALUES (4390, 1152);
INSERT INTO `sys_user_post` VALUES (4390, 1581);
INSERT INTO `sys_user_post` VALUES (4390, 1587);
INSERT INTO `sys_user_post` VALUES (4390, 1588);
INSERT INTO `sys_user_post` VALUES (4390, 1769);
INSERT INTO `sys_user_post` VALUES (4390, 1773);
INSERT INTO `sys_user_post` VALUES (4390, 1838);
INSERT INTO `sys_user_post` VALUES (4390, 1839);
INSERT INTO `sys_user_post` VALUES (4391, 1036);
INSERT INTO `sys_user_post` VALUES (4391, 1143);
INSERT INTO `sys_user_post` VALUES (4391, 1361);
INSERT INTO `sys_user_post` VALUES (4392, 1149);
INSERT INTO `sys_user_post` VALUES (4393, 1264);
INSERT INTO `sys_user_post` VALUES (4393, 1386);
INSERT INTO `sys_user_post` VALUES (4393, 1792);
INSERT INTO `sys_user_post` VALUES (4395, 1277);
INSERT INTO `sys_user_post` VALUES (4396, 1289);
INSERT INTO `sys_user_post` VALUES (4396, 1487);
INSERT INTO `sys_user_post` VALUES (4397, 1446);
INSERT INTO `sys_user_post` VALUES (4399, 1115);
INSERT INTO `sys_user_post` VALUES (4399, 1739);
INSERT INTO `sys_user_post` VALUES (4400, 1744);
INSERT INTO `sys_user_post` VALUES (4401, 1108);
INSERT INTO `sys_user_post` VALUES (4401, 1493);
INSERT INTO `sys_user_post` VALUES (4401, 1498);
INSERT INTO `sys_user_post` VALUES (4401, 1533);
INSERT INTO `sys_user_post` VALUES (4401, 1535);
INSERT INTO `sys_user_post` VALUES (4401, 1538);
INSERT INTO `sys_user_post` VALUES (4402, 1289);
INSERT INTO `sys_user_post` VALUES (4403, 1108);
INSERT INTO `sys_user_post` VALUES (4403, 1506);
INSERT INTO `sys_user_post` VALUES (4403, 1536);
INSERT INTO `sys_user_post` VALUES (4403, 1783);
INSERT INTO `sys_user_post` VALUES (4403, 1785);
INSERT INTO `sys_user_post` VALUES (4403, 1786);
INSERT INTO `sys_user_post` VALUES (4405, 1353);
INSERT INTO `sys_user_post` VALUES (4405, 1363);
INSERT INTO `sys_user_post` VALUES (4406, 1299);
INSERT INTO `sys_user_post` VALUES (4407, 1033);
INSERT INTO `sys_user_post` VALUES (4407, 1128);
INSERT INTO `sys_user_post` VALUES (4407, 1393);
INSERT INTO `sys_user_post` VALUES (4407, 1757);
INSERT INTO `sys_user_post` VALUES (4407, 1763);
INSERT INTO `sys_user_post` VALUES (4408, 1118);
INSERT INTO `sys_user_post` VALUES (4408, 1384);
INSERT INTO `sys_user_post` VALUES (4408, 1463);
INSERT INTO `sys_user_post` VALUES (4408, 1559);
INSERT INTO `sys_user_post` VALUES (4408, 1753);
INSERT INTO `sys_user_post` VALUES (4408, 1764);
INSERT INTO `sys_user_post` VALUES (4414, 1624);
INSERT INTO `sys_user_post` VALUES (4415, 1277);
INSERT INTO `sys_user_post` VALUES (4418, 1290);
INSERT INTO `sys_user_post` VALUES (4419, 1621);
INSERT INTO `sys_user_post` VALUES (4419, 1674);
INSERT INTO `sys_user_post` VALUES (4419, 1677);
INSERT INTO `sys_user_post` VALUES (4420, 1035);
INSERT INTO `sys_user_post` VALUES (4420, 1140);
INSERT INTO `sys_user_post` VALUES (4420, 1431);
INSERT INTO `sys_user_post` VALUES (4420, 1472);
INSERT INTO `sys_user_post` VALUES (4420, 1502);
INSERT INTO `sys_user_post` VALUES (4420, 1631);
INSERT INTO `sys_user_post` VALUES (4420, 1655);
INSERT INTO `sys_user_post` VALUES (4420, 1656);
INSERT INTO `sys_user_post` VALUES (4420, 1663);
INSERT INTO `sys_user_post` VALUES (4420, 1664);
INSERT INTO `sys_user_post` VALUES (4420, 1781);
INSERT INTO `sys_user_post` VALUES (4420, 1798);
INSERT INTO `sys_user_post` VALUES (4420, 1802);
INSERT INTO `sys_user_post` VALUES (4421, 1030);
INSERT INTO `sys_user_post` VALUES (4421, 1146);
INSERT INTO `sys_user_post` VALUES (4421, 1155);
INSERT INTO `sys_user_post` VALUES (4421, 1515);
INSERT INTO `sys_user_post` VALUES (4422, 1433);
INSERT INTO `sys_user_post` VALUES (4422, 1543);
INSERT INTO `sys_user_post` VALUES (4422, 1635);
INSERT INTO `sys_user_post` VALUES (4422, 1800);
INSERT INTO `sys_user_post` VALUES (4422, 1803);
INSERT INTO `sys_user_post` VALUES (4423, 1479);
INSERT INTO `sys_user_post` VALUES (4423, 1512);
INSERT INTO `sys_user_post` VALUES (4423, 1604);
INSERT INTO `sys_user_post` VALUES (4424, 1405);
INSERT INTO `sys_user_post` VALUES (4426, 1496);
INSERT INTO `sys_user_post` VALUES (4427, 1160);
INSERT INTO `sys_user_post` VALUES (4428, 1289);
INSERT INTO `sys_user_post` VALUES (4428, 1487);
INSERT INTO `sys_user_post` VALUES (4428, 1728);
INSERT INTO `sys_user_post` VALUES (4428, 1743);
INSERT INTO `sys_user_post` VALUES (4430, 1135);
INSERT INTO `sys_user_post` VALUES (4431, 1707);
INSERT INTO `sys_user_post` VALUES (4432, 1037);
INSERT INTO `sys_user_post` VALUES (4432, 1355);
INSERT INTO `sys_user_post` VALUES (4433, 1296);
INSERT INTO `sys_user_post` VALUES (4433, 1746);
INSERT INTO `sys_user_post` VALUES (4435, 1473);
INSERT INTO `sys_user_post` VALUES (4436, 1394);
INSERT INTO `sys_user_post` VALUES (4439, 1823);
INSERT INTO `sys_user_post` VALUES (4441, 1279);
INSERT INTO `sys_user_post` VALUES (4442, 1205);
INSERT INTO `sys_user_post` VALUES (4442, 1582);
INSERT INTO `sys_user_post` VALUES (4443, 1151);
INSERT INTO `sys_user_post` VALUES (4444, 1700);
INSERT INTO `sys_user_post` VALUES (4445, 1708);
INSERT INTO `sys_user_post` VALUES (4446, 1526);
INSERT INTO `sys_user_post` VALUES (4447, 1574);
INSERT INTO `sys_user_post` VALUES (4447, 1606);
INSERT INTO `sys_user_post` VALUES (4447, 1689);
INSERT INTO `sys_user_post` VALUES (4447, 1695);
INSERT INTO `sys_user_post` VALUES (4448, 1045);
INSERT INTO `sys_user_post` VALUES (4448, 1380);
INSERT INTO `sys_user_post` VALUES (4449, 1289);
INSERT INTO `sys_user_post` VALUES (4449, 1448);
INSERT INTO `sys_user_post` VALUES (4449, 1487);
INSERT INTO `sys_user_post` VALUES (4449, 1489);
INSERT INTO `sys_user_post` VALUES (4449, 1521);
INSERT INTO `sys_user_post` VALUES (4450, 1134);
INSERT INTO `sys_user_post` VALUES (4450, 1514);
INSERT INTO `sys_user_post` VALUES (4450, 1646);
INSERT INTO `sys_user_post` VALUES (4450, 1651);
INSERT INTO `sys_user_post` VALUES (4450, 1801);
INSERT INTO `sys_user_post` VALUES (4450, 1806);
INSERT INTO `sys_user_post` VALUES (4452, 1204);
INSERT INTO `sys_user_post` VALUES (4452, 1215);
INSERT INTO `sys_user_post` VALUES (4453, 1041);
INSERT INTO `sys_user_post` VALUES (4453, 1115);
INSERT INTO `sys_user_post` VALUES (4453, 1319);
INSERT INTO `sys_user_post` VALUES (4453, 1739);
INSERT INTO `sys_user_post` VALUES (4454, 1436);
INSERT INTO `sys_user_post` VALUES (4455, 1590);
INSERT INTO `sys_user_post` VALUES (4455, 1792);
INSERT INTO `sys_user_post` VALUES (4456, 1603);
INSERT INTO `sys_user_post` VALUES (4457, 1699);
INSERT INTO `sys_user_post` VALUES (4459, 1122);
INSERT INTO `sys_user_post` VALUES (4463, 1600);
INSERT INTO `sys_user_post` VALUES (4464, 1524);
INSERT INTO `sys_user_post` VALUES (4464, 1527);
INSERT INTO `sys_user_post` VALUES (4470, 1719);
INSERT INTO `sys_user_post` VALUES (4471, 1697);
INSERT INTO `sys_user_post` VALUES (4477, 1040);
INSERT INTO `sys_user_post` VALUES (4477, 1139);
INSERT INTO `sys_user_post` VALUES (4477, 1370);
INSERT INTO `sys_user_post` VALUES (4477, 1756);
INSERT INTO `sys_user_post` VALUES (4477, 1760);
INSERT INTO `sys_user_post` VALUES (4481, 1709);
INSERT INTO `sys_user_post` VALUES (4483, 1692);
INSERT INTO `sys_user_post` VALUES (4491, 1520);
INSERT INTO `sys_user_post` VALUES (4491, 1530);
INSERT INTO `sys_user_post` VALUES (4491, 1583);
INSERT INTO `sys_user_post` VALUES (4501, 1691);
INSERT INTO `sys_user_post` VALUES (4505, 1070);
INSERT INTO `sys_user_post` VALUES (4505, 1093);
INSERT INTO `sys_user_post` VALUES (4505, 1169);
INSERT INTO `sys_user_post` VALUES (4507, 1790);
INSERT INTO `sys_user_post` VALUES (4509, 1520);
INSERT INTO `sys_user_post` VALUES (4509, 1530);
INSERT INTO `sys_user_post` VALUES (4509, 1583);
INSERT INTO `sys_user_post` VALUES (4514, 1248);
INSERT INTO `sys_user_post` VALUES (4514, 1378);
INSERT INTO `sys_user_post` VALUES (4516, 1274);
INSERT INTO `sys_user_post` VALUES (4517, 1269);
INSERT INTO `sys_user_post` VALUES (4518, 1170);
INSERT INTO `sys_user_post` VALUES (4519, 1171);
INSERT INTO `sys_user_post` VALUES (4520, 1261);
INSERT INTO `sys_user_post` VALUES (4542, 1649);
INSERT INTO `sys_user_post` VALUES (4543, 1683);
INSERT INTO `sys_user_post` VALUES (4554, 1049);
INSERT INTO `sys_user_post` VALUES (4556, 1712);
INSERT INTO `sys_user_post` VALUES (4556, 1717);
INSERT INTO `sys_user_post` VALUES (4563, 1376);
INSERT INTO `sys_user_post` VALUES (4563, 1516);
INSERT INTO `sys_user_post` VALUES (4563, 1577);
INSERT INTO `sys_user_post` VALUES (4563, 1731);
INSERT INTO `sys_user_post` VALUES (4566, 1252);
INSERT INTO `sys_user_post` VALUES (4567, 1252);
INSERT INTO `sys_user_post` VALUES (4567, 1722);
INSERT INTO `sys_user_post` VALUES (4571, 1273);
INSERT INTO `sys_user_post` VALUES (4571, 1379);
INSERT INTO `sys_user_post` VALUES (4571, 1834);
INSERT INTO `sys_user_post` VALUES (4573, 1601);
INSERT INTO `sys_user_post` VALUES (4581, 1716);
INSERT INTO `sys_user_post` VALUES (4584, 1688);
INSERT INTO `sys_user_post` VALUES (4587, 1685);
INSERT INTO `sys_user_post` VALUES (4588, 1690);
INSERT INTO `sys_user_post` VALUES (4589, 1696);
INSERT INTO `sys_user_post` VALUES (4591, 1373);
INSERT INTO `sys_user_post` VALUES (4591, 1486);
INSERT INTO `sys_user_post` VALUES (4591, 1503);
INSERT INTO `sys_user_post` VALUES (4592, 1269);
INSERT INTO `sys_user_post` VALUES (4594, 1285);
INSERT INTO `sys_user_post` VALUES (4594, 1812);
INSERT INTO `sys_user_post` VALUES (4596, 1721);
INSERT INTO `sys_user_post` VALUES (4599, 1127);
INSERT INTO `sys_user_post` VALUES (4600, 1481);
INSERT INTO `sys_user_post` VALUES (4600, 1554);
INSERT INTO `sys_user_post` VALUES (4603, 1129);
INSERT INTO `sys_user_post` VALUES (4603, 1544);
INSERT INTO `sys_user_post` VALUES (4604, 1113);
INSERT INTO `sys_user_post` VALUES (4604, 1487);
INSERT INTO `sys_user_post` VALUES (4604, 1489);
INSERT INTO `sys_user_post` VALUES (4604, 1729);
INSERT INTO `sys_user_post` VALUES (4605, 1109);
INSERT INTO `sys_user_post` VALUES (4605, 1489);
INSERT INTO `sys_user_post` VALUES (4607, 1580);
INSERT INTO `sys_user_post` VALUES (4607, 1625);
INSERT INTO `sys_user_post` VALUES (4607, 1626);
INSERT INTO `sys_user_post` VALUES (4607, 1793);
INSERT INTO `sys_user_post` VALUES (4608, 1810);
INSERT INTO `sys_user_post` VALUES (4609, 1114);
INSERT INTO `sys_user_post` VALUES (4610, 1119);
INSERT INTO `sys_user_post` VALUES (4610, 1366);
INSERT INTO `sys_user_post` VALUES (4610, 1487);
INSERT INTO `sys_user_post` VALUES (4611, 1092);
INSERT INTO `sys_user_post` VALUES (4611, 1111);
INSERT INTO `sys_user_post` VALUES (4611, 1836);
INSERT INTO `sys_user_post` VALUES (4611, 1837);
INSERT INTO `sys_user_post` VALUES (4612, 1275);
INSERT INTO `sys_user_post` VALUES (4613, 1371);
INSERT INTO `sys_user_post` VALUES (4613, 1377);
INSERT INTO `sys_user_post` VALUES (4613, 1381);
INSERT INTO `sys_user_post` VALUES (4613, 1484);
INSERT INTO `sys_user_post` VALUES (4613, 1542);
INSERT INTO `sys_user_post` VALUES (4613, 1584);
INSERT INTO `sys_user_post` VALUES (4613, 1634);
INSERT INTO `sys_user_post` VALUES (4613, 1650);
INSERT INTO `sys_user_post` VALUES (4613, 1842);
INSERT INTO `sys_user_post` VALUES (4613, 1843);
INSERT INTO `sys_user_post` VALUES (4614, 1532);
INSERT INTO `sys_user_post` VALUES (4614, 1724);
INSERT INTO `sys_user_post` VALUES (4614, 1727);
INSERT INTO `sys_user_post` VALUES (4614, 1732);
INSERT INTO `sys_user_post` VALUES (4614, 1788);
INSERT INTO `sys_user_post` VALUES (4616, 1661);
INSERT INTO `sys_user_post` VALUES (4616, 1670);
INSERT INTO `sys_user_post` VALUES (4617, 1818);
INSERT INTO `sys_user_post` VALUES (4619, 1576);
INSERT INTO `sys_user_post` VALUES (4619, 1628);
INSERT INTO `sys_user_post` VALUES (4619, 1741);
INSERT INTO `sys_user_post` VALUES (4622, 1277);
INSERT INTO `sys_user_post` VALUES (4625, 1113);
INSERT INTO `sys_user_post` VALUES (4625, 1487);
INSERT INTO `sys_user_post` VALUES (4625, 1489);
INSERT INTO `sys_user_post` VALUES (4626, 1814);
INSERT INTO `sys_user_post` VALUES (4627, 1824);
INSERT INTO `sys_user_post` VALUES (4630, 1125);
INSERT INTO `sys_user_post` VALUES (4637, 1117);
INSERT INTO `sys_user_post` VALUES (4638, 1105);
INSERT INTO `sys_user_post` VALUES (4640, 1121);
INSERT INTO `sys_user_post` VALUES (4640, 1815);
INSERT INTO `sys_user_post` VALUES (4641, 1682);
INSERT INTO `sys_user_post` VALUES (4646, 1715);
INSERT INTO `sys_user_post` VALUES (4648, 1668);
INSERT INTO `sys_user_post` VALUES (4648, 1675);
INSERT INTO `sys_user_post` VALUES (4648, 1676);
INSERT INTO `sys_user_post` VALUES (4650, 1400);
INSERT INTO `sys_user_post` VALUES (4651, 1599);
INSERT INTO `sys_user_post` VALUES (4654, 1702);
INSERT INTO `sys_user_post` VALUES (4658, 1162);
INSERT INTO `sys_user_post` VALUES (4658, 1545);
INSERT INTO `sys_user_post` VALUES (4658, 1777);
INSERT INTO `sys_user_post` VALUES (4659, 1161);
INSERT INTO `sys_user_post` VALUES (4660, 1713);
INSERT INTO `sys_user_post` VALUES (4667, 1744);
INSERT INTO `sys_user_post` VALUES (4669, 1737);
INSERT INTO `sys_user_post` VALUES (4672, 1639);
INSERT INTO `sys_user_post` VALUES (4672, 1642);
INSERT INTO `sys_user_post` VALUES (4673, 1252);
INSERT INTO `sys_user_post` VALUES (4674, 1314);
INSERT INTO `sys_user_post` VALUES (4676, 1039);
INSERT INTO `sys_user_post` VALUES (4676, 1356);
INSERT INTO `sys_user_post` VALUES (4676, 1791);
INSERT INTO `sys_user_post` VALUES (4678, 1252);
INSERT INTO `sys_user_post` VALUES (4680, 1117);
INSERT INTO `sys_user_post` VALUES (4684, 1316);
INSERT INTO `sys_user_post` VALUES (4692, 1666);
INSERT INTO `sys_user_post` VALUES (4698, 1662);
INSERT INTO `sys_user_post` VALUES (4698, 1672);
INSERT INTO `sys_user_post` VALUES (4703, 1308);
INSERT INTO `sys_user_post` VALUES (4707, 1466);
INSERT INTO `sys_user_post` VALUES (4708, 1308);
INSERT INTO `sys_user_post` VALUES (4708, 1372);
INSERT INTO `sys_user_post` VALUES (4710, 1307);
INSERT INTO `sys_user_post` VALUES (4710, 1471);
INSERT INTO `sys_user_post` VALUES (4710, 1633);
INSERT INTO `sys_user_post` VALUES (4710, 1638);
INSERT INTO `sys_user_post` VALUES (4710, 1645);
INSERT INTO `sys_user_post` VALUES (4710, 1667);
INSERT INTO `sys_user_post` VALUES (4714, 1513);
INSERT INTO `sys_user_post` VALUES (4719, 1028);
INSERT INTO `sys_user_post` VALUES (4721, 1821);
INSERT INTO `sys_user_post` VALUES (4723, 1454);
INSERT INTO `sys_user_post` VALUES (4724, 1529);
INSERT INTO `sys_user_post` VALUES (4726, 1704);
INSERT INTO `sys_user_post` VALUES (4727, 1745);
INSERT INTO `sys_user_post` VALUES (4728, 1747);
INSERT INTO `sys_user_post` VALUES (4730, 1745);
INSERT INTO `sys_user_post` VALUES (4733, 1308);
INSERT INTO `sys_user_post` VALUES (4748, 1698);
INSERT INTO `sys_user_post` VALUES (4748, 1833);
INSERT INTO `sys_user_post` VALUES (4777, 1826);
INSERT INTO `sys_user_post` VALUES (4777, 1827);
INSERT INTO `sys_user_post` VALUES (4786, 1170);
INSERT INTO `sys_user_post` VALUES (4788, 1168);
INSERT INTO `sys_user_post` VALUES (4788, 1172);
INSERT INTO `sys_user_post` VALUES (4788, 1346);
INSERT INTO `sys_user_post` VALUES (4788, 1593);
INSERT INTO `sys_user_post` VALUES (4793, 1640);
INSERT INTO `sys_user_post` VALUES (4794, 1150);
INSERT INTO `sys_user_post` VALUES (4794, 1768);
INSERT INTO `sys_user_post` VALUES (4794, 1770);
INSERT INTO `sys_user_post` VALUES (4796, 1359);
INSERT INTO `sys_user_post` VALUES (4796, 1548);
INSERT INTO `sys_user_post` VALUES (4796, 1550);
INSERT INTO `sys_user_post` VALUES (4796, 1636);
INSERT INTO `sys_user_post` VALUES (4797, 1367);
INSERT INTO `sys_user_post` VALUES (4797, 1560);
INSERT INTO `sys_user_post` VALUES (4798, 1745);
INSERT INTO `sys_user_post` VALUES (4803, 1262);
INSERT INTO `sys_user_post` VALUES (4805, 1141);
INSERT INTO `sys_user_post` VALUES (4805, 1374);
INSERT INTO `sys_user_post` VALUES (4805, 1518);
INSERT INTO `sys_user_post` VALUES (4805, 1585);
INSERT INTO `sys_user_post` VALUES (4806, 1832);
INSERT INTO `sys_user_post` VALUES (4810, 1520);
INSERT INTO `sys_user_post` VALUES (4810, 1530);
INSERT INTO `sys_user_post` VALUES (4810, 1583);
INSERT INTO `sys_user_post` VALUES (4814, 1511);
INSERT INTO `sys_user_post` VALUES (4814, 1622);
INSERT INTO `sys_user_post` VALUES (4816, 1157);
INSERT INTO `sys_user_post` VALUES (4816, 1360);
INSERT INTO `sys_user_post` VALUES (4816, 1522);
INSERT INTO `sys_user_post` VALUES (4816, 1736);
INSERT INTO `sys_user_post` VALUES (4816, 1751);
INSERT INTO `sys_user_post` VALUES (4817, 1523);
INSERT INTO `sys_user_post` VALUES (4817, 1657);
INSERT INTO `sys_user_post` VALUES (4817, 1660);
INSERT INTO `sys_user_post` VALUES (4817, 1673);
INSERT INTO `sys_user_post` VALUES (4817, 1679);
INSERT INTO `sys_user_post` VALUES (4825, 1046);
INSERT INTO `sys_user_post` VALUES (4825, 1266);
INSERT INTO `sys_user_post` VALUES (4825, 1738);
INSERT INTO `sys_user_post` VALUES (4827, 1308);
INSERT INTO `sys_user_post` VALUES (4828, 1529);
INSERT INTO `sys_user_post` VALUES (4829, 1483);
INSERT INTO `sys_user_post` VALUES (4829, 1490);
INSERT INTO `sys_user_post` VALUES (4829, 1504);
INSERT INTO `sys_user_post` VALUES (4829, 1534);
INSERT INTO `sys_user_post` VALUES (4829, 1594);
INSERT INTO `sys_user_post` VALUES (4829, 1807);
INSERT INTO `sys_user_post` VALUES (4830, 1308);
INSERT INTO `sys_user_post` VALUES (4844, 1564);
INSERT INTO `sys_user_post` VALUES (4844, 1565);
INSERT INTO `sys_user_post` VALUES (4849, 1629);
INSERT INTO `sys_user_post` VALUES (4875, 1710);
INSERT INTO `sys_user_post` VALUES (4886, 1557);
INSERT INTO `sys_user_post` VALUES (4886, 1570);
INSERT INTO `sys_user_post` VALUES (4886, 1571);
INSERT INTO `sys_user_post` VALUES (4886, 1573);
INSERT INTO `sys_user_post` VALUES (4890, 1520);
INSERT INTO `sys_user_post` VALUES (4890, 1530);
INSERT INTO `sys_user_post` VALUES (4890, 1583);
INSERT INTO `sys_user_post` VALUES (4890, 1825);
INSERT INTO `sys_user_post` VALUES (4892, 1787);
INSERT INTO `sys_user_post` VALUES (4894, 1487);
INSERT INTO `sys_user_post` VALUES (4897, 1509);
INSERT INTO `sys_user_post` VALUES (4904, 1475);
INSERT INTO `sys_user_post` VALUES (4904, 1829);
INSERT INTO `sys_user_post` VALUES (4913, 1771);
INSERT INTO `sys_user_post` VALUES (4921, 1489);
INSERT INTO `sys_user_post` VALUES (4921, 1530);
INSERT INTO `sys_user_post` VALUES (4921, 1825);
INSERT INTO `sys_user_post` VALUES (4924, 1489);
INSERT INTO `sys_user_post` VALUES (4924, 1530);
INSERT INTO `sys_user_post` VALUES (4924, 1825);
INSERT INTO `sys_user_post` VALUES (4927, 1555);
INSERT INTO `sys_user_post` VALUES (4927, 1811);
INSERT INTO `sys_user_post` VALUES (4942, 1558);
INSERT INTO `sys_user_post` VALUES (4942, 1562);
INSERT INTO `sys_user_post` VALUES (4942, 1563);
INSERT INTO `sys_user_post` VALUES (4942, 1572);
INSERT INTO `sys_user_post` VALUES (4948, 1831);
INSERT INTO `sys_user_post` VALUES (4949, 1553);
INSERT INTO `sys_user_post` VALUES (4949, 1647);
INSERT INTO `sys_user_post` VALUES (4949, 1648);
INSERT INTO `sys_user_post` VALUES (4949, 1652);
INSERT INTO `sys_user_post` VALUES (4951, 1830);
INSERT INTO `sys_user_post` VALUES (4955, 1395);
INSERT INTO `sys_user_post` VALUES (4955, 1415);
INSERT INTO `sys_user_post` VALUES (4955, 1421);
INSERT INTO `sys_user_post` VALUES (4955, 1579);
INSERT INTO `sys_user_post` VALUES (4955, 1586);
INSERT INTO `sys_user_post` VALUES (4957, 1485);
INSERT INTO `sys_user_post` VALUES (4958, 1714);
INSERT INTO `sys_user_post` VALUES (4959, 1591);
INSERT INTO `sys_user_post` VALUES (4960, 1528);
INSERT INTO `sys_user_post` VALUES (4961, 1705);
INSERT INTO `sys_user_post` VALUES (4962, 1308);
INSERT INTO `sys_user_post` VALUES (4970, 1643);
INSERT INTO `sys_user_post` VALUES (4971, 1733);
INSERT INTO `sys_user_post` VALUES (4971, 1782);
INSERT INTO `sys_user_post` VALUES (4972, 1614);
INSERT INTO `sys_user_post` VALUES (4973, 1153);
INSERT INTO `sys_user_post` VALUES (4980, 1711);
INSERT INTO `sys_user_post` VALUES (4991, 1308);
INSERT INTO `sys_user_post` VALUES (4992, 1402);
INSERT INTO `sys_user_post` VALUES (4995, 1770);
INSERT INTO `sys_user_post` VALUES (4996, 1747);
INSERT INTO `sys_user_post` VALUES (4998, 1308);
INSERT INTO `sys_user_post` VALUES (5001, 1813);
INSERT INTO `sys_user_post` VALUES (5004, 1308);
INSERT INTO `sys_user_post` VALUES (5012, 1308);
INSERT INTO `sys_user_post` VALUES (5013, 1308);
INSERT INTO `sys_user_post` VALUES (5023, 1746);
INSERT INTO `sys_user_post` VALUES (5026, 1746);
INSERT INTO `sys_user_post` VALUES (5027, 1308);
INSERT INTO `sys_user_post` VALUES (5028, 1029);
INSERT INTO `sys_user_post` VALUES (5028, 1748);
INSERT INTO `sys_user_post` VALUES (5032, 1826);
INSERT INTO `sys_user_post` VALUES (5032, 1827);
INSERT INTO `sys_user_post` VALUES (5033, 1497);
INSERT INTO `sys_user_post` VALUES (5033, 1735);
INSERT INTO `sys_user_post` VALUES (5034, 1540);
INSERT INTO `sys_user_post` VALUES (5061, 1737);
INSERT INTO `sys_user_post` VALUES (5066, 1615);
INSERT INTO `sys_user_post` VALUES (5066, 1617);
INSERT INTO `sys_user_post` VALUES (5088, 1607);
INSERT INTO `sys_user_post` VALUES (5088, 1608);
INSERT INTO `sys_user_post` VALUES (5088, 1618);
INSERT INTO `sys_user_post` VALUES (5089, 1029);
INSERT INTO `sys_user_post` VALUES (5117, 1630);
INSERT INTO `sys_user_post` VALUES (5117, 1822);
INSERT INTO `sys_user_post` VALUES (5118, 1339);
INSERT INTO `sys_user_post` VALUES (5118, 1347);
INSERT INTO `sys_user_post` VALUES (5118, 1350);
INSERT INTO `sys_user_post` VALUES (5118, 1474);
INSERT INTO `sys_user_post` VALUES (5118, 1475);
INSERT INTO `sys_user_post` VALUES (5118, 1476);
INSERT INTO `sys_user_post` VALUES (5118, 1477);
INSERT INTO `sys_user_post` VALUES (5118, 1726);
INSERT INTO `sys_user_post` VALUES (5118, 1779);
INSERT INTO `sys_user_post` VALUES (5118, 1789);
INSERT INTO `sys_user_post` VALUES (5118, 1804);
INSERT INTO `sys_user_post` VALUES (5122, 1686);
INSERT INTO `sys_user_post` VALUES (5132, 1653);
INSERT INTO `sys_user_post` VALUES (5133, 1510);
INSERT INTO `sys_user_post` VALUES (5134, 1517);
INSERT INTO `sys_user_post` VALUES (5134, 1620);
INSERT INTO `sys_user_post` VALUES (5135, 1144);
INSERT INTO `sys_user_post` VALUES (5143, 1390);
INSERT INTO `sys_user_post` VALUES (5152, 1113);
INSERT INTO `sys_user_post` VALUES (5152, 1487);
INSERT INTO `sys_user_post` VALUES (5155, 1761);
INSERT INTO `sys_user_post` VALUES (5155, 1762);
INSERT INTO `sys_user_post` VALUES (5166, 1608);
INSERT INTO `sys_user_post` VALUES (5166, 1611);
INSERT INTO `sys_user_post` VALUES (5166, 1612);
INSERT INTO `sys_user_post` VALUES (5166, 1616);
INSERT INTO `sys_user_post` VALUES (5166, 1778);
INSERT INTO `sys_user_post` VALUES (5166, 1780);
INSERT INTO `sys_user_post` VALUES (5177, 1776);
INSERT INTO `sys_user_post` VALUES (5178, 1041);
INSERT INTO `sys_user_post` VALUES (5180, 1487);
INSERT INTO `sys_user_post` VALUES (5180, 1743);
INSERT INTO `sys_user_post` VALUES (5181, 1796);
INSERT INTO `sys_user_post` VALUES (5185, 1487);
INSERT INTO `sys_user_post` VALUES (5190, 1776);
INSERT INTO `sys_user_post` VALUES (5205, 1529);
INSERT INTO `sys_user_post` VALUES (5221, 1541);
INSERT INTO `sys_user_post` VALUES (5316, 1144);
INSERT INTO `sys_user_post` VALUES (5322, 1487);
INSERT INTO `sys_user_post` VALUES (5322, 1743);
INSERT INTO `sys_user_post` VALUES (5370, 1610);
INSERT INTO `sys_user_post` VALUES (5391, 1835);
INSERT INTO `sys_user_post` VALUES (5395, 1109);
INSERT INTO `sys_user_post` VALUES (5395, 1489);
INSERT INTO `sys_user_post` VALUES (5431, 1115);
INSERT INTO `sys_user_post` VALUES (5431, 1746);
INSERT INTO `sys_user_post` VALUES (5456, 1531);
INSERT INTO `sys_user_post` VALUES (5567, 1492);
INSERT INTO `sys_user_post` VALUES (5594, 1519);
INSERT INTO `sys_user_post` VALUES (5599, 1144);
INSERT INTO `sys_user_post` VALUES (5599, 1513);
INSERT INTO `sys_user_post` VALUES (5640, 1029);
INSERT INTO `sys_user_post` VALUES (5650, 1581);
INSERT INTO `sys_user_post` VALUES (5652, 1100);
INSERT INTO `sys_user_post` VALUES (5653, 1101);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (1001, 1);
INSERT INTO `sys_user_role` VALUES (1004, 2);
INSERT INTO `sys_user_role` VALUES (1005, 2);
INSERT INTO `sys_user_role` VALUES (1006, 2);
INSERT INTO `sys_user_role` VALUES (1007, 2);
INSERT INTO `sys_user_role` VALUES (1020, 3);
INSERT INTO `sys_user_role` VALUES (1049, 3);
INSERT INTO `sys_user_role` VALUES (3523, 3);
INSERT INTO `sys_user_role` VALUES (3524, 2);
INSERT INTO `sys_user_role` VALUES (3524, 3);
INSERT INTO `sys_user_role` VALUES (3525, 3);
INSERT INTO `sys_user_role` VALUES (3527, 3);
INSERT INTO `sys_user_role` VALUES (3528, 3);
INSERT INTO `sys_user_role` VALUES (3530, 3);
INSERT INTO `sys_user_role` VALUES (4394, 3);
INSERT INTO `sys_user_role` VALUES (4395, 3);
INSERT INTO `sys_user_role` VALUES (4400, 3);
INSERT INTO `sys_user_role` VALUES (4424, 3);
INSERT INTO `sys_user_role` VALUES (4436, 3);
INSERT INTO `sys_user_role` VALUES (4439, 3);
INSERT INTO `sys_user_role` VALUES (4490, 3);
INSERT INTO `sys_user_role` VALUES (4579, 3);
INSERT INTO `sys_user_role` VALUES (4594, 3);
INSERT INTO `sys_user_role` VALUES (4608, 3);
INSERT INTO `sys_user_role` VALUES (4879, 3);
INSERT INTO `sys_user_role` VALUES (5640, 3);
INSERT INTO `sys_user_role` VALUES (5641, 3);
INSERT INTO `sys_user_role` VALUES (5642, 3);
INSERT INTO `sys_user_role` VALUES (5643, 3);
INSERT INTO `sys_user_role` VALUES (5644, 3);
INSERT INTO `sys_user_role` VALUES (5645, 3);
INSERT INTO `sys_user_role` VALUES (5647, 3);
INSERT INTO `sys_user_role` VALUES (5648, 3);
INSERT INTO `sys_user_role` VALUES (5649, 3);
INSERT INTO `sys_user_role` VALUES (5650, 3);
INSERT INTO `sys_user_role` VALUES (5651, 2);
INSERT INTO `sys_user_role` VALUES (5651, 3);
INSERT INTO `sys_user_role` VALUES (5653, 3);
INSERT INTO `sys_user_role` VALUES (5654, 3);
INSERT INTO `sys_user_role` VALUES (5655, 3);
INSERT INTO `sys_user_role` VALUES (5656, 1009);

SET FOREIGN_KEY_CHECKS = 1;
