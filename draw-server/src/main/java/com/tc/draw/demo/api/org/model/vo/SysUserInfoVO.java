package com.tc.draw.demo.api.org.model.vo;

import com.tc.draw.demo.api.org.model.domain.SysPost;
import com.tc.draw.demo.api.org.model.domain.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserInfoVO {

	private List<SysRoleVO> roles;
	
	private List<SysPost> posts;
	
	private List<Long> roleIds;
	
	private List<Long> postIds;
	
	private SysUser user;
}
