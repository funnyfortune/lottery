package com.tc.draw.demo.api.org.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.draw.demo.api.org.model.domain.SysRole;
import com.tc.draw.demo.api.org.model.dto.role.RoleDataScopeDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleDTO;
import com.tc.draw.demo.api.org.model.dto.role.SysRoleListDTO;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import com.tc.draw.demo.api.org.service.ISysRoleService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import com.tc.draw.demo.framework.web.model.page.PageVO;

/**
 * 角色信息
 *
 *
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
	@Autowired
	private ISysRoleService roleService;

	@PreAuthorize("@ss.hasPermi('system:role:list')")
	@GetMapping("/list")
	public Res<PageVO<SysRoleVO>, Void> list(SysRoleListDTO role) {
		startPage();
		List<SysRoleVO> list = roleService.selectRoleList(role);
		return Res.ok(getDataTable(list));
	}

	// @OperateAction(title = "角色管理", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('system:role:export')")
	@GetMapping("/export")
	public Res<String, Void> export(SysRoleListDTO role) {
		List<SysRoleVO> list = roleService.selectRoleList(role);
		ExcelUtil<SysRoleVO> util = new ExcelUtil<SysRoleVO>(SysRoleVO.class);
		return util.exportExcel(list, "角色数据");
	}

	/**
	 * 根据角色编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:role:list')")
	@GetMapping(value = "/info")
	public Res<SysRoleVO, Void> getInfo(Long roleId) {
		return Res.ok(roleService.selectRoleById(roleId));
	}

	/**
	 * 新增角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:save')")
	// @OperateAction(title = "角色管理", businessType = BusinessType.INSERT)
	@PostMapping("save")
	public Res<Integer, Void> add(@Validated @RequestBody SysRoleDTO role) {

		if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role.getRole()))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(),
					"新增角色'" + role.getRole().getRoleName() + "'失败，角色名称已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role.getRole()))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(),
					"新增角色'" + role.getRole().getRoleName() + "'失败，角色权限已存在");
		}
		return Res.ok(roleService.insertRole(role));

	}

	/**
	 * 修改保存角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:update')")
	// @OperateAction(title = "角色管理", businessType = BusinessType.UPDATE)
	@PostMapping("/update")
	public Res<Integer, Void> edit(@Validated @RequestBody SysRoleDTO role) {
		if(role.getRole().getId()<4L && !SecurityUtils.isAdmin(SecurityUtils.getUserId())){
			return Res.error(SysStatusCode.PARAM_ERROR.getCode(), "不允许修改该权限");
		}
		roleService.checkRoleAllowed(role.getRole().getId());
		if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role.getRole()))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(),
					"修改角色'" + role.getRole().getRoleName() + "'失败，角色名称已存在");
		} else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role.getRole()))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(),
					"修改角色'" + role.getRole().getRoleName() + "'失败，角色权限已存在");
		}
		return Res.ok(roleService.updateRole(role));
	}

	/**
	 * 修改保存数据权限
	 */
	@PreAuthorize("@ss.hasPermi('system:role:update')")
	// @OperateAction(title = "角色管理", businessType = BusinessType.UPDATE)
	@PostMapping("/dataScope")
	public Res<Integer, Void> dataScope(@RequestBody RoleDataScopeDTO role) {
		roleService.checkRoleAllowed(role.getId());
		return Res.ok(roleService.authDataScope(role));
	}

	/**
	 * 状态修改
	 */
	@PreAuthorize("@ss.hasPermi('system:role:update')")
	// @OperateAction(title = "角色管理", businessType = BusinessType.UPDATE)
	@PostMapping("/changeStatus")
	public Res<Integer, Void> changeStatus(@RequestBody SysRole role) {
		roleService.checkRoleAllowed(role.getId());
		return Res.ok(roleService.updateRoleStatus(role));
	}

	/**
	 * 删除角色
	 */
	@PreAuthorize("@ss.hasPermi('system:role:delete')")
	// @OperateAction(title = "角色管理", businessType = BusinessType.DELETE)
	@PostMapping("delete")
	public Res<Integer, Void> remove(@RequestBody List<Long> roleIds) {
		// 判断角色是否被使用
		List<String> usedIds = roleService.checkRoleIdsIsUsed(roleIds);
		if (usedIds != null && usedIds.size() > 0) {
			String idString = usedIds.stream().collect(Collectors.joining(", ", "[", "]"));
			return Res.error(SysStatusCode.DB_EXIST_RECODE.getCode(), "角色" + idString + "存在用户，不能删除");
		}
		return Res.ok(roleService.deleteRoleByIds(roleIds));
	}

	/**
	 * 获取角色选择框列表
	 */
	@PreAuthorize("@ss.hasPermi('system:role:list')")
	@GetMapping("/optionselect")
	public Res<List<SysRoleVO>, Void> optionselect() {
		return Res.ok(roleService.selectRoleAll());
	}
}
