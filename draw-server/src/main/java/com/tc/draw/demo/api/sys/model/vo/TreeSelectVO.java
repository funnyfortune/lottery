package com.tc.draw.demo.api.sys.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.tc.draw.demo.api.org.model.vo.SysDeptListVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Treeselect树结构实体类
 * 
 *
 */
@Data
public class TreeSelectVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 节点ID */
	private Long id;

	/** 节点名称 */
	private String label;

	/** 子节点 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<TreeSelectVO> children;

	public TreeSelectVO() {

	}

	public TreeSelectVO(SysDeptListVO dept) {
		this.id = dept.getId();
		this.label = dept.getDeptName();
		this.children = dept.getChildren().stream().map(TreeSelectVO::new).collect(Collectors.toList());
	}

	public TreeSelectVO(SysMenuListVO menu) {
		this.id = menu.getMenuId();
		this.label = menu.getMenuName();
		this.children = menu.getChildren().stream().map(TreeSelectVO::new).collect(Collectors.toList());
	}
}
