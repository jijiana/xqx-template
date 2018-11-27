package com.xqx.user.data.controller;

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
import com.xqx.user.data.service.IUserService;

/**
 * 用户信息操作
 */
@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	/**
	 * 根据用户名密码查询用户
	 * 
	 * @param name     用户名
	 * @param password 密码
	 * @return 包含用户信息的实体
	 * @throws ServiceException
	 */
	@PostMapping(value = "/findUserByNameAndPassword")
	public ResponseMessage<UserDTO> findUserByNameAndPassword(@RequestParam("name") String name,
			@RequestParam("password") String password) throws ServiceException {
		logger.info("接受请求name={},password={}", name, Base64Utils.encodeToString(name.getBytes()));
		
		UserDTO user = userService.getUserByNameAndPassword(name, password);

		logger.info(user.toString());
		return ResponseMessage.success(user);
	}

}
