package com.xqx.base.exception;

/**
 * 基础异常类，自定义异常均继承此
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	/** 错误编码、详情 */
	protected ErrorCode errorCode;
	/** 错误描述 */
	protected String errMsg;

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(String errMsg, Throwable throwable) {
		super(errMsg, throwable);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
