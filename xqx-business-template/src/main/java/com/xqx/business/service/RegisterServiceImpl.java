package com.xqx.business.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.exception.ServiceException;
import com.xqx.business.config.XxlJobConfig;
import com.xqx.business.dao.IRegisterDao;
import com.xqx.business.util.HttpClientUtils;

@Service
public class RegisterServiceImpl implements IRegisterService {
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	private IRegisterDao registerDao;
	
	@Autowired 
	private XxlJobConfig xxlJobConfig;
	@Override
	public void saveNameAndPassword(String name, String password) throws ServiceException{
		try {
			boolean insertNameAndPassword = registerDao.insertNameAndPassword(name, password);
			if(insertNameAndPassword) {
				/**
				 * 调用调度中心
				 */
				Map<String, String> params = new HashMap<String, String>();
				//触发调度中心任务管理
				String address = xxlJobConfig.getAdminAddresses()+"jobinfo/trigger/desc";
				//测试任务 代表的是任务描述
				params.put("jobDesc", xxlJobConfig.getXxlJobDecName());
				HttpClientUtils client = HttpClientUtils.getInstance();
				String resp = client.sendHttpPost(address,params);
				logger.info(resp);
			}else {
				throw new ServiceException(ErrorCode.TOKEN_REGISTER_FAIL);
			}
		} catch (CallRemoteServiceException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			throw new ServiceException(ErrorCode.HTTP_ERROR);
		}
	}
}
