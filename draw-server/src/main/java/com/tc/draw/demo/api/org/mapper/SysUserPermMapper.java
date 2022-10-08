package com.tc.draw.demo.api.org.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserPermMapper {

	@Delete("delete from sys_user_perm where user_id=#{userId}")
	int deleteByUserId(long userId);
	
	@Insert("insert into sys_user_perm(user_id,perm_user_id) select #{userId}, u.id from sys_user u, "
			+ "(select DISTINCT dept_id from sys_role_dept d,(select role_id from sys_user_role where user_id=#{userId}) t " 
			+ " where t.role_id= d.role_id) u1 where u.dept_id = u1.dept_id")
	int inserByUserId(long userId);
}
