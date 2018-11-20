package com.xqx.user.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.user.data.service.IRegisterService;

/**
 *  用户注册
 */
@RestController
public class RegisterController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IRegisterService registerService;
	
	@PostMapping(value = "/insertNameAndPassword")
	public ResponseMessage<Boolean> insertNameAndPassword(@RequestParam("name") String name,@RequestParam("password") String password){
		try {
			logger.info("注册{}",name);
			registerService.saveNameAndPassword(name, password);
			return ResponseMessage.success(true);
		} catch (ServiceException e) {
			logger.error("注册{}失败",name);
			return ResponseMessage.fail(e.getErrorCode().getCode(), e.getErrMsg());
		}
	}
}