package com.tc.draw.demo.api.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tc.draw.demo.api.org.mapper.SysDeptMapper;
import com.tc.draw.demo.api.org.mapper.SysRoleDeptMapper;
import com.tc.draw.demo.api.org.mapper.SysRoleMapper;
import com.tc.draw.demo.api.org.mapper.SysRoleMenuMapper;
import com.tc.draw.demo.api.org.mapper.SysUserRoleMapper;
import com.tc.draw.demo.api.org.model.domain.SysRole;
import com.tc.draw.demo.api.org.model.domain.SysRoleDept;
import com.tc.draw.demo.api.org.model.domain.SysRoleMenu;
import com.tc.draw.demo.api.org.model.dto.role.RoleDataScopeDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleListDTO;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import com.tc.draw.demo.api.org.service.ISysRoleService;
import com.tc.draw.demo.common.constant.DataScopeConstants;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.common.utils.spring.SpringUtils;
import com.tc.draw.demo.exception.CustomException;

/**
 * 角色 业务层处理
 * 
 *
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;

	@Autowired
	private SysUserRoleMapper userRoleMapper;

	@Autowired
	private SysRoleDeptMapper roleDeptMapper;

	@Autowired
	private SysDeptMapper sysDeptMapper;

	/**
	 * 根据条件分页查询角色数据
	 * 
	 * @param role 角色信息
	 * @return 角色数据集合信息
	 */
	@Override
	public List<SysRoleVO> selectRoleList(SysRoleListDTO role) {
		return roleMapper.selectRoleList(role);
	}

	/**
	 * 查询所有角色
	 * 
	 * @return 角色列表
	 */
	@Override
	public List<SysRoleVO> selectRoleAll() {
		return SpringUtils.getAopProxy(this).selectRoleList(new SysRoleListDTO());
	}

	/**
	 * 根据用户ID获取角色选择框列表
	 * 
	 * @param userId 用户ID
	 * @return 选中角色ID列表
	 */
	@Override
	public List<Long> selectRoleListByUserId(Long userId) {
		return roleMapper.selectRoleListByUserId(userId);
	}

	/**
	 * 通过角色ID查询角色
	 * 
	 * @param roleId 角色ID
	 * @return 角色对象信息
	 */
	public SysRoleVO selectRoleById(Long roleId) {
		return roleMapper.selectRoleById(roleId);
	}

	/**
	 * 校验角色名称是否唯一
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	public String checkRoleNameUnique(SysRole role) {
		Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
		Long id = roleMapper.checkRoleNameUnique(role.getRoleName());
		if (StringUtils.isNotNull(id) && id.longValue() != roleId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验角色权限是否唯一
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	public String checkRoleKeyUnique(SysRole role) {
		Long roleId = StringUtils.isNull(role.getId()) ? -1L : role.getId();
		Long id = roleMapper.checkRoleKeyUnique(role.getRoleKey());
		if (StringUtils.isNotNull(id) && id.longValue() != roleId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 校验角色是否允许操作
	 * 
	 */
	@Override
	public void checkRoleAllowed(Long id) {
		if (StringUtils.isNotNull(id) && id.longValue() == 1L) {
			throw new CustomException("不允许操作超级管理员角色");
		}
	}

	/**
	 * 通过角色ID查询角色使用数量
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	@Override
	public int countUserRoleByRoleId(Long roleId) {
		return userRoleMapper.countUserRoleByRoleId(roleId);
	}

	/**
	 * 新增保存角色信息
	 * 
	 * @return 结果
	 */
	@Override
	@Transactional
	public int insertRole(SysRoleDTO roleDTO) {
		SysRole role = roleDTO.getRole();
		role.setCreateTime(System.currentTimeMillis());
		role.setCreatorId(SecurityUtils.getUserId());
		role.setIsDelete(0);
		role.setOperatorId(role.getCreatorId());
		role.setUpdateTime(role.getCreateTime());
		// 新增角色信息
		roleMapper.insert(role);
		return insertRoleMenu(roleDTO);
	}

	/**
	 * 修改保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateRole(SysRoleDTO role) {
		role.getRole().setOperatorId(SecurityUtils.getUserId());
		role.getRole().setUpdateTime(System.currentTimeMillis());
		// 修改角色信息
		roleMapper.updateById(role.getRole());
		// 删除角色与菜单关联
		roleMenuMapper.deleteRoleMenuByRoleId(role.getRole().getId());
		return insertRoleMenu(role);
	}

	/**
	 * 修改角色状态
	 * 
	 * @param role 角色信息
	 * @return 结果
	 */
	@Override
	public int updateRoleStatus(SysRole role) {
		role.setOperatorId(SecurityUtils.getUserId());
		role.setUpdateTime(System.currentTimeMillis());
		return roleMapper.updateById(role);
	}

	/**
	 * 修改数据权限信息
	 * 
	 * @return 结果
	 */
	@Override
	@Transactional
	public int authDataScope(RoleDataScopeDTO dto) {
		SysRole role = new SysRole();
		role.setId(dto.getId());
		role.setDataScope(dto.getDataScope());
		role.setOperatorId(SecurityUtils.getUserId());
		role.setUpdateTime(System.currentTimeMillis());
		// 修改角色信息
		roleMapper.updateById(role);
		// 删除角色与部门关联
		roleDeptMapper.deleteRoleDeptByRoleId(role.getId());
		// 新增角色和部门信息（数据权限）
		return insertRoleDept(dto);
	}

	/**
	 * 新增角色菜单信息
	 * 
	 * @param role 角色对象
	 */
	public int insertRoleMenu(SysRoleDTO role) {
		int rows = 1;
		// 新增用户与角色管理
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (Long menuId : role.getMenuIds()) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setRoleId(role.getRole().getId());
			rm.setMenuId(menuId);
			list.add(rm);
		}
		if (list.size() > 0) {
			rows = roleMenuMapper.insert(list);
		}
		return rows;
	}

	/**
	 * 新增角色部门信息(数据权限)
	 *
	 * @param role 角色对象
	 */
	private int insertRoleDept(RoleDataScopeDTO role) {
		int rows = 1;
		// 判断权限类型
		String dataScope = role.getDataScope();
		// 新增角色与部门（数据权限）管理
		List<SysRoleDept> list = new ArrayList<>();
		switch (dataScope) {
		case DataScopeConstants.ALL:
		case DataScopeConstants.SELF:
			return rows;
		case DataScopeConstants.DIY: {
			role.getDeptIds().stream().parallel().forEach(deptId -> {
				list.add(createRoleDept(role.getId(), deptId));
			});
			break;
		}
		case DataScopeConstants.ONLY_SELF_DEPT: {
			list.add(createRoleDept(role.getId(), SecurityUtils.getLoginUser().getUser().getDeptId()));
			break;
		}
		case DataScopeConstants.HAS_CHILD_DEPT: {
			list.add(createRoleDept(role.getId(), SecurityUtils.getLoginUser().getUser().getDeptId()));
			// 查看所有的
			List<Long> deptIds = sysDeptMapper
					.selectChildrenDeptIdsById(SecurityUtils.getLoginUser().getUser().getDeptId());
			if (deptIds == null) {
				break;
			}
			deptIds.stream().parallel().forEach(deptId -> {
				list.add(createRoleDept(role.getId(), deptId));
			});
			break;
		}
		}
		if (list.size() > 0) {
			rows = roleDeptMapper.insert(list);
		}
		return rows;
	}

	private SysRoleDept createRoleDept(long roleId, long deptId) {
		SysRoleDept rd = new SysRoleDept();
		rd.setRoleId(roleId);
		rd.setDeptId(deptId);
		return rd;
	}

	/**
	 * 批量删除角色信息
	 * 
	 * @param roleIds 需要删除的角色ID
	 * @return 结果
	 */
	@Override
	public int deleteRoleByIds(List<Long> roleIds) {
		for (Long roleId : roleIds) {
			checkRoleAllowed(roleId);
			SysRoleVO role = selectRoleById(roleId);
			if (countUserRoleByRoleId(roleId) > 0) {
				throw new CustomException(String.format("%1$s已分配,不能删除", role.getRoleName()));
			}
		}
		int count = roleMapper.batDelete(roleIds, System.currentTimeMillis());
		roleIds.parallelStream().forEach(id->{
			// 删除角色与部门关联
			roleDeptMapper.deleteRoleDeptByRoleId(id);
		});
		return count;
	}

	@Override
	public List<String> checkRoleIdsIsUsed(List<Long> roleIds) {
		return userRoleMapper.checkRoleIdsUsedByRoleIds(roleIds);
	}
}
