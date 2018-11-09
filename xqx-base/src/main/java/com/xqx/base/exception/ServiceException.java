package com.xqx.base.exception;

/**
 * 业务异常类
 */
public class ServiceException extends BaseException {
	private static final long serialVersionUID = 1L;

	public ServiceException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
		this.errMsg = errorCode.getDescription();
	}

	public ServiceException(ErrorCode errorCode, String errMsg) {
		super(errMsg);
		this.errorCode = errorCode;
		this.errMsg = errorCode.getDescription() + "。" + errMsg;
	}

	public ServiceException(Throwable throwable, ErrorCode errorCode, String errMsg) {
		super(throwable.getMessage(), throwable);
		this.errorCode = errorCode;
		this.errMsg = errorCode.getDescription() + "。" + errMsg;
	}

	public ServiceException(BaseException e) {
		super(e.getErrMsg(), e);
		this.errorCode = e.getErrorCode();
		this.errMsg = e.getErrMsg();
	}
}
