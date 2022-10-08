package com.tc.draw.demo.api.sys.model.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 字典类型对象 sys_dict_type
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
public class SysDictType {

	/**
	 * 说明：字典主键 字段：dictId 类型：bigint(20)
	 */
	@TableId
	private Long dictId;

	/**
	 * 说明：字典名称 字段：dict_name 类型：varchar(100)
	 */
	private String dictName;

	/**
	 * 说明：字典类型 字段：dict_type 类型：varchar(100)
	 */
	private String dictType;

	/**
	 * 说明：状态（0正常 1停用） 字段：status 类型：char(1)
	 */
	private String status;

	/**
	 * 说明：备注 字段：remark 类型：varchar(500)
	 */
	private String remark;

	/**
	 * 说明：是否删除 字段：is_delete 类型：tinyint(1)
	 */
	private Integer isDelete;

	/**
	 * 说明：创建时间 字段：create_time 类型：bigint(11)
	 */
	private Long createTime;

	/**
	 * 说明：修改时间 字段：update_time 类型：bigint(11)
	 */
	private Long updateTime;

	/**
	 * 说明：用户id 字段：creator_id 类型：bigint(11)
	 */
	private Long creatorId;

	/**
	 * 说明：最后操作id 字段：operator_id 类型：bigint(11)
	 */
	private Long operatorId;

}
