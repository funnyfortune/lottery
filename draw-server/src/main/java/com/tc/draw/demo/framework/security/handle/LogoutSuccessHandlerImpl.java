package com.tc.draw.demo.framework.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import com.tc.draw.demo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.tc.draw.demo.common.constant.HttpStatus;
import com.tc.draw.demo.common.utils.ServletUtils;

import com.tc.draw.demo.framework.security.LoginUser;
import com.tc.draw.demo.framework.security.service.TokenService;
import com.tc.draw.demo.framework.web.model.Res;

/**
 * 自定义退出处理类 返回成功
 * 
 *
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler
{
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        LoginUser loginUser = tokenService.getLoginUser(request.getHeader(tokenService.getTokenName()));
        if (StringUtils.isNotNull(loginUser))
        {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(response, JSONUtil.toJsonStr(new Res<Void, Void>(HttpStatus.SUCCESS, "退出成功")));
    }
}
