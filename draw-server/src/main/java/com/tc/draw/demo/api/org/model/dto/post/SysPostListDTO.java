package com.tc.draw.demo.api.org.model.dto.post;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import lombok.Data;

@Data
public class SysPostListDTO  extends ListFormDTO{

	private String postCode;
	
	private String status;
	
	private String postName;

}
