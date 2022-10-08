package com.tc.draw.demo.framework.web.model;

import com.tc.draw.demo.framework.web.model.code.StatusCode;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果封装
 *
 * @param <T>
 * @author Jason
 */
public class Res<T, K> {
	/**
	 * 状态码
	 */
	@ApiModelProperty("状态码，200为成功，非0为失败")
	private int code;

	/**
	 * 状态描述
	 */
	@ApiModelProperty("信息，成功为空，失败返回错误信息")
	private String msg;

	/**
	 * 返回数据
	 */
	@ApiModelProperty("数据")
	private T data;

	private K err;

	public static <T, K> Res<T, K> ok() {
		return new Res<>();
	}

	public Res() {
		this.code = SysStatusCode.OK.getCode();
		this.msg = SysStatusCode.OK.getMsg();
	}

	public Res(int resultCode, String resultMsg) {
		this.code = resultCode;
		this.msg = resultMsg;
		this.data = null;
		this.err = null;
	}
	
	public Res(int resultCode, String resultMsg, T data, K err) {
		super();
		this.code = resultCode;
		this.msg = resultMsg;
		this.data = data;
		this.err = err;
	}

	public Res(StatusCode code, T data, K err) {
		super();
		this.code = code.getCode();
		this.msg = code.getMsg();
		this.data = data;
		this.err = err;
	}
	
	public Res(StatusCode code) {
		this.code = code.getCode();
		this.msg = code.getMsg();
		this.data = null;
		this.err = null;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}

	public Res<T, K> setCode(int code) {
		this.code = code;
		return this;
	}

	public Res<T, K> setInfo(String msg) {
		this.msg = msg;
		return this;
	}

	public K getErr() {
		return err;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setErr(K err) {
		this.err = err;
	}

	public static <T, K> Res<T, K> ok(T data) {
		Res<T, K> resultInfo = ok();
		resultInfo.data = data;
		return resultInfo;
	}

	@Override
	public String toString() {
		return "Res [code=" + code + ", msg=" + msg + ", data=" + data + ", err=" + err + " ]";
	}

	public static <T, K> Res<T, K> error(StatusCode status, K err) {
		return new Res<>(status.getCode(), status.getMsg(), null, err);
	}

	public static <T, K> Res<T, K> error(StatusCode status) {
		return new Res<>(status.getCode(), status.getMsg(), null, null);
	}
	
	public static <T, K> Res<T, K> error(int statusCode, String msg) {
		return new Res<>(statusCode, msg, null, null);
	}
}
