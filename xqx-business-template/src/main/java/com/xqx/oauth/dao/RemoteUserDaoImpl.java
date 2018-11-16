package com.xqx.oauth.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.dianping.cat.Cat;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.base.vo.ResponseMessage;

/**
 * 远程访问dao实现类
 */
@Component
public class RemoteUserDaoImpl implements IRemoteUserDao {

	private static final Logger logger = LoggerFactory.getLogger(RemoteUserDaoImpl.class);
	private static final Gson gson = new GsonBuilder().create();

	/** http协议 */
	private static final String PROTOCOL = "http://";
	/** 微服务名称 */
	private static final String USER_DATA_SERVER_NAME = "XQX-DATA-TEMPLATE";

	@Autowired
	private RestTemplate restTemplate;

	// unless = "#result==null":返回结果为null则不缓存，sync=true 与 unless 不兼容)
	// @HystrixCommand 服务降级
	@SuppressWarnings("serial")
	@Override
	@HystrixCommand(fallbackMethod = "listAllUserFallback")
	@Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator", unless = "#result==null")
	public List<UserDTO> listAllUser() throws CallRemoteServiceException {
		// 调用登陆接口
		String url = PROTOCOL + USER_DATA_SERVER_NAME + "/listAllUser";
		ResponseMessage<?> body = getRemoteServiceResult(url, null, ResponseMessage.class);

		logger.info("执行查询用户结果 == " + body);
		checkResponse(body);
		List<UserDTO> users = gson.fromJson(body.getData().toString(), new TypeToken<List<UserDTO>>() {}.getType());

		return users;
	}

	@Override
	@HystrixCommand(fallbackMethod = "addBlackListFallback")
	public boolean addBlackList(Long userID) throws CallRemoteServiceException {
		// 调用登陆接口
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("id", userID);

		String url = PROTOCOL + USER_DATA_SERVER_NAME + "/doForbiddenByUserId";
		String body = getRemoteServiceResult(url, paramMap, String.class);

		logger.info("执行冻结用户结果 == " + body);
		ResponseMessage<?> responseMessage = gson.fromJson(body, ResponseMessage.class);
		checkResponse(responseMessage);

		return (Boolean) responseMessage.getData();

	}

	@Override
	@HystrixCommand(fallbackMethod = "doUnforbiddenByUserIdFallback")
	public boolean doUnforbiddenByUserId(Long userID) throws CallRemoteServiceException {
		// 调用登陆接口
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		paramMap.add("id", userID);

		String url = PROTOCOL + USER_DATA_SERVER_NAME + "/doUnforbiddenByUserId";
		String body = getRemoteServiceResult(url, paramMap, String.class);

		logger.info("执行解冻用户结果 == " + body);
		ResponseMessage<?> responseMessage = gson.fromJson(body, ResponseMessage.class);
		checkResponse(responseMessage);

		return (Boolean) responseMessage.getData();
	}

	protected boolean addBlackListFallback(String accessToken, Throwable throwable) {
		Cat.logError(throwable);
		return false;
	}

	protected boolean doUnforbiddenByUserIdFallback(Long userId, Throwable throwable) {
		Cat.logError(throwable);
		return false;
	}
	protected List<UserDTO> listAllUserFallback(Throwable throwable) {
		Cat.logError(throwable);
		return null;
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

	@SuppressWarnings("unused")
	// 另一种访问方式
	private ResponseMessage<UserDTO> getRemoteServiceResult(String url, HttpMethod method,
			MultiValueMap<String, Object> paramMap, Object... uriVariables) throws CallRemoteServiceException {
		try {
			return restTemplate.exchange(url, method, new HttpEntity<>(paramMap),
					new ParameterizedTypeReference<ResponseMessage<UserDTO>>() {
					}, uriVariables).getBody();
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
