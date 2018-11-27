package com.xqx.business.service;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;

/**
 * 
 * 注册接口
 */
public interface IRegisterService {
	/**
	 * 注册保存用户名和密码
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	void saveNameAndPassword(String name, String password) throws ServiceException;

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	UserDTO findUserByNameAndPassword(String name, String password) throws ServiceException;
}
