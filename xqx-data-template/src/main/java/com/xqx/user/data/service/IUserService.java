package com.xqx.user.data.service;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;

/**
 * 逻辑层接口
 * 
 * @author teng
 *
 */
public interface IUserService {

	/**
	 * 根据用户名密码查询用户
	 * 
	 * @param name     用户名
	 * @param password 密码
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：参数不能为空错误，未找到用户错误
	 */
	UserDTO getUserByNameAndPassword(String name, String password) throws ServiceException;

}
