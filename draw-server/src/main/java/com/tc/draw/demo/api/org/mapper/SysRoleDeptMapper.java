package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysRoleDept;
import org.apache.ibatis.annotations.*;

/**
 * 角色与部门关联表 数据层
 * 
 *
 */
@Mapper
public interface SysRoleDeptMapper
{
    /**
     * 通过角色ID删除角色和部门关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
	@Delete("delete from sys_role_dept where role_id=#{roleId}")
     int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量删除角色部门关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DeleteProvider(type=SysRoleDeptMapperSQL.class, method="deleteRoleDept")
    int deleteRoleDept(@Param("list")List<Long> ids);

    /**
     * 查询部门使用数量
     * 
     * @param deptId 部门ID
     * @return 结果
     */
	@Select(" select count(1) from sys_role_dept where dept_id=#{deptId}")
    int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * 批量新增角色部门信息
     * 
     * @param list 角色部门列表
     * @return 结果
     */
	@InsertProvider(type=SysRoleDeptMapperSQL.class, method="insert")
    public int insert(@Param("list")List<SysRoleDept> list);
}
