package com.tc.draw.demo.api.org.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联表
 *
 */
@Data
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 说明：用户ID 字段名：user_id 类型：bigint
	 */
	@TableId
	private Long userId;

	/**
	 * 说明：角色ID 字段名：role_id 类型：bigint
	 */
	@TableId
	private Long roleId;

}
