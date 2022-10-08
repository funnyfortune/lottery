

-- ----------------------------
-- 2、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  id 				bigint(11)      NOT NULL  AUTO_INCREMENT   COMMENT '主键ID',
  ancestors         varchar(50)     DEFAULT ''                 COMMENT '祖级列表',
  phone             varchar(11)     DEFAULT ''                 COMMENT '联系电话',
  status            char(1)         DEFAULT '0'                COMMENT '部门状态（0正常 1停用）',
  email             varchar(256)     default ''                 COMMENT '邮箱',
  dept_name         varchar(256)     DEFAULT ''                 COMMENT '部门名称',
  order_num         int(4)          DEFAULT 0                  COMMENT '显示顺序',
  leader_id            bigint(11)     DEFAULT 0                 COMMENT '负责人',
  is_delete			tinyint(1)      DEFAULT 0                  COMMENT '是否有效',
  memo              varchar(1500)   DEFAULT ''                 COMMENT '备注',
  create_time       bigint(11)      DEFAULT 0                  COMMENT '创建时间',
  creator_id        bigint(11)  	DEFAULT 0                  COMMENT '组织表id',
  operator_id       bigint(11) 	    DEFAULT 0                  COMMENT '最后操作id',
  update_time       bigint(11)      DEFAULT 0                  COMMENT '修改时间',
  parent_id         bigint(11)      DEFAULT 0                  COMMENT '所在部门',
  name_pinyin       varchar(200)    DEFAULT ''                 COMMENT '拼音名称',
  primary key (id)
) engine=innodb auto_increment=200 COMMENT = '部门表';



-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------

-- ----------------------------
-- 3、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  id                bigint(11)      not null auto_increment    COMMENT '用户ID',
  user_type         varchar(2)      DEFAULT '00'               COMMENT '用户类型（00系统用户）',
  avatar            varchar(100)    DEFAULT ''                 COMMENT '头像地址',
  status            char(1)         DEFAULT '0'                COMMENT '帐号状态（0正常 1停用）',
  login_ip          varchar(50)     DEFAULT ''                 COMMENT '最后登陆IP',
  login_date        bigint(11)      DEFAULT 0                  COMMENT '登录时间',
  remark            varchar(500)    DEFAULT ''                 COMMENT '备注',
  name              varchar(256)    DEFAULT ''                 COMMENT '名称',
  is_delete         tinyint(1)      DEFAULT 0                  COMMENT '是否删除',
  login_name        varchar(256)    DEFAULT ''                 COMMENT '登录名',
  password          varchar(256) 	DEFAULT '' 			       COMMENT '登录密码',
  sex               varchar(256) 	DEFAULT '' 			       COMMENT '性别',
  entry_time 		bigint(11) 		DEFAULT 0 			       COMMENT '入职时间',
  work_number 		varchar(256) 	DEFAULT '' 			       COMMENT '工号',
  org_email 		varchar(256) 	DEFAULT '' 			       COMMENT '邮件地址',
  hierarchy_id 		varchar(450)	DEFAULT '' 			       COMMENT '底层id',
  create_time		bigint(11)      DEFAULT 0                  COMMENT '创建时间',
  update_time		bigint(11)      DEFAULT 0                  COMMENT '修改时间',
  creator_id 		bigint(11)      DEFAULT 0                  COMMENT '组织表id',
  operator_id 		bigint(11)      DEFAULT 0                  COMMENT '最后操作id',
  dept_id			varchar(450)    DEFAULT 0                  COMMENT '所在部门',
  name_pinyin 		varchar(200)    DEFAULT ''                 COMMENT '拼音名称',
  mobile_no			varchar(100)    DEFAULT ''                 COMMENT '手机号',
  primary key (id)
) engine=innodb auto_increment=1000 COMMENT = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
INSERT INTO `sys_user`(`id`, `user_type`, `avatar`, `status`, `login_ip`, `login_date`, `remark`,  `name`, `is_delete`, `login_name`, `password`, `sex`, `entry_time`, `work_number`, `org_email`, `memo`, `hierarchy_id`, `create_time`, `update_time`, `creator_id`, `operator_id`, `this_leaderid`, `super_leaderid`, `parentorgid`, `parentid`, `name_pinyin`, `mobile_no`,`org_id`) VALUES (1, '00', '', '1', '0.0.0.0', NULL, NULL, '超级管理员', 0, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '3', '0', '0', 'adminmadmin.con', '', ' ', 0, 0, 0, 0, '0', '0', '0', '0', 'chaojiguanliyuan', '0000',1);
-- ----------------------------
-- 4、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  id            bigint(11)      NOT NULL  AUTO_INCREMENT   COMMENT '主键ID',
  post_code     varchar(256)     NOT NULL  DEFAULT ''       COMMENT '岗位编码',
  post_sort     int(4)          NOT NULL  DEFAULT 0        COMMENT '显示顺序',
  status        char(1)         NOT NULL  DEFAULT '0'      COMMENT '状态（0正常 1停用）',
  creator_id    bigint(11)  			  DEFAULT 0 	   COMMENT  '创建者',
  operator_id    bigint(11)               DEFAULT 0        COMMENT  '最后操作id',
  is_delete     tinyint(1)                DEFAULT 0        COMMENT '是否删除',
  post_name     varchar(256)              DEFAULT ''       COMMENT '名称',
  level         int(10)                   DEFAULT 0        COMMENT '职务等级',
  remark        varchar(1500)             DEFAULT ''       COMMENT '备注',
  update_time   bigint(11)                DEFAULT 0        COMMENT '最后修改时间',
  create_time   bigint(11)                DEFAULT 0        COMMENT '创建时间',
  primary key (id)
) engine=innodb COMMENT = '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------

