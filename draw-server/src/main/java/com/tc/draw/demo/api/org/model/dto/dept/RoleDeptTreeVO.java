package com.tc.draw.demo.api.org.model.dto.dept;

import java.util.List;

import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import lombok.Data;

@Data
public class RoleDeptTreeVO {
	
	private List<Long> checkedKeys;
	
	private List<TreeSelectVO> depts;

}
