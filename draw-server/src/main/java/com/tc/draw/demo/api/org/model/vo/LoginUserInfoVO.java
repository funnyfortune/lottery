package com.tc.draw.demo.api.org.model.vo;

import java.util.Set;

import com.tc.draw.demo.framework.security.SysUserInfo;
import lombok.Data;

@Data
public class LoginUserInfoVO {
	private SysUserInfo user;
	
	private Set<String> roles;
	
	private Set<String> permissions;

	private int noticeCount;

	private int msgCount;

}
