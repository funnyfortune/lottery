package com.tc.draw.demo.api.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.api.sys.model.dto.SysMenuListDTO;

import java.util.StringJoiner;
import java.util.List;

/**
 * sysMenuMapperSQL
 *
 * @author TC
 * @date 2020-05-20
 */
public class SysMenuMapperSQL {


	/**
	 *
	 * 删除SQL
	 * 
	 * @return
	 */
	public String deleteByIds(@Param("list") List<Long> menuIds, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		int len = menuIds.size();
		for (int i = 0; i < len; i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.UPDATE("sys_menu").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}")
				.WHERE("menu_id in " + values.toString());
		return sql.toString();
	}

	/**
	 * 查询菜单权限列表
	 * 
	 * @return
	 */
	public String selectMenuListByUserId(SysMenuListDTO sysMenu) {
		SQL sql = new SQL();
		sql.SELECT("distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.status, ifnull(m.perms,'') as perms, m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time")
				.FROM("sys_menu m")
				.LEFT_OUTER_JOIN("sys_role_menu rm on m.menu_id = rm.menu_id")
				.LEFT_OUTER_JOIN("sys_user_role ur on rm.role_id = ur.role_id")
				.LEFT_OUTER_JOIN("sys_role ro on ur.role_id = ro.id")
				.WHERE("ur.user_id = #{userId}");
		listCondition(sysMenu, sql);
		return sql.toString();
	}
	
	/**
	 * 查询菜单权限列表
	 * 
	 * @return
	 */
	public String selectMenuList(SysMenuListDTO sysMenu) {
		SQL sql = new SQL();
		sql.SELECT(" menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, ifnull(perms,'') as perms, icon, create_time ")
				.FROM("sys_menu m");
		listCondition(sysMenu, sql);
		return sql.toString();
	}

	private void listCondition(SysMenuListDTO sysMenu, SQL sql) {
		if (sysMenu.getMenuName() != null && !sysMenu.getMenuName().trim().equals("")) {
			sql.WHERE("m.menu_name like concat('%', #{menuName}, '%')");
		}

		if (sysMenu.getVisible() != null && !sysMenu.getVisible().trim().equals("")) {
			sql.WHERE("m.visible = #{visible}");
		}
		if (sysMenu.getStatus() != null && !sysMenu.getStatus().trim().equals("")) {
			sql.WHERE("m.status = #{status}");
		}
		sql.ORDER_BY("m.parent_id, m.order_num");
	}
}
