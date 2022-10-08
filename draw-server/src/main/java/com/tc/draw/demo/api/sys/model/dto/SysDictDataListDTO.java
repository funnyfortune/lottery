package com.tc.draw.demo.api.sys.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("字典数据列表传输对象")
public class SysDictDataListDTO extends ListFormDTO {

	/**
	 * 说明：字典标签 类型：String
	 */
	@ApiModelProperty("字典标签")
	private String dictLabel;

	/**
	 * 说明：字典类型 类型：String
	 */
	@ApiModelProperty("字典类型")
	private String dictType;

	/**
	 * 说明：是否默认（Y是 N否） 类型：String
	 */
	@ApiModelProperty("是否默认（Y是 N否）")
	private String isDefault;
	/**
	 * 说明：状态（0正常 1停用） 类型：String
	 */
	@ApiModelProperty("状态（0正常 1停用）")
	private String status;

}
