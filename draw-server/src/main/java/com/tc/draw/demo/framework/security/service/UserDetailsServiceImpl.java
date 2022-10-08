package com.tc.draw.demo.framework.security.service;

import com.tc.draw.demo.api.org.service.ISysUserService;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.framework.security.LoginUser;
import com.tc.draw.demo.framework.security.SysUserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tc.draw.demo.common.enums.UserStatus;
import com.tc.draw.demo.exception.BaseException;

/**
 * 用户验证处理
 *
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);

	@Autowired
	private ISysUserService userService;

	@Autowired
	private PermissionService permissionService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUserInfo user = userService.selectUserByUserName(username);
		if (StringUtils.isNull(user)) {
			log.info("登录用户："+username+"不存在.");
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		} else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
			log.info("登录用户："+username+"已被停用." );
			throw new BaseException("对不起，您的账号：" + username + " 已停用");
		}
		return createLoginUser(user);
	}

	public UserDetails createLoginUser(SysUserInfo user) {
		String dataScope = permissionService.getDataScope(user.getId());
		return new LoginUser(user, permissionService.getMenuPermission(user.getId()),dataScope);
	}
}
