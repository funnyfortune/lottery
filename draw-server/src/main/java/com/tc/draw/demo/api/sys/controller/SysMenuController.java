package com.tc.draw.demo.api.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.draw.demo.api.sys.model.domain.SysMenu;
import com.tc.draw.demo.api.sys.model.dto.SysMenuListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysMenuListVO;
import com.tc.draw.demo.api.sys.model.vo.TreeMenuListVO;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import com.tc.draw.demo.api.sys.service.ISysMenuService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单信息
 * 
 *
 */
@Api(value = "菜单权限接口文档", tags = { "菜单权限相关接口" })
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
	@Autowired
	private ISysMenuService menuService;

	/**
	 * 获取菜单列表
	 */
	@ApiOperation(value = "菜单权限列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:menu:list')")
	@GetMapping("/list")
	public Res<List<SysMenuListVO>, Void> list(SysMenuListDTO menu) {
		Long userId = SecurityUtils.getUserId();
		menu.setUserId(userId);
		List<SysMenuListVO> menus = menuService.selectMenuList(menu);
		return Res.ok(menus);
	}

	/**
	 * 根据菜单编号获取详细信息
	 */
	@ApiOperation(value = "菜单权限详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:menu:list')")
	@GetMapping(value = "/{menuId}")
	public Res<SysMenu, Void> getInfo(@PathVariable Long menuId) {
		return Res.ok(menuService.selectMenuById(menuId));
	}

	/**
	 * 获取菜单下拉树列表
	 */
	@ApiOperation(value = "获取菜单下拉树列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@GetMapping("/treeselect")
	public Res<List<TreeSelectVO>, Void> treeselect(SysMenuListDTO menu) {
		Long userId = SecurityUtils.getUserId();
		menu.setUserId(userId);
		List<SysMenuListVO> menus = menuService.selectMenuList(menu);
		return Res.ok(menuService.buildMenuTreeSelect(menus));
	}

	/**
	 * 加载对应角色菜单列表树
	 */
	@GetMapping(value = "/roleMenuTreeselect/{roleId}")
	public Res<TreeMenuListVO, Void> roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
		List<SysMenuListVO> menus = menuService.selectMenuList(SecurityUtils.getUserId());
		TreeMenuListVO vo = new TreeMenuListVO();
		vo.setCheckedKeys(menuService.selectMenuListByRoleId(roleId));
		vo.setMenus(menuService.buildMenuTreeSelect(menus));
		return Res.ok(vo);
	}

	/**
	 * 新增菜单
	 */
	@ApiOperation(value = "新增菜单权限")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:menu:save')")
	// @OperateAction(title = "菜单管理", businessType = BusinessType.INSERT)
	@PostMapping
	public Res<Integer, Void> add(@Validated @RequestBody SysMenu menu) {
		if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		}
		return Res.ok(menuService.insertMenu(menu));
	}

	/**
	 * 修改菜单
	 */
	@ApiOperation(value = "修改菜单权限")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:menu:update')")
	// @OperateAction(title = "菜单管理", businessType = BusinessType.UPDATE)
	@PutMapping
	public Res<Integer, Void> edit(@Validated @RequestBody SysMenu menu) {
		if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
		}
		if (menu.getMenuType().equals("M")) {
			menu.setComponent("");
		}
		return Res.ok(menuService.updateMenu(menu));
	}

	/**
	 * 删除菜单
	 */
	@ApiOperation(value = "删除菜单权限")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:menu:delete')")
	// @OperateAction(title = "菜单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{menuId}")
	public Res<Integer, Void> remove(@PathVariable("menuId") Long menuId) {
		if (menuService.hasChildByMenuId(menuId)) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "存在子菜单,不允许删除");
		}
		if (menuService.checkMenuExistRole(menuId)) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "菜单已分配,不允许删除");
		}
		return Res.ok(menuService.deleteMenuById(menuId));
	}
}