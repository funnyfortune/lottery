package com.tc.draw.demo.api.sys.service;

import java.util.List;
import java.util.Set;

import com.tc.draw.demo.api.sys.model.domain.SysMenu;
import com.tc.draw.demo.api.sys.model.dto.SysMenuListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysMenuListVO;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import com.tc.draw.demo.api.sys.model.vo.router.RouterVO;

/**
 * 菜单 业务层
 * 
 *
 */
public interface ISysMenuService
{
    /**
     * 根据用户查询系统菜单列表
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenuListVO> selectMenuList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenuListVO> selectMenuList(SysMenuListDTO menu);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<SysMenuListVO> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     * 
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    public List<Integer> selectMenuListByRoleId(Long roleId);

    /**
     * 构建前端路由所需要的菜单
     * 
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVO> buildMenus(List<SysMenuListVO> menus);

    /**
     * 构建前端所需要树结构
     * 
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<SysMenuListVO> buildMenuTree(List<SysMenuListVO> menus);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    public List<TreeSelectVO> buildMenuTreeSelect(List<SysMenuListVO> menus);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysMenu selectMenuById(Long menuId);

    /**
     * 是否存在菜单子节点
     * 
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean hasChildByMenuId(Long menuId);

    /**
     * 查询菜单是否存在角色
     * 
     * @param menuId 菜单ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkMenuExistRole(Long menuId);

    /**
     * 新增保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     * 修改保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(SysMenu menu);
}
