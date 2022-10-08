package com.tc.draw.demo.api.sys.mapper;

import java.util.List;
import java.util.StringJoiner;

import com.tc.draw.demo.api.sys.model.dto.SysDictTypeListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * sysDictTypeMapperSQL
 *
 * @author TC
 * @date 2020-05-19
 */
public class SysDictTypeMapperSQL {


	/**
	 *
	 * 删除SQL
	 *
	 * @return
	 */
	public String deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		int len = ids.size();
		for (int i = 0; i < len; i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.UPDATE("sys_dict_type").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}")
				.WHERE("dict_id in " + values.toString());
		return sql.toString();
	}

	/**
	 * 查询字典类型列表
	 * 
	 * @return
	 */
	public String selectSysDictTypeList(SysDictTypeListDTO sysDictType) {
		SQL sql = new SQL();
		sql.SELECT("dict_id, dict_name, dict_type, status, creator_id, create_time, remark").FROM("sys_dict_type");
		sql.WHERE("is_delete=0");
		if (sysDictType.getDictName() != null && !sysDictType.getDictName().trim().equals("")) {
			sql.WHERE("dict_name like concat('%', #{dictName}, '%')");
		}
		if (sysDictType.getDictType() != null && !sysDictType.getDictType().trim().equals("")) {
			sql.WHERE("dict_type  like concat('%', #{dictType}, '%')");
		}
		if (sysDictType.getStatus() != null && !sysDictType.getStatus().trim().equals("")) {
			sql.WHERE("status = #{status}");
		}
		if (sysDictType.getBeginTime() > 0) {
			sql.WHERE("create_time >= #{beginTime}");
		}
		if (sysDictType.getEndTime() > 0) {
			sql.WHERE("create_time <= #{endTime}");
		}
		return sql.toString();
	}
}
