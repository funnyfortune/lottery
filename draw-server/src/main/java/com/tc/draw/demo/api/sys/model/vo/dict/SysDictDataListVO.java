package com.tc.draw.demo.api.sys.model.vo.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * 字典数据列表视图对象
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
@ApiModel("字典数据列表视图对象")
public class SysDictDataListVO {

	/**
	 * 说明：$comment 类型：Long
	 */
	@Excel(name = "字典编码")
	@ApiModelProperty("字典编码")
	private Long dictCode;
	/**
	 * 说明：字典编码 类型：Integer
	 */
	@Excel(name = "字典排序")
	@ApiModelProperty("字典排序")
	private Integer dictSort;
	/**
	 * 说明：字典排序 类型：String
	 */
	@Excel(name = "字典标签")
	@ApiModelProperty("字典标签")
	private String dictLabel;
	/**
	 * 说明：字典标签 类型：String
	 */
	@Excel(name = "字典键值")
	@ApiModelProperty("字典键值")
	private String dictValue;
	/**
	 * 说明：字典键值 类型：String
	 */
	@Excel(name = "字典类型")
	@ApiModelProperty("字典类型")
	private String dictType;
	/**
	 * 说明：字典类型 类型：String
	 */
	@Excel(name = "样式属性", readConverterExp = "其=他样式扩展")
	@ApiModelProperty("样式属性")
	private String cssClass;
	/**
	 * 说明：样式属性 类型：String
	 */
	@Excel(name = "表格回显样式")
	@ApiModelProperty("表格回显样式")
	private String listClass;
	/**
	 * 说明：表格回显样式 类型：String
	 */
	@Excel(name = "是否默认", readConverterExp = "Y=是,N=否")
	@ApiModelProperty("是否默认")
	private String isDefault;
	/**
	 * 说明：是否默认 类型：String
	 */
	@Excel(name = "状态", readConverterExp = "0=正常,1=停用")
	@ApiModelProperty("状态")
	private String status;
	/**
	 * 说明：状态 类型：String
	 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;

	/**
	 * 说明：是否删除 类型：Long
	 */
	@Excel(name = "创建时间")
	@ApiModelProperty("创建时间")
	private Long createTime;
	/**
	 * 说明：修改时间 类型：Long
	 */
	@Excel(name = "用户id")
	@ApiModelProperty("用户id")
	private Long creatorId;
}
