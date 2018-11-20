package com.xqx.business.service;

import com.xqx.base.exception.ServiceException;

/**
 * 
 *注册接口
 */
public interface IRegisterService {
	/**
	 * 注册保存用户名和密码
	 * @param name
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	public boolean saveNameAndPassword(String name,String password) throws ServiceException;
}
