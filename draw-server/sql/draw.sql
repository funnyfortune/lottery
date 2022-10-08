DROP TABLE IF EXISTS `draw_theme`;
CREATE TABLE `draw_theme` (
`id`          bigint(11) NOT NULL AUTO_INCREMENT          COMMENT '主键ID',
`name`          varchar(256) DEFAULT ''          COMMENT '活动名称',
`draw_type`        tinyint(1) DEFAULT '0'          COMMENT '0随机，1概率',
  `is_delete`   tinyint(1) DEFAULT '0'                      COMMENT '是否删除',
`creator_id`  bigint(11) DEFAULT '0'                      COMMENT '创建者id',
  `operator_id` bigint(11) DEFAULT '0'                      COMMENT '最后操作者id',
  `create_time` bigint(11) DEFAULT '0'                      COMMENT '创建时间',
  `update_time` bigint(11) DEFAULT '0'                      COMMENT '修改时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='抽奖主题表';

DROP TABLE IF EXISTS `draw_gift`;
CREATE TABLE `draw_gift` (
`id`          bigint(11) NOT NULL AUTO_INCREMENT          COMMENT '主键ID',
`theme_id`    bigint(11) DEFAULT '0'                      COMMENT '主题Id',
`name`          varchar(256) DEFAULT ''                     COMMENT '名称',
`num`             int(11) DEFAULT '0'                       COMMENT '数量',
`completed`        tinyint(1) DEFAULT '0'                    COMMENT '0未完成，1完成',
`complete_time` bigint(11) DEFAULT '0'               COMMENT '时间',
`creator_id`  bigint(11) DEFAULT '0'                      COMMENT '创建者id',
`create_time` bigint(11) DEFAULT '0'               COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='抽奖礼品表';

DROP TABLE IF EXISTS `draw_customer`;
CREATE TABLE `draw_customer` (
  `id`          bigint(11) NOT NULL AUTO_INCREMENT          COMMENT '主键ID',
  `company`        varchar(50) DEFAULT ''                      COMMENT '公司',
  `name`        varchar(50) DEFAULT ''                      COMMENT '名称',
  `mobile`      varchar(50) DEFAULT ''                    COMMENT '手机',
  `probability`  double(10,3) DEFAULT 0               COMMENT '概率',
  `status`      char(1) DEFAULT '0'                         COMMENT '状态（0=正常,1=停用）',
  `remark`      varchar(256) DEFAULT ''                     COMMENT '备注',
  `is_delete`   tinyint(1) DEFAULT '0'                      COMMENT '是否删除',
  `theme_id`  bigint(11) DEFAULT '0'                      COMMENT '主题id',
  `creator_id`  bigint(11) DEFAULT '0'                      COMMENT '创建者id',
  `operator_id` bigint(11) DEFAULT '0'                      COMMENT '最后操作者id',
  `create_time` bigint(11) DEFAULT '0'                      COMMENT '创建时间',
  `update_time` bigint(11) DEFAULT '0'                      COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='抽奖客户表';

DROP TABLE IF EXISTS `draw_result`;
CREATE TABLE `draw_result` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theme_id` bigint(11) NOT NULL COMMENT '主题ID',
  `gift_id` bigint(11) NOT NULL COMMENT '奖项ID',
  `customer_id` bigint(11) NOT NULL COMMENT '客户表Id',
  `create_time` bigint(11) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COMMENT='抽奖中奖表';


DROP TABLE IF EXISTS `draw_action`;
CREATE TABLE `draw_action` (
`id`          bigint(11) NOT NULL AUTO_INCREMENT          COMMENT '主键ID',
`theme_id`  bigint(11) DEFAULT '0'                      COMMENT '主题Id',
`gift_id`  bigint(11) DEFAULT '0'                      COMMENT '主题奖项Id',
`creator_id`  bigint(11) DEFAULT '0'                      COMMENT '创建者id',
`create_time` bigint(11) DEFAULT '0'               COMMENT '创建时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='抽奖行为表';
