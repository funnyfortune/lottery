package com.tc.draw.demo.common.utils;

import com.tc.draw.demo.common.constant.DefaultRoleIdConstants;
import com.tc.draw.demo.common.constant.HttpStatus;
import com.tc.draw.demo.exception.CustomException;
import com.tc.draw.demo.framework.security.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全服务工具类
 * 
 *
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
    public static String getUsername()
    {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    
	/**
	 * 
	 * 获取用户Id
	 * @author 幽望
	 * @date:   22 May 2020 3:59:23 PM   
	 * @Description:
	 * @return
	 */
    public static Long getUserId()
    {
        try
        {
            return getLoginUser().getUser().getId();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * 获取登录人的数据权限SQL
     * @author 幽望
     * @date:   22 May 2020 4:02:09 PM   
     * @Description:
     * @param dataScopeColumn
     * @return
     */
    public static String getDataScope(String dataScopeColumn) {
    	return String.format(getLoginUser().getDataScope(), dataScopeColumn);
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new CustomException("获取用户信息异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && DefaultRoleIdConstants.SUPER_ADMIN == userId;
    }
}
