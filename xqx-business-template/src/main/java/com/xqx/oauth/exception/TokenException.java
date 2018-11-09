package com.xqx.oauth.exception;

import com.xqx.base.exception.BaseException;
import com.xqx.base.exception.ErrorCode;

/**
 * Token其他异常
 */
public class TokenException extends BaseException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
    private String errMsg;

    public TokenException(ErrorCode errorCode, String errMsg){
        super(errMsg);
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

    public TokenException(Throwable throwable, ErrorCode errorCode, String errMsg) {
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
