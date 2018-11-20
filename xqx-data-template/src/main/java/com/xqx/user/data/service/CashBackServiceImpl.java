package com.xqx.user.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.ServiceException;
import com.xqx.user.data.dao.ICashBackRepository;

@Service
public class CashBackServiceImpl implements ICashBackService {

	@Autowired ICashBackRepository cashBackRepository;
	@Override
	public void doCashBackForUser(Long number) throws ServiceException {
		cashBackRepository.updateCashBackInfo(number);
	}

}
