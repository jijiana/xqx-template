package com.xqx.business.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xqx.base.vo.ResponseMessage;
import com.xqx.business.dao.IUserDataFeignClient;

public class UserDataFeignClientFallBack implements IUserDataFeignClient {
	private static final Logger logger = LoggerFactory.getLogger(UserDataFeignClientFallbackFactory.class);

	@Override
	public ResponseMessage<?> findUserByNameAndPassword(String name, String password) {
		logger.info("服务降级,name={}", name);
		return ResponseMessage.fallBackFail();
	}

	@Override
	public ResponseMessage<?> insertNameAndPassword(String name, String password) {
		logger.info("服务降级,name={}", name);
		return ResponseMessage.fallBackFail();
	}
}
