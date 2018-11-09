package com.xqx.user.data.service;

import java.util.List;

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
	 * 保存用户信息
	 * 
	 * @param user 用户实体
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：user对象不能为空错误
	 */
	UserDTO saveUser(UserDTO user) throws ServiceException;

	/**
	 * 根据ID删除用户信息
	 * 
	 * @param id 用户唯一标识
	 * @throws ServiceException 业务异常，包含：参数ID不能为空错误
	 */
	void removeUserById(Long id) throws ServiceException;

	/**
	 * 根据ID获取用户对象
	 * 
	 * @param id 用户唯一标识
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：参数ID不能为空错误
	 */
	UserDTO getUserByID(Long id) throws ServiceException;

	/**
	 * 根据用户名密码查询用户
	 * 
	 * @param name     用户名
	 * @param password 密码
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：参数不能为空错误，未找到用户错误
	 */
	UserDTO getUserByNameAndPassword(String name, String password) throws ServiceException;

	/**
	 * 获取所有用户
	 * 
	 * @return 用户传输实体列表
	 */
	List<UserDTO> listAllUser();

	/**
	 * 统计用户数量 示例
	 * 
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	Long countUserByName(String name) throws ServiceException;

	/**
	 * 冻结用户
	 * 
	 * @param id 用户唯一标识
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：未找到该id错误，设置黑名单失败错误
	 */
	UserDTO doForbiddenById(Long id) throws ServiceException;

	/**
	 * 解冻用户
	 * 
	 * @param id 用户唯一标识
	 * @return 用户传输实体
	 * @throws ServiceException 业务异常，包含：未找到该id错误，设置黑名单失败错误
	 */
	UserDTO doUnforbiddenById(Long id) throws ServiceException;
}
