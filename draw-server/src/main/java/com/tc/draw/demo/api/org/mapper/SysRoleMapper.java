package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.org.model.domain.SysRole;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleListDTO;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import org.apache.ibatis.annotations.*;

/**
 * 角色表 数据层
 * 
 *
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
	 * 根据条件分页查询角色数据
	 * 
	 * @param role 角色信息
	 * @return 角色数据集合信息
	 */
	@SelectProvider(type = SysRoleMapperSQL.class, method = "selectRoleList")
	List<SysRoleVO> selectRoleList(SysRoleListDTO role);

	/**
	 * 根据用户ID查询角色
	 * 
	 * @param userId 用户ID
	 * @return 角色列表
	 */

	@Select("select r.id, r.role_name, r.role_key, r.role_sort,r.data_scope, "
			+ "r.status,r.create_time,r.remark  from sys_role r  " + "left join sys_user_role ur on ur.role_id = r.id"
			+ "  where ur.user_id = #{userId} and r.is_delete=0")
	 List<SysRoleVO> selectRolePermissionByUserId(Long userId);


	/**
	 * 根据用户ID获取角色选择框列表
	 * 
	 * @param userId 用户ID
	 * @return 选中角色ID列表
	 */
	@Select("select role_id from sys_user_role where user_id=#{userId}")
	List<Long> selectRoleListByUserId(Long userId);

	/**
	 * 通过角色ID查询角色
	 * 
	 * @param roleId 角色ID
	 * @return 角色对象信息
	 */
	@Select("select id, role_name, role_key, role_sort,data_scope,is_sys, "
			+ "status,create_time, remark from sys_role where id=#{roleId} and is_delete=0")
	SysRoleVO selectRoleById(Long roleId);

	/**
	 * 根据用户ID查询角色
	 * 
	 * @param userName 用户名
	 * @return 角色列表
	 */
	@Select("select r.id, r.role_name, r.role_key, r.role_sort,r.data_scope, "
			+ "r.status,r.create_time,r.remark  from sys_role r  " + "left join sys_user_role ur on ur.role_id = r.id"
			+ " left join sys_user u on u.id = ur.user_id where ( u.name=#{userName} and r.is_delete=0) or (r.id in (2,3))")
	List<SysRoleVO> selectRolesByUserName(@Param("userName") String userName);

	/**
	 * 校验角色名称是否唯一
	 * 
	 * @param roleName 角色名称
	 * @return 角色信息
	 */
	@Select("select distinct id from sys_role  where role_name=#{roleName} and is_delete=0")
	Long checkRoleNameUnique(@Param("roleName") String roleName);

	/**
	 * 校验角色权限是否唯一
	 * 
	 * @param roleKey 角色权限
	 * @return 角色信息
	 */
	@Select("select distinct id from sys_role  where role_key=#{roleKey} and is_delete=0")
	Long checkRoleKeyUnique(@Param("roleKey") String roleKey);


	/**
	 * 批量删除角色信息
	 * 
	 * @param ids 角色ID
	 * @return 结果
	 */
	@UpdateProvider(type = SysRoleMapperSQL.class, method = "batDelete")
	int batDelete(@Param("ids") List<Long> ids, @Param("updateTime") long updateTime);
}
