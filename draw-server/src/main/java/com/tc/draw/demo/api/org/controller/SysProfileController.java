package com.tc.draw.demo.api.org.controller;

import java.io.IOException;

import com.tc.draw.demo.api.org.model.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.tc.draw.demo.api.org.model.vo.PersonalInfoVO;
import com.tc.draw.demo.api.org.service.ISysUserService;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.ServletUtils;

import com.tc.draw.demo.framework.security.LoginUser;
import com.tc.draw.demo.framework.security.SysUserInfo;
import com.tc.draw.demo.framework.security.service.TokenService;
import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;

/**
 * 个人信息 业务处理
 *
 *
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
	@Autowired
	private ISysUserService userService;

	@Autowired
	private TokenService tokenService;

	/**
	 * 个人信息
	 */
	@GetMapping
	public Res<PersonalInfoVO, Void> profile() {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
		SysUserInfo user = loginUser.getUser();
		PersonalInfoVO vo = new PersonalInfoVO();
		vo.setUser(user);
		vo.setPostGroup(userService.selectUserPostGroup(loginUser.getUsername()));
		vo.setRoleGroup( userService.selectUserRoleGroup(loginUser.getUsername()));
		return Res.ok(vo);
	}

	/**
	 * 修改用户
	 */
	// @OperateAction(title = "个人信息", businessType = BusinessType.UPDATE)
	@PutMapping
	public Res<Void, Void> updateProfile(@RequestBody SysUser user) {
		if (userService.updateUserProfile(user) > 0) {
			LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
			// 更新缓存用户信息
			loginUser.getUser().setName(user.getName());
			loginUser.getUser().setMobileNo(user.getMobileNo());
			loginUser.getUser().setOrgEmail(user.getOrgEmail());
			loginUser.getUser().setSex(user.getSex());
			tokenService.setLoginUser(loginUser);
			return Res.ok();
		}
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(),"修改个人信息异常，请联系管理员");
	}

	/**
	 * 重置密码
	 */
	// @OperateAction(title = "个人信息", businessType = BusinessType.UPDATE)
	@PostMapping("/updatePwd")
	public Res<Void, Void> updatePwd(String oldPassword, String newPassword) {
		LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
		String password = loginUser.getPassword();
		if (!SecurityUtils.matchesPassword(oldPassword, password)) {
			return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(),"修改密码失败，旧密码错误");
		}
		if (SecurityUtils.matchesPassword(newPassword, password)) {
			return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(),"新密码不能与旧密码相同");
		}
		SysUser user = new SysUser();
		user.setId(SecurityUtils.getUserId());
		user.setPassword(SecurityUtils.encryptPassword(newPassword));
		if (userService.resetUserPwd(user) > 0) {
			// 更新缓存用户密码
			loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
			tokenService.setLoginUser(loginUser);
			return Res.ok();
		}
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(),"修改密码异常，请联系管理员");
	}

	/**
	 * 头像上传
	 */
	// @OperateAction(title = "用户头像", businessType = BusinessType.UPDATE)
	@PostMapping("/avatar")
	public Res<String, Void> avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
//			LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
//			Res<FileVO, Void> res = fileService.upload(file);
//			if(res.getCode() != 200) {
//				return  new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), "上传图片异常，请联系管理员");
//			}
//			SysUser user = new SysUser();
//			user.setAvatar(res.getData().getUrl());
//			user.setId(SecurityUtils.getUserId());
//			if (userService.updateUserAvatar(user)) {
//				// 更新缓存用户头像
//				loginUser.getUser().setAvatar(res.getData().getFullUrl());
//				tokenService.setLoginUser(loginUser);
//				return Res.ok(res.getData().getFullUrl());
//			}
		}
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), "上传图片异常，请联系管理员");
	}
}
