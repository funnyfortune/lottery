package com.tc.draw.demo.framework.security;

import java.util.List;

import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import lombok.Data;

@Data
public class SysUserInfo {

	/**
     * 说明：用户ID
     * 字段名：id
     * 类型：bigint
     */
    private Long id;
    
    /**
     * 说明：用户类型（00系统用户）
     * 字段名：user_type
     * 类型：varchar
     */
    private String userType;
    
    private String sex;
    
    /**
     * 说明：头像地址
     * 字段名：avatar
     * 类型：varchar
     */
    private String avatar;
    
    /**
     * 说明：帐号状态（0正常 1停用）
     * 字段名：status
     * 类型：char
     */
    private String status;
    
    /**
     * 说明：名称
     * 字段名：name
     * 类型：varchar
     */
    private String name;
    
    /**
     * 说明：登录名
     * 字段名：login_name
     * 类型：varchar
     */
    private String loginName;
    
    /**
     * 说明：登录密码
     * 字段名：password
     * 类型：varchar
     */
    private String password;
    
    /**
     * 说明：工号
     * 字段名：work_number
     * 类型：varchar
     */
    private String workNumber;
    
    /**
     * 说明：邮件地址
     * 字段名：org_email
     * 类型：varchar
     */
    private String orgEmail;

    
    /**
     * 说明：所在部门
     * 字段名：dept_id
     * 类型：bigint
     */
    private Long deptId;
    
    private String deptName;
    

    /**
     * 说明：手机号
     * 字段名：mobile_no
     * 类型：varchar
     */
    private String mobileNo;
    
    private List<SysRoleVO> roles;
}
