package com.xqx.oauth.dao;

import java.util.List;

import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.pojo.dto.UserDTO;

/**
 * 远程访问dao接口
 */
public interface IRemoteUserDao {

	/**
	 * 获取所有用户信息
	 * 
	 * @return 用户传输实体
	 * @throws CallRemoteServiceException 业务异常，包括：访问远程服务失败错误
	 */
	List<UserDTO> listAllUser() throws CallRemoteServiceException;

	/**
	 * 远程将用户加入黑名单
	 * 
	 * @param userID 用户唯一标识
	 * @return 是否加入成功
	 * @throws CallRemoteServiceException
	 */
	boolean addBlackList(Long userID) throws CallRemoteServiceException;

	/**
	 * 远程将用户移除黑名单
	 * 
	 * @param userID 用户唯一标识
	 * @return 是否移除成功
	 * @throws CallRemoteServiceException
	 */
	boolean doUnforbiddenByUserId(Long userID) throws CallRemoteServiceException;

}
