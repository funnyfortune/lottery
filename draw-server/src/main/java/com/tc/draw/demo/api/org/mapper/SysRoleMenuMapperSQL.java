package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.org.model.domain.SysRoleMenu;

import io.lettuce.core.dynamic.annotation.Param;

public class SysRoleMenuMapperSQL {

	public String insert(@Param("list")List<SysRoleMenu> list) {
        StringBuilder sql = new StringBuilder("insert into sys_role_menu(role_id, menu_id) values ");
    	StringJoiner values = new StringJoiner(",", "", "");
        for(int i=0; i< list.size();i++) {
        	StringJoiner value = new StringJoiner(",", "(", ")");
        	value.add("#{list["+i+"].roleId}");
        	value.add("#{list["+i+"].menuId}");
        	values.add(value.toString());
        }
        sql.append(values.toString());
        return sql.toString();
    }
}
