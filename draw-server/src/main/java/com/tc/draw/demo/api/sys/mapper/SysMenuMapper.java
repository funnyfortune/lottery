package com.tc.draw.demo.api.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.sys.model.domain.SysMenu;
import com.tc.draw.demo.api.sys.model.dto.SysMenuListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysMenuListVO;

/**
 * 菜单表 数据层
 * 
 *
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/**
	 * 查询系统菜单列表
	 * 
	 * @param menu 菜单信息
	 * @return 菜单列表
	 */
	@SelectProvider(type = SysMenuMapperSQL.class, method = "selectMenuList")
	List<SysMenuListVO> selectMenuList(SysMenuListDTO menu);


	/**
	 * 根据用户查询系统菜单列表
	 * 
	 * @param menu 菜单信息
	 * @return 菜单列表
	 */
	@SelectProvider(type = SysMenuMapperSQL.class, method = "selectMenuListByUserId")
	List<SysMenuListVO> selectMenuListByUserId(SysMenuListDTO menu);

	/**
	 * 根据用户ID查询权限
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	@Select("select distinct m.perms from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id"
			+ " left join sys_user_role ur on rm.role_id = ur.role_id"
			+ " left join sys_role r on r.id = ur.role_id"
			+ "	where m.status = '0' and r.status = '0' and ur.user_id = #{userId}")
	List<String> selectMenuPermsByUserId(Long userId);

	/**
	 * 根据用户ID查询菜单
	 * 
	 * @return 菜单列表
	 */
	@Select("select distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.status, ifnull(m.perms,'') as perms,"
			+ " m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time"
			+ "	from sys_menu m where m.menu_type in ('M', 'C') and m.status = 0"
			+ "	order by m.parent_id, m.order_num")
	List<SysMenuListVO> selectMenuTreeAll();

	/**
	 * 根据用户ID查询菜单
	 * 
	 * @param username 用户ID
	 * @return 菜单列表
	 */
	@Select("select distinct m.menu_id, m.parent_id, m.menu_name, m.path, m.component, m.visible, m.status,"
			+ " ifnull(m.perms,'') as perms, m.is_frame, m.menu_type, m.icon, m.order_num, m.create_time"
			+ " from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id"
			+ " left join sys_user_role ur on rm.role_id = ur.role_id"
			+ " left join sys_role ro on ur.role_id = ro.id" + " left join sys_user u on ur.user_id = u.id"
			+ " where u.id = #{userId} and m.menu_type in ('M', 'C') and m.status = 0  AND ro.status = 0"
			+ " order by m.parent_id, m.order_num")
	List<SysMenuListVO> selectMenuTreeByUserId(Long userId);

	/**
	 * 根据角色ID查询菜单树信息
	 * 
	 * @param roleId 角色ID
	 * @return 选中菜单列表
	 */
	@Select("select m.menu_id, m.parent_id from sys_menu m left join sys_role_menu rm on m.menu_id = rm.menu_id"
			+ "  where rm.role_id = #{roleId}"
			+ "  and m.menu_id not in (select m.parent_id from sys_menu m inner join sys_role_menu rm on m.menu_id = rm.menu_id and rm.role_id = #{roleId})"
			+ "	 order by m.parent_id, m.order_num")
	List<Integer> selectMenuListByRoleId(Long roleId);

	/**
	 * 根据菜单ID查询信息
	 * 
	 * @param menuId 菜单ID
	 * @return 菜单信息
	 */
	@Select("select menu_id, menu_name, parent_id, order_num, path, component, is_frame, menu_type, visible, status, ifnull(perms,'') as perms, icon, create_time from sys_menu where menu_id = #{menuId}")
	SysMenu selectMenuById(Long menuId);

	/**
	 * 是否存在菜单子节点
	 * 
	 * @param menuId 菜单ID
	 * @return 结果
	 */
	@Select("select count(1) from sys_menu where parent_id = #{menuId} ")
	int hasChildByMenuId(Long menuId);


	/**
	 * 删除菜单管理信息
	 * 
	 * @param menuId 菜单ID
	 * @return 结果
	 */
	@Delete("delete from sys_menu where menu_id = #{menuId}")
	int deleteMenuById(Long menuId);

	/**
	 * 校验菜单名称是否唯一
	 * 
	 * @param menuName 菜单名称
	 * @param parentId 父菜单ID
	 * @return 结果
	 */
	@Select("select menu_id from sys_menu where menu_name=#{menuName} and parent_id = #{parentId}")
	Long checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Long parentId);
}
