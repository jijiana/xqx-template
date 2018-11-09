package com.xqx.oauth.dao;

import com.xqx.base.exception.CallRemoteServiceException;

/**
 * 远程访问dao接口
 */
public interface IRemoteUserDao {
	/**
	 * 远程将用户加入黑名单
	 * 
	 * @param userID 用户唯一标识
	 * @return 是否加入成功
	 * @throws CallRemoteServiceException
	 */
	public boolean addBlackList(Long userID) throws CallRemoteServiceException;

	/**
	 * 远程将用户移除黑名单
	 * 
	 * @param userID 用户唯一标识
	 * @return 是否移除成功
	 * @throws CallRemoteServiceException
	 */
	public boolean doUnforbiddenByUserId(Long userID) throws CallRemoteServiceException;

}
