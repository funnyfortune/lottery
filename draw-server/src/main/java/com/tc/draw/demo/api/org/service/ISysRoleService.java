package com.tc.draw.demo.api.org.service;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysRole;
import com.tc.draw.demo.api.org.model.dto.role.RoleDataScopeDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleListDTO;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;

/**
 * 角色业务层
 * 
 *
 */
public interface ISysRoleService
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
     List<SysRoleVO> selectRoleList(SysRoleListDTO role);

    /**
     * 查询所有角色
     * 
     * @return 角色列表
     */
     List<SysRoleVO> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     * 
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
     List<Long> selectRoleListByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
     SysRoleVO selectRoleById(Long roleId);

    /**
     * 校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
     String checkRoleNameUnique(SysRole role);

    /**
     * 校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
     String checkRoleKeyUnique(SysRole role);

    /**
     * 校验角色是否允许操作
     * 
     * @param role 角色信息
     */
     void checkRoleAllowed(Long id);

    /**
     * 通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
     int countUserRoleByRoleId(Long roleId);

    /**
     * 新增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
     int insertRole(SysRoleDTO role);

    /**
     * 修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
     int updateRole(SysRoleDTO role);

    /**
     * 修改角色状态
     * 
     * @param role 角色信息
     * @return 结果
     */
     int updateRoleStatus(SysRole role);

    /**
     * 修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
     int authDataScope(RoleDataScopeDTO role);

    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
     int deleteRoleByIds(List<Long> roleIds);

    List<String> checkRoleIdsIsUsed(List<Long> roleIds);
}
