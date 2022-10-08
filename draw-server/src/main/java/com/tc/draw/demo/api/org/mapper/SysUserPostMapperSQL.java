package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.org.model.domain.SysUserPost;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class SysUserPostMapperSQL {

	public String insert(@Param("list") List<SysUserPost> list) {
		StringBuilder sql = new StringBuilder("insert into sys_user_post(user_id, post_id) values ");
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < list.size(); i++) {
			StringJoiner value = new StringJoiner(",", "(", ")");
			value.add("#{list[" + i + "].userId}");
			value.add("#{list[" + i + "].postId}");
			values.add(value.toString());
		}
		sql.append(values.toString());
		return sql.toString();
	}

	public String deleteUserPost(@Param("list") List<Long> ids) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < ids.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.DELETE_FROM("sys_user_post where user_id in " + values.toString());
		return sql.toString();
	}

	public String checkPostIdsUsedByPostIds(@Param("list") List<Long> postIds){
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		for (int i = 0; i < postIds.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.SELECT("DISTINCT post_id").FROM("sys_user_post").WHERE("post_id in " + values.toString());
		return sql.toString();
	}
}
