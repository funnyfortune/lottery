package com.tc.draw.demo.api.org.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 岗位信息表
 *
 */
@Data
public class SysPost implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 说明：岗位编码 字段名：post_code 类型：varchar
	 */
	private String postCode;

	/**
	 * 说明：显示顺序 字段名：post_sort 类型：int
	 */
	private Integer postSort;

	/**
	 * 说明：状态（0正常 1停用） 字段名：status 类型：char
	 */
	private String status;

	/**
	 * 说明：主键ID 字段名：id 类型：bigint
	 */
	@TableId
	private Long id;



	/**
	 * 说明：组织表id 字段名：creator_id 类型：bigint
	 */
	private Long creatorId;

	/**
	 * 说明：最后操作id 字段名：operator_id 类型：bigint
	 */
	private Long operatorId;

	/**
	 * 说明：是否删除 字段名：is_delete 类型：tinyint
	 */
	private Integer isDelete;

	/**
	 * 说明：名称 字段名：post_name 类型：varchar
	 */
	private String postName;

	/**
	 * 说明：职务等级 字段名：level 类型：int
	 */
	private Integer level;

	/**
	 * 说明：描述 字段名：remark 类型：varchar
	 */
	private String remark;

	/**
	 * 说明：最后修改时间 字段名：update_time 类型：bigint
	 */
	private Long updateTime;

	/**
	 * 说明：创建时间 字段名：create_time 类型：bigint
	 */
	private Long createTime;
}
