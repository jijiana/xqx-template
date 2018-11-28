package com.xqx.business.dao;

import com.xqx.base.vo.ResponseMessage;

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
