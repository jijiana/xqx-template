package com.xqx.user.data.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.ErrorCode;
import com.xqx.base.exception.ServiceException;
import com.xqx.user.data.dao.IRegisterRepository;
import com.xqx.user.data.pojo.entity.UserDO;

@Service
public class RegisterServiceImpl implements IRegisterService {
	private static Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

	@Autowired
	public IRegisterRepository registerRepository;

	@Override
	public void saveNameAndPassword(String name, String password) throws ServiceException {
		UserDO userDo = new UserDO();
		userDo.setName(name);
		userDo.setPassword(password);
		/**
		 * 根据name查询用户是否存在
		 */
		List<UserDO> users = registerRepository.findUserByName(name);
		System.out.println(users);
		// 如果用户已经注册，则不允许重复添加
		if (users.size() > 0) {
			logger.info("注册失败, {}", ErrorCode.TOKEN_REGISTERED.getDescription());
			throw new ServiceException(ErrorCode.TOKEN_REGISTERED);
		}
		registerRepository.save(userDo);
	}

}
