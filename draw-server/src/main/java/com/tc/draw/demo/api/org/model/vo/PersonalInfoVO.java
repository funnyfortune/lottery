package com.tc.draw.demo.api.org.model.vo;

import com.tc.draw.demo.framework.security.SysUserInfo;
import lombok.Data;

@Data
public class PersonalInfoVO {
	
	private SysUserInfo user;

	private String roleGroup;
	
	private String postGroup;

}
