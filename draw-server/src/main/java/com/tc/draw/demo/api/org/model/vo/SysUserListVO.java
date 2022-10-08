package com.tc.draw.demo.api.org.model.vo;

import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("人员表列表视图对象")
public class SysUserListVO {

	/**
	 * 说明：主键ID 字段名：id 类型：varchar
	 */

	@Excel(name = "人员ID")
	@ApiModelProperty("主键ID")
	private long id;

	/**
	 * 说明：名称 字段名：name 类型：varchar
	 */
	@Excel(name = "名称")
	@ApiModelProperty("名称")
	private String name;

	@Excel(name = "账号")
	private String account;

	/**
	 * 说明：职务id 字段名：staffing_level_id 类型：varchar
	 */
	@Excel(name = "职务")
	@ApiModelProperty("职务")
	private String dutyNames;

	@Excel(name = "角色名称")
	@ApiModelProperty("角色名称")
	private String roleNames;

	@Excel(name = "部门名称")
	@ApiModelProperty("部门名称")
	private String deptName;

	/**
	 * 说明：入职时间 字段名：entry_time 类型：varchar
	 */
	@Excel(name = "入职时间")
	@ApiModelProperty("入职时间")
	private Long entryTime;

	/**
	 * 说明：工号 字段名：work_number 类型：varchar
	 */
	@Excel(name = "工号")
	@ApiModelProperty("工号")
	private String workNumber;

	/**
	 * 说明：邮件地址 字段名：org_email 类型：varchar
	 */
	@Excel(name = "邮件地址")
	@ApiModelProperty("邮件地址")
	private String orgEmail;

	/**
	 * 说明：手机号 字段名：mobile_no 类型：varchar
	 */
	@Excel(name = "手机号码")
	@ApiModelProperty("手机号")
	private String mobileNo;

	@Excel(name = "状态", readConverterExp = "0=正常,1=停用")
	private String status;
}
