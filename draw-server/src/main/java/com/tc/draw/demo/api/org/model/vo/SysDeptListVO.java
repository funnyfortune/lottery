package com.tc.draw.demo.api.org.model.vo;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("部门表列表视图对象")
public class SysDeptListVO {

	/**
	 * 说明：主键ID 字段名：id 类型：bigint
	 */
	private Long id;

	/**
	 * 说明：祖级列表 字段名：ancestors 类型：varchar
	 */
	private String ancestors;

	/**
	 * 说明：联系电话 字段名：phone 类型：varchar
	 */
	private String phone;

	/**
	 * 说明：部门状态（0正常 1停用） 字段名：status 类型：char
	 */
	private String status;


	/**
	 * 说明：部门名称 字段名：dept_name 类型：varchar
	 */
	private String deptName;

	/**
	 * 说明：显示顺序 字段名：order_num 类型：int
	 */
	private Integer orderNum;

	/**
	 * 说明：负责人 字段名：leader 类型：varchar
	 */
	private String leader;

	private String email;

	/**
	 * 说明：备注 字段名：memo 类型：varchar
	 */
	private String memo;

	/**
	 * 说明：创建时间 字段名：create_time 类型：bigint
	 */
	private Long createTime;

	/**
	 * 说明：组织表id 字段名：creator_id 类型：bigint
	 */
	private Long creatorId;

	/**
	 * 说明：最后操作id 字段名：operator_id 类型：bigint
	 */
	private Long operatorId;

	/**
	 * 说明：修改时间 字段名：update_time 类型：bigint
	 */
	private Long updateTime;


	/**
	 * 说明：所在部门 字段名：parent_id 类型：long
	 */
	private Long parentId;

	/** 父部门名称 */
	private String parentName;

	/** 子部门 */
	private List<SysDeptListVO> children = new ArrayList<SysDeptListVO>();
}
