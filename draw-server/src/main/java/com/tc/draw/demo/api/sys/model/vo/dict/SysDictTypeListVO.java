package com.tc.draw.demo.api.sys.model.vo.dict;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * 字典类型列表视图对象
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
@ApiModel("字典类型列表视图对象")
public class SysDictTypeListVO {

	/**
	 * 说明：$comment 类型：Long
	 */
	@Excel(name = "字典主键")
	@ApiModelProperty("字典主键")
	private Long dictId;
	/**
	 * 说明：字典主键 类型：String
	 */
	@Excel(name = "字典名称")
	@ApiModelProperty("字典名称")
	private String dictName;
	/**
	 * 说明：字典名称 类型：String
	 */
	@Excel(name = "字典类型")
	@ApiModelProperty("字典类型")
	private String dictType;
	/**
	 * 说明：字典类型 类型：String
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
	 * 说明：备注 类型：Long
	 */
	@Excel(name = "创建时间")
	@ApiModelProperty("创建时间")
	private Long createTime;
	/**
	 * 说明：创建时间 类型：Long
	 */
	@Excel(name = "修改时间")
	@ApiModelProperty("修改时间")
	private Long updateTime;

}
