package com.xqx.user.data.service;

import com.xqx.base.exception.ServiceException;

public interface IRegisterService {
	/**
	 * 注册用户接口
	 * @param name
	 * @param password
	 * @throws ServiceException
	 */
	public void saveNameAndPassword(String name,String password) throws ServiceException;
}
