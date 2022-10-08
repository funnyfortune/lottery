package com.tc.draw.demo.framework.web.model.code;

/**
 * 状态码
 *
 * @author Jason
 * @date 2017年5月4日 下午5:00:41
 */
public enum SysStatusCode implements StatusCode {

    OK(200, "success"),

    UNKNOW_ERROR(1000, "系统繁忙"),

    SYSTEM_UPDATE(1001, "系统维护中"),

    SIGN_FAILED(1002, "token错误"),

    PARAM_ERROR(1003, "参数不正确"),

    DATA_ERROR(1004, "数据不存在"),

    URL_NOT_FOUND(1005, "路径不存在，请检查路径是否正确"),

    DB_EXIST_RECODE(1006, "数据库中已存在该记录"),
    
    HTTP_METHOD_NO_SUPPORT(1007, "请求方法不支持"),
   FORM_REPEATED(1008, "不允许重复提交，请稍后再试"),
   ADD_ERROR(1009, "新增失败，该编号已存在"),
   UPDATE_ERROR(1010, "修改失败，该编号已存在");

    private final int code;
    private final String msg;

    SysStatusCode(int code, String msg) {
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

    public static SysStatusCode getStatusCode(int code) {
        for (SysStatusCode statusCode : values()) {
            if (code == statusCode.getCode()) {
                return statusCode;
            }
        }
        return null;
    }

}
