package com.xqx.user.data.service;

import com.xqx.base.exception.ServiceException;

public interface ICashBackService {
	public void doCashBackForUser(Long number)throws ServiceException ;
}
