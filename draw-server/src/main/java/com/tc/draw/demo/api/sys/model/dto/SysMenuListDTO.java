package com.tc.draw.demo.api.sys.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单权限列表传输对象")
public class SysMenuListDTO extends ListFormDTO {

	/**
	 * 说明：菜单名称 类型：String
	 */
	@ApiModelProperty("菜单名称")
	private String menuName;
	/**
	 * 说明：菜单状态（0显示 1隐藏） 类型：String
	 */
	@ApiModelProperty("菜单状态（0显示 1隐藏）")
	private String visible;
	/**
	 * 说明：菜单状态（0正常 1停用） 类型：String
	 */
	@ApiModelProperty("菜单状态（0正常 1停用）")
	private String status;
	
	private Long userId;

	
}
