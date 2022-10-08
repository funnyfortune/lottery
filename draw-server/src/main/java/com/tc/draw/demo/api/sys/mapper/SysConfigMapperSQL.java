package com.tc.draw.demo.api.sys.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import com.tc.draw.demo.api.sys.model.domain.SysConfig;
import com.tc.draw.demo.api.sys.model.dto.SysConfigListDTO;

import java.util.StringJoiner;
import java.util.List;

/**
 * sysConfigMapperSQL
 *
 * @author TC
 * @date 2020-05-19
 */
public class SysConfigMapperSQL {

	/**
	 *
	 * 删除SQL
	 * 
	 * @return
	 */
	public String deleteByIds(@Param("list") List<Long> configIds, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		int len = configIds.size();
		for (int i = 0; i < len; i++) {
			values.add("#{list[" + i + "]}");
		}
		sql.UPDATE("sys_config").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}")
				.WHERE("config_id in " + values.toString());
		return sql.toString();
	}

	/**
	 * 查询参数配置列表
	 * 
	 * @return
	 */
	public String selectConfigList(SysConfigListDTO sysConfig) {
		SQL sql = new SQL();
		sql.SELECT("config_id, config_name, config_key, config_value, config_type, create_time, update_time, remark")
				.FROM("sys_config");
		sql.WHERE("is_delete=0");
		if (sysConfig.getConfigName() != null && !sysConfig.getConfigName().trim().equals("")) {
			sql.WHERE("config_name like concat('%', #{configName}, '%')");
		}
		if (sysConfig.getConfigKey() != null && !sysConfig.getConfigKey().trim().equals("")) {
			sql.WHERE("config_key = #{configKey}");
		}
		if (sysConfig.getConfigType() != null && !sysConfig.getConfigType().trim().equals("")) {
			sql.WHERE("config_type = #{configType}");
		}
		if (sysConfig.getBeginTime() > 0) {
			sql.WHERE("create_time >= #{beginTime}");
		}
		if (sysConfig.getEndTime() > 0) {
			sql.WHERE("create_time <= #{endTime}");
		}
		return sql.toString();
	}

	/**
	 * 查询参数配置列表
	 * 
	 * @return
	 */
	public String selectConfig(SysConfig sysConfig) {
		SQL sql = new SQL();
		sql.SELECT("config_id, config_name, config_key, config_value, config_type,value_type, create_time, update_time, remark")
				.FROM("sys_config");
		sql.WHERE("is_delete=0");
		if (sysConfig.getConfigId() != null) {
			sql.WHERE(" config_id = #{configId} ");
		}
		if (sysConfig.getConfigKey() != null && !sysConfig.getConfigKey().trim().equals("")) {
			sql.WHERE("config_key = #{configKey}");
		}
		return sql.toString();
	}
	
	public  String selectConfigKeyByIds(@Param("list") List<Long> configIds) {
		SQL sql = new SQL();
		StringJoiner values = new StringJoiner(",", "(", ")");
		int len = configIds.size();
		for (int i = 0; i < len; i++) {
			values.add("#{list[" + i + "]}");
		}
		return sql.SELECT("config_key").FROM("sys_config").WHERE("config_id in " + values.toString()).toString();
	}

}
