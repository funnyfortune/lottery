package com.tc.draw.demo.api.sys.controller;

import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.common.utils.VerifyCodeUtils;
import com.tc.draw.demo.common.utils.sign.Base64;
import com.tc.draw.demo.framework.redis.RedisCache;
import com.tc.draw.demo.framework.redis.RedisConstant;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 *
 */
@RestController
public class CaptchaController {

	private final Log LOG = LogFactory.getLog(CaptchaController.class);
	@Autowired
	private RedisCache redisCache;



	/**
	 * 生成验证码
	 */
	@GetMapping("/captchaImage")
	public Res<String, Void> getCode(String account, HttpServletResponse response) throws IOException {
		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		redisCache.setCacheObject(RedisConstant.CACHE_ADMIN_CAPTCHA+"-"+account,verifyCode, 5*60, TimeUnit.SECONDS);
		// 生成图片
		int w = 111, h = 36;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		VerifyCodeUtils.outputImage(w, h, stream, verifyCode);
		try {
			return Res.ok(Base64.encode(stream.toByteArray()));
		} catch (Exception e) {
			LOG.error(e, e);
			return Res.error(SysStatusCode.UNKNOW_ERROR);
		} finally {
			stream.close();
		}
	}

	/**
	 * 通过账号检查是否需要验证码
	 *
	 * @param account
	 * @return
	 */
	@GetMapping("/captchaNeed")
	public Res<Boolean, Void> isNeedCaptcha(String account) {
		int errCount = getErrorCount(account);
		return Res.ok(errCount > 5 ? true : false);
	}

	private int getErrorCount(String account) {
		String errCount = redisCache.getCacheObject(RedisConstant.CACHE_ADMIN_ERR_COUNT+"-"+account);
		if (StringUtils.isEmpty(errCount)) {
			return 0;
		}
		return Integer.parseInt(errCount);
	}
}
