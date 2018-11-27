package com.xqx.business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.business.service.IRegisterService;

/**
 * 注册管理
 */

@RestController
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

	@Autowired
	private IRegisterService registerService;

	/**
	 * 注册
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@PostMapping(value = "/register")
	public ResponseMessage<Boolean> doRegister(@RequestParam("name") String name,
			@RequestParam("password") String password) throws ServiceException {
		logger.info("接收：name={}, password={}", name, Base64Utils.encodeToString(password.getBytes()));

		registerService.saveNameAndPassword(name, password);

		logger.info("注册{}成功", name);
		return ResponseMessage.success(true);
	}

	@PostMapping(value = "/findUser")
	public ResponseMessage<UserDTO> findUser(@RequestParam("name") String name,
			@RequestParam("password") String password) throws ServiceException {
		logger.info("接收：name={}, password={}", name, Base64Utils.encodeToString(password.getBytes()));

		UserDTO user = registerService.findUserByNameAndPassword(name, password);

		logger.info("查询{}成功", name);
		return ResponseMessage.success(user);
	}
}
