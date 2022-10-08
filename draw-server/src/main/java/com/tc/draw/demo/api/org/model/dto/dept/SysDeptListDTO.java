package com.tc.draw.demo.api.org.model.dto.dept;

import com.tc.draw.demo.framework.web.model.ListFormDTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("部门表列表传输对象")
public class SysDeptListDTO extends ListFormDTO {
	private long parentId;

	private String deptName;

	private String status;

}
