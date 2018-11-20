package com.xqx.business.jobhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.vo.ResponseMessage;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
/**
 * 
 * xxl-job调用微服务
 */
@JobHandler(value="cashBackJobHandler")
@Component
public class CashBackJobHandler extends IJobHandler {

	private static final Logger logger = LoggerFactory.getLogger(CashBackJobHandler.class);
	private static final Gson gson = new GsonBuilder().create();
	
	/** http协议 */
	private static final String PROTOCOL = "http://";
	/** 微服务名称 */
	private static final String USER_DATA_SERVER_NAME = "XQX-DATA-TEMPLATE";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		// 调用登陆接口
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		
		paramMap.add("param", param);
		String url = PROTOCOL + USER_DATA_SERVER_NAME + "/doCashBackForUser";
		String body = getRemoteServiceResult(url, paramMap, String.class);
		
		logger.info("执行冻结用户结果 == " + body);
		ResponseMessage<?> responseMessage = gson.fromJson(body, ResponseMessage.class);
		checkResponse(responseMessage);

		return SUCCESS;
	}

	/**
	 * 远程访问数据
	 * 
	 * @param url          地址
	 * @param request      请求参数
	 * @param responseType 类型
	 * @param uriVariables
	 * @return 类型对应的实体
	 * @throws CallRemoteServiceException 远程调用异常，微服务未启动
	 */
	private <T> T getRemoteServiceResult(String url, Object request, Class<T> responseType, Object... uriVariables)
			throws CallRemoteServiceException {
		try {
			return restTemplate.postForEntity(url, request, responseType).getBody();
		} catch (IllegalStateException e) {
			logger.error("远程访问失败：{}", e.getMessage());
			throw new CallRemoteServiceException(ErrorCode.REMOTE_EXCEPTION, "微服务" + USER_DATA_SERVER_NAME + "未启动");
		}
	}
	/**
	 * 检查返回结果
	 * 
	 * @param responseMessage 待检测的实体
	 * @throws CallRemoteServiceException 远程调用异常，返回码不为0错误
	 */
	private void checkResponse(ResponseMessage<?> responseMessage) throws CallRemoteServiceException {
		if (responseMessage.getStatus() != 0) {
			throw new CallRemoteServiceException(ErrorCode.getErrorCode(responseMessage.getStatus()),
					responseMessage.getMessage());
		}
	}
}
