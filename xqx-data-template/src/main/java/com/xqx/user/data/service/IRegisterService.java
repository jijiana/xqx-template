package com.xqx.user.data.service;

import com.xqx.base.exception.ServiceException;

public interface IRegisterService {
	/**
	 * 
	 * @param name
	 * @param password
	 * @throws ServiceException
	 */
	public void saveNameAndPassword(String name,String password) throws ServiceException;
}
