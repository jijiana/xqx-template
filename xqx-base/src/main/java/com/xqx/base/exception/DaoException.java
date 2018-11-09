package com.xqx.base.exception;


/**
 * 持久化访问异常类
 */
public class DaoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
    private String errMsg;

    public DaoException(ErrorCode errorCode, String errMsg){
        super(errMsg);
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public DaoException(Throwable throwable, ErrorCode errorCode, String errMsg) {
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
