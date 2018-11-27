package com.xqx.base.exception;

/**
 * 异常代码描述。
 */
public enum ErrorCode {

    UNKNOWN_ERROR(1000, "未知错误"),
    ILLEGAL_ARGUMENT(1001, "非法参数"),
    HTTP_ERROR(1002, "HTTP错误"),
    TOKEN_EXPIRED(2001, "Token过期"),
    TOKEN_EXCEPTION(2002, "Token异常"),
    TOKEN_BLACLIST(2003, "当前用户已列入黑名单"),
    TOKEN_REGISTERED(2004, "当前用户已经注册"),
    TOKEN_REGISTER_FAIL(2005, "用户注册失败"),
    REMOTE_EXCEPTION(3001, "远程服务异常"),
    DAO_ERROR(4001, "数据持久层异常"),
    DAO_NOTFOUND(4004, "未检查到任何数据"),
    SERVICE_ERROR(5001, "服务层异常"),
    XXL_JOB_FAIL(5002, "远程调用XXL-JOB失败"),
    HYSTRIX_FALLBACK(8001, "短路降级"),
    TIME_OUT(9001, "请求超时"),
    ;


    /** 错误编码 */
    private int code;
    /** 错误描述 */
    private String description;

    private ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据错误编码实例
     *
     * @param code 错误编码
     * @return 实例
     */
    public static ErrorCode getErrorCode(int code) {
        for (ErrorCode rerroCode : ErrorCode.values()) {
            if (rerroCode.getCode() == code) {
                return rerroCode;
            }
        }
        return ErrorCode.UNKNOWN_ERROR;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}