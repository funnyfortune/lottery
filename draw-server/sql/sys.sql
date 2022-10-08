



-- ----------------------------
-- 菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    COMMENT '菜单ID',
  menu_name         varchar(50)     not null                   COMMENT '菜单名称',
  parent_id         bigint(20)      DEFAULT 0                  COMMENT '父菜单ID',
  order_num         int(4)          DEFAULT 0                  COMMENT '显示顺序',
  path              varchar(200)    DEFAULT ''                 COMMENT '路由地址',
  component         varchar(255)    DEFAULT null               COMMENT '组件路径',
  is_frame          int(1)          DEFAULT 1                  COMMENT '是否为外链（0是 1否）',
  menu_type         char(1)         DEFAULT ''                 COMMENT '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         DEFAULT 0                  COMMENT '菜单状态（0显示 1隐藏）',
  status            char(1)         DEFAULT 0                  COMMENT '菜单状态（0正常 1停用）',
  perms             varchar(100)    DEFAULT null               COMMENT '权限标识',
  icon              varchar(100)    DEFAULT '#'                COMMENT '菜单图标',
  creator_id        bigint(11)      DEFAULT 0                  COMMENT  '操作id',
  create_time       bigint(11)      DEFAULT 0                  COMMENT '创建时间',
  operator_id       bigint(11)      DEFAULT 0                  COMMENT  '最后操作id',
  update_time       bigint(11)      DEFAULT 0                  COMMENT '更新时间',
  remark            varchar(500)    DEFAULT ''                 COMMENT '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------



-- ----------------------------
-- 字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          	    bigint(20)       not null auto_increment         COMMENT '字典主键',
  dict_name        		varchar(100)     default ''                 	 COMMENT '字典名称',
  dict_type        		varchar(100)     default ''                 	 COMMENT '字典类型',
  status           		char(1)          default '0'                     COMMENT '状态（0正常 1停用）',
  is_delete             tinyint(1)       DEFAULT 0   				     COMMENT '是否删除',
  create_time           bigint(11)       DEFAULT 0                       COMMENT '创建时间',
  update_time           bigint(11)       DEFAULT 0                       COMMENT '修改时间',
  creator_id            bigint(11)       DEFAULT 0                       COMMENT '用户id',
  operator_id           bigint(11)       DEFAULT 0                       COMMENT '最后操作id',
  remark                varchar(500)     default ''                      COMMENT '备注',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

-- ----------------------------
-- 字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code             bigint(11)       not null auto_increment          COMMENT '字典编码',
  dict_sort             int(4)           DEFAULT 0                        COMMENT '字典排序',
  dict_label            varchar(100)     DEFAULT ''                       COMMENT '字典标签',
  dict_value            varchar(100)     DEFAULT ''                       COMMENT '字典键值',
  dict_type             varchar(100)     DEFAULT ''                       COMMENT '字典类型',
  css_class             varchar(100)     DEFAULT ''                       COMMENT '样式属性（其他样式扩展）',
  list_class            varchar(100)     DEFAULT ''                       COMMENT '表格回显样式',
  is_default            char(1)          DEFAULT 'N'                      COMMENT '是否默认（Y是 N否）',
  status                char(1)          DEFAULT '0'                      COMMENT '状态（0正常 1停用）',
  is_delete             tinyint(1)       DEFAULT 0   				     COMMENT '是否删除',
  create_time           bigint(11)       DEFAULT 0                       COMMENT '创建时间',
  update_time           bigint(11)       DEFAULT 0                       COMMENT '修改时间',
  creator_id            bigint(11)       DEFAULT 0                       COMMENT '用户id',
  operator_id           bigint(11)       DEFAULT 0                       COMMENT '最后操作id',
  remark                varchar(500)     DEFAULT ''                       COMMENT '备注',
  primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';




drop table if exists sys_config;
create table sys_config (
  config_id             bigint(11)       not null auto_increment         COMMENT '参数主键',
  config_name           varchar(100)     DEFAULT ''                      COMMENT '参数名称',
  config_key            varchar(100)     DEFAULT ''                      COMMENT '参数键名',
  config_value          varchar(500)     DEFAULT ''                      COMMENT '参数键值',
  config_type		    char(1)          DEFAULT '0'                	 COMMENT '系统内置（Y是 N否）',
  value_type            char(1)          DEFAULT 'N'                     COMMENT '值类型（0文本 1json）',
  is_delete             tinyint(1)       DEFAULT 0   				     COMMENT '是否删除',
  create_time           bigint(11)       DEFAULT 0                       COMMENT '创建时间',
  update_time           bigint(11)       DEFAULT 0                       COMMENT '修改时间',
  creator_id            bigint(11)       DEFAULT 0                       COMMENT '用户id',
  operator_id           bigint(11)       DEFAULT 0                       COMMENT '最后操作id',
  remark                varchar(500)     DEFAULT ''                      COMMENT '备注',
  primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';


