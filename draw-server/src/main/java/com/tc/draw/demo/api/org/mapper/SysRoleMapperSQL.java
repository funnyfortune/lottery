package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.draw.demo.api.org.model.dto.role.SysRoleListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.common.utils.StringUtils;


public class SysRoleMapperSQL {

    public String selectRoleList(SysRoleListDTO params) {
        SQL sql = new SQL();
        sql.SELECT("r.id, r.role_name, r.role_key,r.is_sys, r.role_sort,r.data_scope," +
                "r.status,r.create_time,r.remark").FROM("sys_role r ")
                .WHERE("(r.is_delete = 0  or r.id in (2,3))");
        if (!StringUtils.isEmpty(params.getRoleKey())) {
            sql.WHERE("r.role_key like concat('%', #{roleKey}, '%')");
        }
        if (!StringUtils.isEmpty(params.getRoleName())) {
            sql.WHERE("r.role_name like concat('%', #{roleName}, '%')");
        }
        if (!StringUtils.isEmpty(params.getStatus())) {
            sql.WHERE("r.status = #{status}");
        }
        if (params.getBeginTime() > 0) {
            sql.WHERE("r.create_time >=#{beginTime}");
        }
        if (params.getEndTime() > 0) {
            sql.WHERE("r.create_time <=#{endTime}");
        }
        sql.ORDER_BY("r.role_sort");
        return sql.toString();
    }


    public String batDelete(@Param("ids") List<Long> ids, @Param("updateTime") long updateTime) {
        SQL sql = new SQL();
        sql.UPDATE("sys_role").SET("is_delete=1,update_time=#{updateTime}").WHERE("id in " + ids.stream().map(in -> "'" + in + "'")
                .collect(Collectors.joining(",", "(", ")")));
        sql.WHERE("is_sys=0");
        return sql.toString();
    }
}
