package com.tc.draw.demo.api.org.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.tc.draw.demo.api.org.model.dto.post.SysPostListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.common.utils.StringUtils;

public class SysPostMapperSQL {


    public String selectPostList(SysPostListDTO params) {
        SQL sql = new SQL();
        sql.SELECT("post_code postCode,post_sort postSort,status,id id,creator_id creatorId,operator_id operatorId,"
        		+ "post_name,level level,remark,update_time updateTime,create_time createTime")
        .FROM("sys_post").WHERE("is_delete = 0 ");
        if(!StringUtils.isEmpty(params.getPostCode())) {
        	sql.WHERE("post_code like concat('%', #{postCode}, '%')");
        }
        if(!StringUtils.isEmpty(params.getPostName())) {
        	sql.WHERE("post_name like concat('%', #{postName}, '%')");
        }
        if(!StringUtils.isEmpty(params.getStatus())) {
        	sql.WHERE("status = #{status}");
        }
        return sql.toString();
    }

	public String batDelete(@Param("ids") List<Long> ids, @Param("updateTime") long updateTime) {
		SQL sql = new SQL();
		sql.UPDATE("sys_post").SET("is_delete=1,update_time=#{updateTime}")
				.WHERE("id in " + ids.stream().map(in -> "'" + in + "'").collect(Collectors.joining(",", "(", ")")));
		return sql.toString();
	}
}
