package com.tc.draw.demo.api.org.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("组织选择框的组件对象")
public class SysOrgDeptSelectVO {

	@ApiModelProperty("id")
	private long id;
	
	@ApiModelProperty("部门名称")
	private String name;
	
	private String pinyin;
	
	@ApiModelProperty("子部门")
	private List<SysOrgDeptSelectVO> children;
}