-- ----------------------------
-- 5、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  id                bigint(11)      NOT NULL  AUTO_INCREMENT   COMMENT '主键',
  role_key          varchar(100)    NOT null                   COMMENT '角色权限字符串',
  role_sort         int(4)          NOT null                   COMMENT '显示顺序',
  data_scope        char(1)         DEFAULT '1'                COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  status            char(1)         NOT null  DEFAULT '0'      COMMENT '角色状态（0正常 1停用）',
  update_time 		bigint(11) 		DEFAULT 0 				   COMMENT '最后修改时间',
  creator_id 		bigint(11)  	DEFAULT 0 				   COMMENT  '组织表id',
  operator_id  		bigint(11)  	DEFAULT 0 				   COMMENT  '最后操作id',
  role_name 		varchar(100) 	DEFAULT '' 				   COMMENT '角色名称',
  remark 			varchar(100) 	DEFAULT '' 				   COMMENT '备注',
  create_time		bigint(11) 		DEFAULT 0				   COMMENT '创建时间',
  is_delete 		tinyint(1) 		DEFAULT 0 				   COMMENT '是否删除',
  is_sys 		    tinyint(1) 		DEFAULT 0 				   COMMENT '是否是系统角色',
  primary key (id)
) engine=innodb auto_increment=1000 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
INSERT INTO `sys_role`(`id`, `role_key`, `role_sort`, `data_scope`, `status`, `update_time`, `creator_id`, `operator_id`, `role_name`, `remark`, `create_time`, `is_delete`) VALUES (1, 'admin', 1, '1', '0', 0, 1, 1, '超级管理员', '', 0, 0);
INSERT INTO `sys_role`(`id`, `role_key`, `role_sort`, `data_scope`, `status`, `update_time`, `creator_id`, `operator_id`, `role_name`, `remark`, `create_time`, `is_delete`) VALUES (2, 'common', 2, '2', '0', 0, 1, 1, '普通管理员', '', 0, 0);


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null COMMENT '用户ID',
  role_id   bigint(20) not null COMMENT '角色ID',
  primary key(user_id, role_id)
) engine=innodb COMMENT = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null COMMENT '角色ID',
  menu_id   bigint(20) not null COMMENT '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb COMMENT = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(11) not null COMMENT '角色ID',
  dept_id   bigint(11) not null COMMENT '部门ID',
  primary key(role_id, dept_id)
) engine=innodb COMMENT = '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------

-- ----------------------------
-- 9、登录用户和用户关联表  登录用户1-N用户
-- ----------------------------
drop table if exists sys_user_perm;
create table sys_user_perm (
  user_id   bigint(11) not null COMMENT '角色ID',
  perm_user_id   bigint(11) not null COMMENT '可查看的用户ID',
  primary key(user_id,perm_user_id)
) engine=innodb COMMENT = '登录用户和用户权限关联表';


-- ----------------------------
-- 10、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null COMMENT '用户ID',
  post_id   bigint(20) not null COMMENT '岗位ID',
  primary key (user_id, post_id)
) engine=innodb COMMENT = '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------

