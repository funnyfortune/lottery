package com.tc.draw.demo.api.org.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 *
 */
@Data
public class SysUser implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 说明：用户ID 字段名：id 类型：bigint
	 */
	@TableId
	private Long id;

	/**
	 * 说明：用户类型（00系统用户） 字段名：user_type 类型：varchar
	 */
	private String userType;

	/**
	 * 说明：头像地址 字段名：avatar 类型：varchar
	 */
	private String avatar;

	/**
	 * 说明：帐号状态（0正常 1停用） 字段名：status 类型：char
	 */
	private String status;

	/**
	 * 说明：最后登陆IP 字段名：login_ip 类型：varchar
	 */
	private String loginIp;

	/**
	 * 说明：最后登陆时间 字段名：login_date 类型：datetime
	 */
	private Long loginDate;

	/**
	 * 说明：备注 字段名：remark 类型：varchar
	 */
	private String remark;



	/**
	 * 说明：名称 字段名：name 类型：varchar
	 */
	private String name;

	/**
	 * 说明：是否删除 字段名：is_delete 类型：tinyint
	 */
	private Integer isDelete;

	/**
	 * 说明：登录名 字段名：login_name 类型：varchar
	 */
	private String loginName;

	/**
	 * 说明：登录密码 字段名：password 类型：varchar
	 */
	private String password;

	/**
	 * 说明：性别 字段名：sex 类型：varchar
	 */
	private String sex;

	/**
	 * 说明：入职时间 字段名：entry_time 类型：varchar
	 */
	private Long entryTime;

	/**
	 * 说明：工号 字段名：work_number 类型：varchar
	 */
	private String workNumber;

	/**
	 * 说明：邮件地址 字段名：org_email 类型：varchar
	 */
	private String orgEmail;

	/**
	 * 说明：创建时间 字段名：create_time 类型：bigint
	 */
	private Long createTime;

	/**
	 * 说明：修改时间 字段名：update_time 类型：bigint
	 */
	private Long updateTime;

	/**
	 * 说明：组织表id 字段名：creator_id 类型：bigint
	 */
	private Long creatorId;

	/**
	 * 说明：最后操作id 字段名：operator_id 类型：bigint
	 */
	private Long operatorId;

	/**
	 * 说明：所在部门 字段名：dept_id 类型：bigint
	 */
	private Long deptId;

	/**
	 * 说明：拼音名称 字段名：name_pinyin 类型：varchar
	 */
	private String namePinyin;

	/**
	 * 说明：手机号 字段名：mobile_no 类型：varchar
	 */
	private String mobileNo;
}
