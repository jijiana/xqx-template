package com.xqx.business.dao.impl;

import com.xqx.base.vo.ResponseMessage;
import com.xqx.business.dao.IUserDataFeignClient;

public class UserDataFeignClientFallBack implements IUserDataFeignClient {

	@Override
	public ResponseMessage<?> findUserByNameAndPassword(String name, String password) {
		return null;
	}

	@Override
	public ResponseMessage<?> insertNameAndPassword(String name, String password) {
		return null;
	}
}
