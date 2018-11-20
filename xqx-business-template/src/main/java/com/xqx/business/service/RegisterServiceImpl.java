package com.xqx.business.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ServiceException;
import com.xqx.business.dao.IRegisterDao;
import com.xqx.business.util.HttpClientUtils;

@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private IRegisterDao registerDao;
	
	@Override
	public boolean saveNameAndPassword(String name, String password) throws ServiceException{
		try {
			boolean insertNameAndPassword = registerDao.insertNameAndPassword(name, password);
			if(insertNameAndPassword) {
				//调用调度中心
				Map<String, String> params = new HashMap<String, String>();
				String address = "http://9.186.52.100:9060/jobinfo/trigger/desc";
				params.put("jobDesc", "测试任务1");
				HttpClientUtils client = HttpClientUtils.getInstance();
				String resp = client.sendHttpPost(address,params);
		    	System.out.println(resp);
			}
			return insertNameAndPassword;
			
		} catch (CallRemoteServiceException e) {
			throw new ServiceException(e);
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		}
		return false; 
	}
	
	

}
