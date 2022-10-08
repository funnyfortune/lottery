package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.org.model.domain.SysDept;
import com.tc.draw.demo.api.org.model.dto.dept.SysDeptListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.common.utils.StringUtils;

public class SysDeptMapperSQL {

	public String selectDeptList(SysDeptListDTO params) {
		SQL sql = new SQL();
		sql.SELECT(" d.id, d.parent_id, d.ancestors, d.dept_name, d.order_num, u.name leader, d.phone, d.email, "
				+ "d.status,d.creator_id, d.create_time").FROM("sys_dept d " );
		sql.LEFT_OUTER_JOIN("sys_user u on u.id=d.leader_id");
		sql.WHERE("d.is_delete = 0 ");
		if (params.getParentId() > 0) {
			sql.WHERE("d.parent_id = #{parentId}");
		}
		if (!StringUtils.isEmpty(params.getDeptName())) {
			sql.WHERE("d.dept_name like concat('%', #{deptName}, '%')");
		}
		if (!StringUtils.isEmpty(params.getStatus())) {
			sql.WHERE("d.status = #{status}");
		}
		sql.ORDER_BY("d.parent_id, order_num");
		return sql.toString();
	}

	public String updateDeptChildren(@Param("list") List<SysDept> depts) {
		SQL sql = new SQL();
		StringJoiner joiner = new StringJoiner(" ", "case dept_id", "end");
		StringJoiner where = new StringJoiner(",", "(", ")");
		for (int i = 0; i < depts.size(); i++) {
			joiner.add("when #{list[" + i + "].id} then #{list[" + i + "].ancestors}");
			where.add("#{list[" + i + "].id}");
		}
		sql.UPDATE("sys_dept").SET("ancestors =" + joiner.toString()).WHERE(" where id in" + where.toString());
		return sql.toString();
	}

}
