package com.tc.draw.demo.api.sys.controller;

import com.tc.draw.demo.api.org.model.vo.LoginUserInfoVO;
import com.tc.draw.demo.api.org.model.vo.TokenVO;
import com.tc.draw.demo.api.sys.model.dto.UserLoginDTO;
import com.tc.draw.demo.api.sys.model.vo.SysMenuListVO;
import com.tc.draw.demo.api.sys.model.vo.router.RouterVO;
import com.tc.draw.demo.api.sys.service.ISysMenuService;
import com.tc.draw.demo.common.utils.ServletUtils;
import com.tc.draw.demo.framework.security.LoginUser;
import com.tc.draw.demo.framework.security.SysUserInfo;
import com.tc.draw.demo.framework.security.service.PermissionService;
import com.tc.draw.demo.framework.security.service.SysLoginService;
import com.tc.draw.demo.framework.security.service.TokenService;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 *
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private TokenService tokenService;



    /**
     * 登录方法
     *
     * @return 结果
     */
    @PostMapping("/login")
    public Res<TokenVO, Boolean> login(@RequestBody UserLoginDTO dto, HttpServletRequest request)
    {
    	if (dto == null) {
			return Res.error(SysStatusCode.PARAM_ERROR);
		}
		return loginService.login(dto, request);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public Res<LoginUserInfoVO, Void> getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        SysUserInfo user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission();
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user.getId());
        LoginUserInfoVO vo = new LoginUserInfoVO();
        vo.setUser(user);
        vo.setRoles(roles);
        vo.setPermissions(permissions);
        vo.setMsgCount(0);
        return Res.ok(vo);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Res<List<RouterVO>, Void> getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest().getHeader(tokenService.getTokenName()));
        // 用户信息
        SysUserInfo user = loginUser.getUser();
        List<SysMenuListVO> menus = menuService.selectMenuTreeByUserId(user.getId());
        return Res.ok(menuService.buildMenus(menus));
    }
}
