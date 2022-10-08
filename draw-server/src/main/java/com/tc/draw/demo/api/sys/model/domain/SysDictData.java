package com.tc.draw.demo.api.sys.model.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 字典数据对象 sys_dict_data
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
public class SysDictData {

	/**
	 * 说明：字典编码 字段：dict_code 类型：bigint(20)
	 */
	@TableId
	private Long dictCode;

	/**
	 * 说明：字典排序 字段：dict_sort 类型：int(4)
	 */
	private Integer dictSort;

	/**
	 * 说明：字典标签 字段：dict_label 类型：varchar(100)
	 */
	private String dictLabel;

	/**
	 * 说明：字典键值 字段：dict_value 类型：varchar(100)
	 */
	private String dictValue;

	/**
	 * 说明：字典类型 字段：dict_type 类型：varchar(100)
	 */
	private String dictType;

	/**
	 * 说明：样式属性（其他样式扩展） 字段：css_class 类型：varchar(100)
	 */
	private String cssClass;

	/**
	 * 说明：表格回显样式 字段：list_class 类型：varchar(100)
	 */
	private String listClass;

	/**
	 * 说明：是否默认（Y是 N否） 字段：is_default 类型：char(1)
	 */
	private String isDefault;

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
