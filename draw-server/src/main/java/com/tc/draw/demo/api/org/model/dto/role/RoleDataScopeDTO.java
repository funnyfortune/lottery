package com.tc.draw.demo.api.org.model.dto.role;

import lombok.Data;

import java.util.List;

@Data
public class RoleDataScopeDTO {

	private long id;
	
    /**
     * 说明：数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     * 字段名：data_scope
     * 类型：char
     */
    private String dataScope;
    
    private List<Long> deptIds;
}
