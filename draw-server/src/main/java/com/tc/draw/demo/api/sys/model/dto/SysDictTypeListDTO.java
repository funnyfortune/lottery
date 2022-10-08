package com.tc.draw.demo.api.sys.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("字典类型列表传输对象")
public class SysDictTypeListDTO extends ListFormDTO {

	/**
	 * 说明：字典名称 类型：String
	 */
	@ApiModelProperty("字典名称")
	private String dictName;
	/**
	 * 说明：字典类型 类型：String
	 */
	@ApiModelProperty("字典类型")
	private String dictType;
	/**
	 * 说明：状态（0正常 1停用） 类型：String
	 */
	@ApiModelProperty("状态（0正常 1停用）")
	private String status;

}
