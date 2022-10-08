package com.tc.draw.demo.framework.security.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tc.draw.demo.api.org.mapper.SysRoleMapper;
import com.tc.draw.demo.api.org.mapper.SysUserPermMapper;
import com.tc.draw.demo.api.org.model.vo.SysRoleVO;
import com.tc.draw.demo.api.sys.service.ISysMenuService;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.framework.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tc.draw.demo.common.constant.DataScopeConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.ServletUtils;

/**
 * 自定义权限实现，ss取自SpringSecurity首字母
 *
 *
 */
@Service("ss")
public class PermissionService
{
    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    @Autowired
    private TokenService tokenService;
    
	@Autowired
	private SysRoleMapper roleMapper;

	@Autowired
	private ISysMenuService menuService;

	@Autowired
	private SysUserPermMapper userPermMapper;

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission)
    {
        if (StringUtils.isEmpty(permission))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
        {
            return false;
        }
        if(permission.contains("|")){
            String [] ors = permission.split("\\|");
            for(String or: ors){
                if(hasPermissions(loginUser.getPermissions(), or.trim())) {
                    return true;
                }
            }
            return false;
        }
        if(permission.contains("&")){
            String [] ands = permission.split("&");
            for(String and: ands){
                if(!hasPermissions(loginUser.getPermissions(), and.trim())) {
                    return false;
                }
            }
            return true;
        }

        return hasPermissions(loginUser.getPermissions(), permission.trim());
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    public boolean lacksPermi(String permission)
    {
        return hasPermi(permission) != true;
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermi(String permissions)
    {
        if (StringUtils.isEmpty(permissions))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getPermissions()))
        {
            return false;
        }
        Set<String> authorities = loginUser.getPermissions();
        for (String permission : permissions.split(PERMISSION_DELIMETER))
        {
            if (permission != null && hasPermissions(authorities, permission))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role)
    {
        if (StringUtils.isEmpty(role))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
        {
            return false;
        }
        for (SysRoleVO sysRoleVO : loginUser.getUser().getRoles())
        {
            String roleKey = sysRoleVO.getRoleKey();
            if (SUPER_ADMIN.contains(roleKey) || roleKey.contains(StringUtils.trim(role)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean lacksRole(String role)
    {
        return hasRole(role) != true;
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public boolean hasAnyRoles(String roles)
    {
        if (StringUtils.isEmpty(roles))
        {
            return false;
        }
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(loginUser.getUser().getRoles()))
        {
            return false;
        }
        for (String role : roles.split(ROLE_DELIMETER))
        {
            if (hasRole(role))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission)
    {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trim(permission));
    }
    
    
	/**
	 * 获取角色数据权限
	 * 
	 * @param user 用户信息
	 * @return 角色权限信息
	 */
	public Set<String> getRolePermission() {
		Set<String> roles = new HashSet<String>();
		// 管理员拥有所有权限
		if (SecurityUtils.isAdmin(SecurityUtils.getUserId())) {
			roles.add("admin");
		} else {
			roles.addAll(selectRolePermissionByUserId(SecurityUtils.getUserId()));
		}
		return roles;
	}

	/**
	 * 根据用户ID查询权限
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */
	private Set<String> selectRolePermissionByUserId(Long userId) {
		List<SysRoleVO> perms = roleMapper.selectRolePermissionByUserId(userId);
		Set<String> permsSet = new HashSet<>();
		for (SysRoleVO perm : perms) {
			if (StringUtils.isNotNull(perm)) {
				permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
			}
		}
		return permsSet;
	}

	/**
	 * 获取菜单数据权限
	 * 
	 * @param user 用户信息
	 * @return 菜单权限信息
	 */
	public Set<String> getMenuPermission(long userId) {
		Set<String> perms = new HashSet<String>();
		// 管理员拥有所有权限
		if (SecurityUtils.isAdmin(userId)) {
			perms.add("*:*:*");
		} else {
			perms.addAll(menuService.selectMenuPermsByUserId(userId));
		}
		return perms;
	}

	/**
	 * 数据权限
	 * @param userId
	 * @return
	 */
	public String getDataScope(Long userId) {

		if (SecurityUtils.isAdmin(userId)) {
			return "";// 所有权限
		}
		List<SysRoleVO> roles = roleMapper.selectRolePermissionByUserId(userId);
		if (roles == null) { // 没有角色，不能查询任何数据, 通配符匹配
			return " INNER JOIN (select 0 perm_user_id ) dataScope ON dataScope.perm_user_id=%s";
		}
		for (SysRoleVO role : roles) {
			if (role.getDataScope().equals(DataScopeConstants.ALL)) { // 可以查看全部的
				return "";
			}
			if (role.getDataScope().equals(DataScopeConstants.SELF)) { // 只可以查看自己的
				return " INNER JOIN (select " + userId +" perm_user_id) dataScope ON dataScope.perm_user_id=%s";
			}
		}
		// 清除用户Id权限
		userPermMapper.deleteByUserId(userId);
		// 更新用户Id权限
		userPermMapper.inserByUserId(userId);
		return " INNER JOIN (select perm_user_id from sys_user_perm where user_id="+userId+") dataScope ON dataScope.perm_user_id=%s";
	}
}
