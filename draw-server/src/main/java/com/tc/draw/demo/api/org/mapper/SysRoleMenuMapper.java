package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * 角色与菜单关联表 数据层
 * 
 *
 */
@Mapper
public interface SysRoleMenuMapper
{
    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
	@Select("select count(1) from sys_role_menu where menu_id = #{menuId}")
    int checkMenuExistRole(Long menuId);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
	@Delete("delete from sys_role_menu where role_id=#{roleId}")
    int deleteRoleMenuByRoleId(Long roleId);

    /**
     * 批量新增角色菜单信息
     * 
     * @param list 角色菜单列表
     * @return 结果
     */
	
	@InsertProvider(type=SysRoleMenuMapperSQL.class, method="insert")
    int insert(@Param("list")List<SysRoleMenu> list);
}
