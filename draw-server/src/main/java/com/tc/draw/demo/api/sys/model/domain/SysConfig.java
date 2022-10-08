package com.tc.draw.demo.api.sys.model.domain;



import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 参数配置对象 sys_config
 *
 * @author TC
 * @date 2020-05-19
 */
@Data
public class SysConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 说明：参数主键 字段：config_id 类型：int(5)
	 */
	@TableId
	private Long configId;

	/**
	 * 说明：参数名称 字段：config_name 类型：varchar(100)
	 */
	private String configName;

	/**
	 * 说明：参数键名 字段：config_key 类型：varchar(100)
	 */
	private String configKey;

	/**
	 * 说明：参数键值 字段：config_value 类型：varchar(500)
	 */
	private String configValue;

	/**
	 * 说明：系统内置（Y是 N否） 字段：config_type 类型：char(1)
	 */
	private String configType;
	
	private String valueType;

	/**
	 * 说明：备注 字段：remark 类型：varchar(500)
	 */
	private String remark;

	/**
	 * 说明：创建时间 字段：create_time 类型：bigint(11)
	 */
	private Long createTime;

	/**
	 * 说明：修改时间 字段：update_time 类型：bigint(11)
	 */
	private Long updateTime;

	/**
	 * 说明：用户id 字段：creator_id 类型：bigint(11)
	 */
	private Long creatorId;

	/**
	 * 说明：最后操作id 字段：operator_id 类型：bigint(11)
	 */
	private Long operatorId;

	/**
	 * 说明：是否删除 字段：is_delete 类型：tinyint(1)
	 */
	private Integer isDelete;

}
