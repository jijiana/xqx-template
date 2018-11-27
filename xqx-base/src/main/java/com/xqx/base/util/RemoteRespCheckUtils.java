package com.xqx.base.util;

import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.vo.ResponseMessage;

public class RemoteRespCheckUtils {
	/**
	 * 检查返回结果
	 * 
	 * @param responseMessage 待检测的实体
	 * @throws CallRemoteServiceException 远程调用异常，返回码不为0错误
	 */
	public static void checkResponse(ResponseMessage<?> responseMessage) throws CallRemoteServiceException {
		if (responseMessage.getStatus() != 0) {
			throw new CallRemoteServiceException(ErrorCode.getErrorCode(responseMessage.getStatus()),
					responseMessage.getMessage());
		}
	}

	public static void checkJobResponse(String resp) throws CallRemoteServiceException {
		if (!resp.contains("200")) {
			throw new CallRemoteServiceException(ErrorCode.REMOTE_EXCEPTION,
					ErrorCode.REMOTE_EXCEPTION.getDescription());
		}
	}
}
