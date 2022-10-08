package com.tc.draw.demo.framework.web.model.code;

public enum TokenErrStatusCode implements StatusCode {
	CAPTCHA_ERROR(2002, "验证码错误"),
	CAPTCHA_NOT_EXIST(2003, "验证码已过期"),
	TOKEN_NOT_EXIST(2004, "token已过期"),
	ADMIN_LOGIN_FAILD(2005, "帐号或密码不正确"),
	TOKEN_NOT_NULL(20006, "token不能为空"),
	MSG_CAPTCHA_ERROR(2007, "手机验证码错误"),
	PICTURE_CAPTCHA_ERROR(2008, "图形验证码错误"), 
	PICTURE_CAPTCHA_NOT_EXIST(2009, "图形验证码已过期");

	private final int code;
	private final String msg;

	TokenErrStatusCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

}
