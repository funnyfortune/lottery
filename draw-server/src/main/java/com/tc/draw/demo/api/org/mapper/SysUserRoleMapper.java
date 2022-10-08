package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysUserRole;
import org.apache.ibatis.annotations.*;


/**
 * 用户与角色关联表 数据层
 * 
 *
 */
@Mapper
public interface SysUserRoleMapper
{
    /**
     * 通过用户ID删除用户和角色关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
	@Delete("delete from sys_user_role where user_id=#{userId}")
    int deleteUserRoleByUserId(Long userId);

    /**
     * 批量删除用户和角色关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@DeleteProvider(type=SysUserRoleMapperSQL.class, method="deleteUserRole")
    int deleteUserRole(@Param("list") List<Long> ids);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
	@Select("select count(1) from sys_user_role where role_id=#{roleId}")
    int countUserRoleByRoleId(Long roleId);

    /**
     * 批量新增用户角色信息
     * 
     * @param userRoleList 用户角色列表
     * @return 结果
     */
	@InsertProvider(type = SysUserRoleMapperSQL.class, method="insert")
    int insert(@Param("list") List<SysUserRole> list);

    /**
     * 删除用户和角色关联信息
     * 
     * @param userRole 用户和角色关联信息
     * @return 结果
     */
	@Delete("delete from sys_user_role where user_id=#{userId} and role_id=#{roleId}")
    int deleteUserRoleInfo(SysUserRole userRole);

    /**
     * 批量取消授权用户角色
     * 
     * @param roleId 角色ID
     * @param userIds 需要删除的用户数据ID
     * @return 结果
     */
	@DeleteProvider(type = SysUserRoleMapperSQL.class, method="deleteUserRoleInfos")
    public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("list") List<Long> userIds);

	@SelectProvider(type = SysUserRoleMapperSQL.class, method="checkRoleIdsUsedByRoleIds")
    List<String> checkRoleIdsUsedByRoleIds(@Param("list") List<Long> roleIds);
}
