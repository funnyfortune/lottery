package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.org.model.domain.SysRoleDept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;


public class  SysRoleDeptMapperSQL {


    public String insert(@Param("list") List<SysRoleDept> list) {
    	StringBuilder sql = new StringBuilder("insert into sys_role_dept(role_id, dept_id) values ");
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < list.size(); i++) {
			StringJoiner value = new StringJoiner(",", "(", ")");
			value.add("#{list[" + i + "].roleId}");
			value.add("#{list[" + i + "].deptId}");
			values.add(value.toString());
		}
		sql.append(values.toString());
		return sql.toString();
    }
   public String deleteRoleDept(@Param("list")List<Long> ids){
	   SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "", "");
		for (int i = 0; i < ids.size(); i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.DELETE_FROM("sys_role_dept where role_id in " + values.toString());
		return sql.toString();
    }
 
}
