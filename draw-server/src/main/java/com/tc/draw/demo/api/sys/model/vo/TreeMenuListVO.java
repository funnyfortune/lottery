package com.tc.draw.demo.api.sys.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class TreeMenuListVO {

	private List<Integer> checkedKeys;
	
	private List<TreeSelectVO> menus;

}
