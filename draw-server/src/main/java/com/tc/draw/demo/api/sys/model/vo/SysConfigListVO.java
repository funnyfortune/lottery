package com.tc.draw.demo.api.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

/**
 * 参数配置列表视图对象
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
@ApiModel("参数配置列表视图对象")
public class SysConfigListVO {

	/**
	 * 说明：$comment 类型：Integer
	 */
	@Excel(name = "参数主键")
	@ApiModelProperty("参数主键")
	private long configId;
	/**
	 * 说明：参数主键 类型：String
	 */
	@Excel(name = "参数名称")
	@ApiModelProperty("参数名称")
	private String configName;
	/**
	 * 说明：参数名称 类型：String
	 */
	@Excel(name = "参数键名")
	@ApiModelProperty("参数键名")
	private String configKey;
	/**
	 * 说明：参数键名 类型：String
	 */
	@Excel(name = "参数键值")
	@ApiModelProperty("参数键值")
	private String configValue;
	/**
	 * 说明：参数键值 类型：String
	 */
	@Excel(name = "系统内置", readConverterExp = "Y=是,N=否")
	@ApiModelProperty("系统内置")
	private String configType;
	/**
	 * 说明：系统内置 类型：String
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
	/**
	 * 说明：修改时间 类型：Long
	 */
	@Excel(name = "用户id")
	@ApiModelProperty("用户id")
	private Long creatorId;
}
