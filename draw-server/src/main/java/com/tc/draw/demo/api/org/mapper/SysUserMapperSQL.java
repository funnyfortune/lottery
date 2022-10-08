package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.draw.demo.api.org.model.dto.SysUserListDTO;
import com.tc.draw.demo.common.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.common.utils.SecurityUtils;

public class SysUserMapperSQL {

	public String countSysPerson(SysUserListDTO params) {
		SQL sql = new SQL();
		sql.SELECT("count(*)").FROM("sys_user u");
		listCondition(params,sql);
		return sql.toString();
	}
	
	private void listCondition(SysUserListDTO params,SQL sql) {
		sql.WHERE("u.is_delete = 0 ");
		if(!StringUtils.isEmpty(params.getUserName())) {
			sql.WHERE("u.name like concat('%', #{userName}, '%')");
		}
		if(!StringUtils.isEmpty(params.getStatus())) {
			sql.WHERE("u.status = #{status}");
		}
		if(!StringUtils.isEmpty(params.getPhonenumber())) {
			sql.WHERE(" u.moblie_no like concat('%', #{phonenumber}, '%')");
		}
		if(params.getBeginTime()>0) {
			sql.WHERE("u.create_time >=#{beginTime}");
		}
		if(params.getEndTime()>0) {
			sql.WHERE("u.create_time <=#{endTime}");
		}
		if(params.getDeptId()>0) {
			sql.WHERE(" (u.dept_id = #{deptId} OR u.dept_id IN ( SELECT t.id FROM sys_dept t WHERE FIND_IN_SET (#{deptId},ancestors) ))");
		}
	}

	public String selectUserList(SysUserListDTO params) {
		SQL sql = new SQL();
		sql.SELECT("u.id,u.dept_id deptId,u.name,u.login_name account,d.dept_name deptName,org_email orgEmail,u.status,"
				+ "u.entry_time entryTime,u.work_number workNumber,u.mobile_no mobileNo")
				.FROM("sys_user u " + SecurityUtils.getDataScope("u.id"));
				sql.LEFT_OUTER_JOIN("sys_dept d on u.dept_id = d.id");
		listCondition(params, sql);
		return sql.toString();
	}

	public String batDelete(@Param("ids") List<Long> ids, @Param("updateTime") long updateTime) {
		SQL sql = new SQL();
		sql.UPDATE("sys_user").SET("is_delete=1,update_time=#{updateTime}")
				.WHERE("id in " + ids.stream().map(in -> "'" + in + "'").collect(Collectors.joining(",", "(", ")")));
		return sql.toString();
	}

	public String selectEngineerListByKeyword(@Param("userName") String userName,@Param("deptId") long deptId, @Param("roleId") long roleId){
		SQL sql = new SQL();
		sql.SELECT("u.id,u.dept_id deptId,u.name,u.work_number workNumber,u.mobile_no mobileNo ").FROM("sys_user u,sys_user_role ur");
		sql.WHERE("u.is_delete=0 and ur.role_id=#{roleId} and ur.user_id=u.id");
		if(deptId > 0L){
			sql.WHERE("u.dept_id=#{deptId} ");
		}
		if (StringUtils.isNotEmpty(userName)) {
			sql.WHERE("u.name like CONCAT('%',#{userName},'%')");
		}
		return sql.toString();
	}
}
