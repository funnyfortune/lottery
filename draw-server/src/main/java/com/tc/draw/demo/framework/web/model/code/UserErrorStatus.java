package com.tc.draw.demo.framework.web.model.code;

public enum UserErrorStatus implements StatusCode {
    REQUEST_BODY_IS_NULL(2000, "请求参数不能为空"),
    ACCOUNT_PWD_IS_NULL(2001, "帐号密码不能为空"),
    CAPTCHA_ERROR(2002, "验证码错误"),
    CAPTCHA_NOT_EXIST(2003, "验证码已过期"),
    TOKEN_NOT_EXIST(2004, "token已过期"),
    ADMIN_LOGIN_FAILD(2005, "帐号或密码不正确"),
    CRM_UPDATE_PWD_FAILD(2006, "修改密码失败"),
    CRM_UPDATE_OLDPWD_FAILD(2007, "原始密码错误"),
    TOKEN_NOT_NULL(200, "token不能为空"),
    SUBMENU_NOT_DEL(2008, "请先删除子菜单或按钮"),
    NOT_PERMISSION(2009, "没有权限"),
	MOBILE_NOT_SAME(2012,"该手机号已被使用"),
	ACCOUNT_EXIST(2013,"账号存在"),
	SMS_SEND_ERROR(2014, "发送失败,触发短信流控"),
	EMAIL_ERROR(2015, "邮箱错误");

    private final int code;
    private final String msg;

    UserErrorStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public static UserErrorStatus getStatusCode(String code) {
        for (UserErrorStatus statusCode : values()) {
            if (code.equals(statusCode.getCode())) {
                return statusCode;
            }
        }
        return null;
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
