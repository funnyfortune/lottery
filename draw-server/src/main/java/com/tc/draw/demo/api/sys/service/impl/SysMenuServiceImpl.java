package com.tc.draw.demo.api.sys.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.draw.demo.api.org.mapper.SysRoleMenuMapper;
import com.tc.draw.demo.api.sys.mapper.SysMenuMapper;
import com.tc.draw.demo.api.sys.model.domain.SysMenu;
import com.tc.draw.demo.api.sys.model.dto.SysMenuListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysMenuListVO;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import com.tc.draw.demo.api.sys.model.vo.router.MetaVO;
import com.tc.draw.demo.api.sys.model.vo.router.RouterVO;
import com.tc.draw.demo.api.sys.service.ISysMenuService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;

/**
 * 菜单 业务层处理
 * 
 *
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {

	@Autowired
	private SysMenuMapper menuMapper;

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;

	/**
	 * 根据用户查询系统菜单列表
	 * 
	 * @param userId 用户ID
	 * @return 菜单列表
	 */
	@Override
	public List<SysMenuListVO> selectMenuList(Long userId) {
		SysMenuListDTO dto = new SysMenuListDTO();
		dto.setUserId(userId);
		return selectMenuList(dto);
	}

	/**
	 * 查询系统菜单列表
	 * 
	 * @param menu 菜单信息
	 * @return 菜单列表
	 */
	@Override
	public List<SysMenuListVO> selectMenuList(SysMenuListDTO menu) {
		List<SysMenuListVO> menuList = null;
		// 管理员显示所有菜单信息
		if (SecurityUtils.isAdmin(menu.getUserId())) {
			menuList = menuMapper.selectMenuList(menu);
		} else {
			menuList = menuMapper.selectMenuListByUserId(menu);
		}
		return menuList;
	}

	/**
	 * 根据用户ID查询权限
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	@Override
	public Set<String> selectMenuPermsByUserId(Long userId) {
		List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotEmpty(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	/**
	 * 根据用户ID查询菜单
	 * 
	 * @param userId 用户名称
	 * @return 菜单列表
	 */
	@Override
	public List<SysMenuListVO> selectMenuTreeByUserId(Long userId) {
		List<SysMenuListVO> menus = null;
		if (SecurityUtils.isAdmin(userId)) {
			menus = menuMapper.selectMenuTreeAll();
		} else {
			menus = menuMapper.selectMenuTreeByUserId(userId);
		}
		return getChildPerms(menus, 0);
	}

	/**
	 * 根据角色ID查询菜单树信息
	 * 
	 * @param roleId 角色ID
	 * @return 选中菜单列表
	 */
	@Override
	public List<Integer> selectMenuListByRoleId(Long roleId) {
		return menuMapper.selectMenuListByRoleId(roleId);
	}

	/**
	 * 构建前端路由所需要的菜单
	 * 
	 * @param menus 菜单列表
	 * @return 路由列表
	 */
	@Override
	public List<RouterVO> buildMenus(List<SysMenuListVO> menus) {
		List<RouterVO> routers = new LinkedList<RouterVO>();
		for (SysMenuListVO menu : menus) {
			RouterVO router = new RouterVO();
			router.setHidden("1".equals(menu.getVisible()));
			router.setName(StrUtil.upperFirst(menu.getPath()));
			router.setPath(getRouterPath(menu));
			router.setType(menu.getMenuType());
			router.setIsFrame(menu.getIsFrame());
			router.setComponent(StringUtils.isEmpty(menu.getComponent()) ? "Layout" : menu.getComponent());
			router.setMeta(new MetaVO(menu.getMenuName(), menu.getIcon()));
			List<SysMenuListVO> cMenus = menu.getChildren();
			if (!cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType())) {
				router.setAlwaysShow(true);
				router.setRedirect("noRedirect");
				router.setChildren(buildMenus(cMenus));
			}
			routers.add(router);
		}
		return routers;
	}

	/**
	 * 构建前端所需要树结构
	 * 
	 * @param menus 菜单列表
	 * @return 树结构列表
	 */
	@Override
	public List<SysMenuListVO> buildMenuTree(List<SysMenuListVO> menus) {
		List<SysMenuListVO> returnList = new ArrayList<>();
		for (Iterator<SysMenuListVO> iterator = menus.iterator(); iterator.hasNext();) {
			SysMenuListVO t = (SysMenuListVO) iterator.next();
			// 根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == 0) {
				recursionFn(menus, t);
				returnList.add(t);
			}
		}
		if (returnList.isEmpty()) {
			returnList = menus;
		}
		return returnList;
	}

	/**
	 * 构建前端所需要下拉树结构
	 * 
	 * @param menus 菜单列表
	 * @return 下拉树结构列表
	 */
	@Override
	public List<TreeSelectVO> buildMenuTreeSelect(List<SysMenuListVO> menus) {
		List<SysMenuListVO> menuTrees = buildMenuTree(menus);
		return menuTrees.stream().map(TreeSelectVO::new).collect(Collectors.toList());
	}

	/**
	 * 根据菜单ID查询信息
	 * 
	 * @param menuId 菜单ID
	 * @return 菜单信息
	 */
	@Override
	public SysMenu selectMenuById(Long menuId) {
		return menuMapper.selectMenuById(menuId);
	}

	/**
	 * 是否存在菜单子节点
	 * 
	 * @param menuId 菜单ID
	 * @return 结果
	 */
	@Override
	public boolean hasChildByMenuId(Long menuId) {
		int result = menuMapper.hasChildByMenuId(menuId);
		return result > 0 ? true : false;
	}

	/**
	 * 查询菜单使用数量
	 * 
	 * @param menuId 菜单ID
	 * @return 结果
	 */
	@Override
	public boolean checkMenuExistRole(Long menuId) {
		int result = roleMenuMapper.checkMenuExistRole(menuId);
		return result > 0 ? true : false;
	}

	/**
	 * 新增保存菜单信息
	 * 
	 * @param menu 菜单信息
	 * @return 结果
	 */
	@Override
	public int insertMenu(SysMenu menu) {
		menu.setUpdateTime(System.currentTimeMillis());
		menu.setOperatorId(SecurityUtils.getUserId());
		menu.setCreateTime(menu.getUpdateTime());
		menu.setCreatorId(menu.getOperatorId());
		return menuMapper.insert(menu);
	}

	/**
	 * 修改保存菜单信息
	 * 
	 * @param menu 菜单信息
	 * @return 结果
	 */
	@Override
	public int updateMenu(SysMenu menu) {
		menu.setUpdateTime(System.currentTimeMillis());
		menu.setOperatorId(SecurityUtils.getUserId());
		return menuMapper.updateById(menu);
	}

	/**
	 * 删除菜单管理信息
	 * 
	 * @param menuId 菜单ID
	 * @return 结果
	 */
	@Override
	public int deleteMenuById(Long menuId) {
		return menuMapper.deleteMenuById(menuId);
	}

	/**
	 * 校验菜单名称是否唯一
	 * 
	 * @param menu 菜单信息
	 * @return 结果
	 */
	@Override
	public String checkMenuNameUnique(SysMenu menu) {
		Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
		Long id = menuMapper.checkMenuNameUnique(menu.getMenuName(), menu.getParentId());
		if (StringUtils.isNotNull(id) && id.longValue() != menuId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 获取路由地址
	 * 
	 * @param menu 菜单信息
	 * @return 路由地址
	 */
	public String getRouterPath(SysMenuListVO menu) {
		String routerPath = menu.getPath();
		// 非外链并且是一级目录
		if (0 == menu.getParentId() && menu.getIsFrame().intValue() == 1) {
			routerPath = "/" + menu.getPath();
		}
		return routerPath;
	}

	/**
	 * 根据父节点的ID获取所有子节点
	 * 
	 * @param list     分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	public List<SysMenuListVO> getChildPerms(List<SysMenuListVO> list, int parentId) {
		List<SysMenuListVO> returnList = new ArrayList<>();
		for (Iterator<SysMenuListVO> iterator = list.iterator(); iterator.hasNext();) {
			SysMenuListVO t = (SysMenuListVO) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == parentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 * 
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<SysMenuListVO> list, SysMenuListVO t) {
		// 得到子节点列表
		List<SysMenuListVO> childList = getChildList(list, t);
		t.setChildren(childList);
		for (SysMenuListVO tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<SysMenuListVO> it = childList.iterator();
				while (it.hasNext()) {
					SysMenuListVO n = (SysMenuListVO) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<SysMenuListVO> getChildList(List<SysMenuListVO> list, SysMenuListVO t) {
		List<SysMenuListVO> tlist = new ArrayList<>();
		Iterator<SysMenuListVO> it = list.iterator();
		while (it.hasNext()) {
			SysMenuListVO n = (SysMenuListVO) it.next();
			if (n.getParentId().longValue() == t.getMenuId().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysMenuListVO> list, SysMenuListVO t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
}
