package com.xqx.user.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.ServiceException;
import com.xqx.user.data.dao.IRegisterRepository;
import com.xqx.user.data.pojo.entity.UserDO;

@Service
public class RegisterServiceImpl implements IRegisterService {
	
	@Autowired
	public IRegisterRepository registerRepository;

	@Override
	public void saveNameAndPassword(String name, String password) throws ServiceException {
		//registerRepository.insertNameAndPassword(name, password);
		UserDO userDo = new UserDO();
		userDo.setName(name);
		userDo.setPassword(password);
		//TODO 查询name 
		registerRepository.save(userDo);
	}

}
