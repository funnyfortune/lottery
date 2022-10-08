package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.org.model.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class SysUserRoleMapperSQL {

	public String insert(@Param("list") List<SysUserRole> list) {
		StringBuilder sql = new StringBuilder("insert into sys_user_role(user_id, role_id) values ");
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < list.size(); i++) {
			StringJoiner value = new StringJoiner(",", "(", ")");
			value.add("#{list[" + i + "].userId}");
			value.add("#{list[" + i + "].roleId}");
			values.add(value.toString());
		}
		sql.append(values.toString());
		return sql.toString();
	}
	
	public String deleteUserRole(@Param("list") List<Long> ids){
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < ids.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.DELETE_FROM("sys_user_role where user_id in " + values.toString());
		return sql.toString();
	}

	public String deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("list") List<Long> userIds) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < userIds.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.DELETE_FROM("sys_user_role where role_id=#{roleId} and user_id in " + values.toString());
		return sql.toString();
	}

	public String checkRoleIdsUsedByRoleIds(@Param("list") List<Long> roleIds) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		for (int i = 0; i < roleIds.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.SELECT("DISTINCT role_id from sys_user_role where role_id in " + values.toString());
		return sql.toString();
	}
}
