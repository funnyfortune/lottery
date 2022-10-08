package com.tc.draw.demo.api.sys.mapper;

import java.util.List;
import java.util.StringJoiner;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.api.sys.model.dto.SysDictDataListDTO;

/**
 * sysDictDataMapperSQL
 *
 * @author TC
 * @date 2020-05-19
 */
public class SysDictDataMapperSQL {

	/**
	 *
	 * 删除SQL
	 * 
	 * @return
	 */
	public String deleteByIds(@Param("list") List<Long> dictCodes, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		int len = dictCodes.size();
		for (int i = 0; i < len; i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.UPDATE("sys_dict_data").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}")
				.WHERE("dict_code in " + values.toString());
		return sql.toString();
	}

	/**
	 * 查询字典数据列表
	 * 
	 * @return
	 */
	public String selectDictDataList(SysDictDataListDTO sysDictData) {
		SQL sql = new SQL();
		sql.SELECT("dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, "
				+ "list_class, is_default,status, creator_id, create_time, remark").FROM("sys_dict_data");
		sql.WHERE("is_delete=0");
		if (sysDictData.getDictType() != null && !sysDictData.getDictType().trim().equals("")) {
			sql.WHERE("dict_type = #{dictType}");
		}
		if (sysDictData.getDictLabel() != null && !sysDictData.getDictLabel().trim().equals("")) {
			sql.WHERE("dict_label like concat('%', #{dictLabel}, '%')");
		}

		if (sysDictData.getStatus() != null && !sysDictData.getStatus().trim().equals("")) {
			sql.WHERE("status = #{status}");
		}
		return sql.toString();
	}
}
