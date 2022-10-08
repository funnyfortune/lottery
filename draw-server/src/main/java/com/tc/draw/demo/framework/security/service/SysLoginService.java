package com.tc.draw.demo.framework.security.service;

import com.tc.draw.demo.api.org.model.vo.TokenVO;
import com.tc.draw.demo.api.sys.model.dto.UserLoginDTO;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.framework.redis.RedisCache;
import com.tc.draw.demo.framework.redis.RedisConstant;
import com.tc.draw.demo.framework.security.LoginUser;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.TokenErrStatusCode;
import com.tc.draw.demo.framework.web.model.code.UserErrorStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 登录校验方法
 * 
 *
 */
@Component
public class SysLoginService {
	
	private static final Log LOG = LogFactory.getLog(SysLoginService.class);

	@Autowired
	private TokenService tokenService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private RedisCache redisService;

	/**
	 * 登录验证
	 *
	 * @return 结果
	 */
	public Res<TokenVO, Boolean> login(UserLoginDTO dto, HttpServletRequest request) {
		String username = dto.getAccount();
		String password = dto.getPassword();
		int errCount = getErrorCount(dto.getAccount());
		// 如果验证码错误次数大于5，比较用户对比验证码
		if (errCount > 5) {
			String verify = redisService.getCacheObject( RedisConstant.CACHE_ADMIN_CAPTCHA+"-"+dto.getAccount());
			if (StringUtils.isEmpty(verify)) {
				return Res.error(TokenErrStatusCode.CAPTCHA_NOT_EXIST, true);
			}
			if (!verify.equalsIgnoreCase(dto.getVerify())) {
				return Res.error(TokenErrStatusCode.CAPTCHA_ERROR, true);
			}
		}

		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (Exception e) {
			LOG.error("登录出错", e);
			long count = redisService.hIncrBy(RedisConstant.CACHE_ADMIN_ERR_COUNT, dto.getAccount(), 1);
			return Res.error(UserErrorStatus.ADMIN_LOGIN_FAILD,count > 5 ? true : false);
		}
		redisService.setCacheObject( RedisConstant.CACHE_ADMIN_ERR_COUNT+"-"+dto.getAccount(), "0");
		redisService.deleteObject(RedisConstant.CACHE_ADMIN_CAPTCHA+"-"+ dto.getAccount());
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		// 生成token
		String token = tokenService.createToken(loginUser);
		TokenVO vo = new TokenVO(token, loginUser.getExpireTime());
		return Res.ok(vo);
	}

	private int getErrorCount(String account) {
		String errCount = redisService.getCacheObject(RedisConstant.CACHE_ADMIN_ERR_COUNT+"-"+account);
		if (StringUtils.isEmpty(errCount)) {
			return 0;
		}
		return Integer.parseInt(errCount);
	}
}
