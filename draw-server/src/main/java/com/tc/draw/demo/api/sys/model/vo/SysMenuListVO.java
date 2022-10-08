package com.tc.draw.demo.api.sys.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单权限列表视图对象
 *
 * @author TC
 * @date 2020-05-20
 */
@Data
@ApiModel("菜单权限列表视图对象")
public class SysMenuListVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 说明：$comment 类型：Long
	 */
	@Excel(name = "菜单ID")
	@ApiModelProperty("菜单ID")
	private Long menuId;
	/**
	 * 说明：菜单ID 类型：String
	 */
	@Excel(name = "菜单名称")
	@ApiModelProperty("菜单名称")
	private String menuName;
	/**
	 * 说明：菜单名称 类型：Long
	 */
	@Excel(name = "父菜单ID")
	@ApiModelProperty("父菜单ID")
	private Long parentId;
	/**
	 * 说明：父菜单ID 类型：Integer
	 */
	@Excel(name = "显示顺序")
	@ApiModelProperty("显示顺序")
	private Integer orderNum;
	/**
	 * 说明：显示顺序 类型：String
	 */
	@Excel(name = "路由地址")
	@ApiModelProperty("路由地址")
	private String path;
	/**
	 * 说明：路由地址 类型：String
	 */
	@Excel(name = "组件路径")
	@ApiModelProperty("组件路径")
	private String component;
	/**
	 * 说明：组件路径 类型：Integer
	 */
	@Excel(name = "是否为外链", readConverterExp = "0=是,1=否")
	@ApiModelProperty("是否为外链")
	private Integer isFrame;
	/**
	 * 说明：是否为外链 类型：String
	 */
	@Excel(name = "菜单类型", readConverterExp = "M=目录,C=菜单,F=按钮")
	@ApiModelProperty("菜单类型")
	private String menuType;
	/**
	 * 说明：菜单类型 类型：String
	 */
	@Excel(name = "菜单状态", readConverterExp = "0=显示,1=隐藏")
	@ApiModelProperty("菜单状态")
	private String visible;
	/**
	 * 说明：菜单状态 类型：String
	 */
	@Excel(name = "菜单状态", readConverterExp = "0=正常,1=停用")
	@ApiModelProperty("菜单状态")
	private String status;
	/**
	 * 说明：菜单状态 类型：String
	 */
	@Excel(name = "权限标识")
	@ApiModelProperty("权限标识")
	private String perms;
	/**
	 * 说明：权限标识 类型：String
	 */
	@Excel(name = "菜单图标")
	@ApiModelProperty("菜单图标")
	private String icon;
	/**
	 * 说明：创建者 类型：Long
	 */
	@Excel(name = "创建时间")
	@ApiModelProperty("创建时间")
	private Long createTime;
	/**
	 * 说明：更新者 类型：Long
	 */
	@Excel(name = "更新时间")
	@ApiModelProperty("更新时间")
	private Long updateTime;
	/**
	 * 说明：更新时间 类型：String
	 */
	@Excel(name = "备注")
	@ApiModelProperty("备注")
	private String remark;

	/** 子菜单 */
	private List<SysMenuListVO> children = new ArrayList<>();
	public List<SysMenuListVO> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuListVO> children) {
		this.children = children;
	}

}
