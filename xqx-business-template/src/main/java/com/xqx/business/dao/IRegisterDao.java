package com.xqx.business.dao;

import com.xqx.base.exception.CallRemoteServiceException;

/**
 * 远程访问dao接口
 */
public interface IRegisterDao {
	/**
	 * 远程保存用户名和密码
	 * @param name
	 * @param password
	 * @return
	 * @throws CallRemoteServiceException
	 */
	public boolean insertNameAndPassword(String name,String password) throws CallRemoteServiceException;
}
