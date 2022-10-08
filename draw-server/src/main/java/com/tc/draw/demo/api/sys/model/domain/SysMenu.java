package com.tc.draw.demo.api.sys.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;



/**
 * 菜单权限对象 sys_menu
 *
 * @author TC
 * @date 2020-05-20
 */
@Data
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 说明：菜单ID 字段：menu_id 类型：bigint(20)
	 */
	@TableId
	private Long menuId;

	/**
	 * 说明：菜单名称 字段：menu_name 类型：varchar(50)
	 */
	private String menuName;

	/**
	 * 说明：父菜单ID 字段：parent_id 类型：bigint(20)
	 */
	private Long parentId;

	/**
	 * 说明：显示顺序 字段：order_num 类型：int(4)
	 */
	private Integer orderNum;

	/**
	 * 说明：路由地址 字段：path 类型：varchar(200)
	 */
	private String path;

	/**
	 * 说明：组件路径 字段：component 类型：varchar(255)
	 */
	private String component;

	/**
	 * 说明：是否为外链（0是 1否） 字段：is_frame 类型：int(1)
	 */
	private Integer isFrame;

	/**
	 * 说明：菜单类型（M目录 C菜单 F按钮） 字段：menu_type 类型：char(1)
	 */
	private String menuType;

	/**
	 * 说明：菜单状态（0显示 1隐藏） 字段：visible 类型：char(1)
	 */
	private String visible;

	/**
	 * 说明：菜单状态（0正常 1停用） 字段：status 类型：char(1)
	 */
	private String status;

	/**
	 * 说明：权限标识 字段：perms 类型：varchar(100)
	 */
	private String perms;

	/**
	 * 说明：菜单图标 字段：icon 类型：varchar(100)
	 */
	private String icon;

	/**
	 * 说明：创建者 字段：creator_id 类型：bigint(11)
	 */
	private Long creatorId;

	/**
	 * 说明：创建时间 字段：create_time 类型：bigint(11)
	 */
	private Long createTime;

	/**
	 * 说明：更新者 字段：operator_id 类型：bigint(11)
	 */
	private Long operatorId;

	/**
	 * 说明：更新时间 字段：update_time 类型：bigint(11)
	 */
	private Long updateTime;

	/**
	 * 说明：备注 字段：remark 类型：varchar(500)
	 */
	private String remark;
}
