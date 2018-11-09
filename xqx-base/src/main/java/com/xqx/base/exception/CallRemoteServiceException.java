package com.xqx.base.exception;

/**
 * 调用远程服务异常
 */
public class CallRemoteServiceException extends BaseException {
	private static final long serialVersionUID = 1L;

	public CallRemoteServiceException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
		errMsg = errorCode.getDescription();
	}

	public CallRemoteServiceException(ErrorCode errorCode, String errMsg) {
		super(errMsg);
		this.errorCode = errorCode;
		this.errMsg = errMsg;
	}

	public CallRemoteServiceException(Throwable throwable, ErrorCode errorCode, String errMsg) {
		super(throwable.getMessage(), throwable);
		this.errorCode = errorCode;
		this.errMsg = errMsg;
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
