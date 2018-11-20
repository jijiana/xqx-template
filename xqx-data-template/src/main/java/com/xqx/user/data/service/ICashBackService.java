package com.xqx.user.data.service;

import com.xqx.base.exception.ServiceException;
/**
 * 
 *给用户返金接口
 */
public interface ICashBackService {
	public void doCashBackForUser(Long number)throws ServiceException ;
}
