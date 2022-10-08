package com.tc.draw.demo.exception;

import javax.servlet.http.HttpServletResponse;

import com.tc.draw.demo.common.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.tc.draw.demo.common.constant.HttpStatus;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;

/**
 * 全局异常处理器
 * 
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Log log = LogFactory.getLog(GlobalExceptionHandler.class);

	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Res<Void, Void> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error(e.getMessage(), e);
		return Res.error(SysStatusCode.HTTP_METHOD_NO_SUPPORT);
	}
	
	/**
	 * 基础异常
	 */
	@ExceptionHandler(BaseException.class)
	public Res<Void, Void> baseException(BaseException e) {
		log.error(e.getMessage(), e);
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), e.getMessage());
	}

	/**
	 * 业务异常
	 */
	@ExceptionHandler(CustomException.class)
	public Res<Void, Void> businessException(CustomException e) {
		log.error(e.getMessage(), e);
		if (StringUtils.isNull(e.getCode())) {
			return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), e.getMessage());
		}
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), e.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	public Res<Void, Void> handleAuthorizationException(AccessDeniedException e) {
		log.error(e.getMessage());
		return new Res<>(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
	}

	@ExceptionHandler(AccountExpiredException.class)
	public Res<Void, Void> handleAccountExpiredException(AccountExpiredException e) {
		log.error(e.getMessage(), e);
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public Res<Void, Void> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return Res.error(SysStatusCode.UNKNOW_ERROR);
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(BindException.class)
	public Res<Void, Void> validatedBindException(BindException e) {
		log.error(e.getMessage(), e);
		String message = e.getAllErrors().get(0).getDefaultMessage();
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), message);
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Res<Void, Void> validExceptionHandler(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return new Res<>(SysStatusCode.UNKNOW_ERROR.getCode(), message);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Res<Void, Void> handlerNoFoundException(HttpServletResponse response, Exception e) {
		log.error(e.getMessage(), e);

		response.setStatus(HttpStatus.NOT_FOUND);
		return Res.error(SysStatusCode.URL_NOT_FOUND);
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Res<Void, Void> handleDuplicateKeyException(DuplicateKeyException e) {
		log.error(e.getMessage(), e);
		return Res.error(SysStatusCode.DB_EXIST_RECODE);
	}
}
