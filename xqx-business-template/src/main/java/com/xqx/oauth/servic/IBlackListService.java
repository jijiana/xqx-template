package com.xqx.oauth.servic;

import java.util.HashSet;
import java.util.Set;

import com.xqx.base.exception.ServiceException;

/**
 * token服务接口
 */
public interface IBlackListService {
	/** 黑名单，设置初始容量 */
	static final Set<Long> BLACK_LIST = new HashSet<>(16000);

	/**
	 * 添加用户到黑名单
	 * 
	 * @param userId 用户唯一标识
	 * @throws ServiceException 业务异常，包括：访问远程服务失败错误，未知异常错误
	 */
	void addBlackList(Long userId) throws ServiceException;

	/**
	 * 将用户从黑名单移除
	 * 
	 * @param userId 用户唯一标识
	 * @throws ServiceException业务异常，包括：访问远程服务失败错误，未知异常错误
	 */
	void removeBlackList(Long userId) throws ServiceException;
}
