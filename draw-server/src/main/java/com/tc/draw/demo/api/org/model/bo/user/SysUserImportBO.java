package com.tc.draw.demo.api.org.model.bo.user;


import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel.Type;
import lombok.Data;

/**
 * 用户对象 sys_user
 * 
 *
 */
@Data
public class SysUserImportBO {

	/** 部门ID */
	@Excel(name = "部门ID", type = Type.IMPORT)
	private Long deptId;

	/** 用户账号 */
	@Excel(name = "登录名称")
	private String loginName;

	/** 用户昵称 */
	@Excel(name = "用户名称")
	private String name;

	/** 用户邮箱 */
	@Excel(name = "用户邮箱")
	private String orgEmail;

	/** 手机号码 */
	@Excel(name = "手机号码")
	private String mobileNo;

	/** 用户性别 */
	@Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
	private String sex;
	
	/** 用户性别 */
	@Excel(name = "用户工号")
	private String workNumber;
	
	/** 用户性别 */
	@Excel(name = "入职时间")
	private String entryTime;

}
