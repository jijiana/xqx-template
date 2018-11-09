package com.xqx.user.data.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xqx.base.exception.ErrorCode;
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
	 */
	@PostMapping(value = "/findUserByNameAndPassword")
	public ResponseMessage<UserDTO> findUserByNameAndPassword(@RequestParam("name") String name,
			@RequestParam("password") String password) {
		try {
			UserDTO user = userService.getUserByNameAndPassword(name, password);

			logger.info(user.toString());
			return ResponseMessage.success(user);
		} catch (ServiceException e) {
			return ResponseMessage.fail(e.getErrorCode().getCode(), e.getErrMsg());
		}
	}

	/**
	 * 根据用户ID加入黑名单
	 * 
	 * @param id 用户唯一标识
	 * @return 包含是否加入黑名单成功信息的实体
	 */
	@PostMapping(value = "/doForbiddenByUserId")
	public ResponseMessage<Boolean> doForbiddenByUserId(@RequestParam("id") Long id) {
		try {
			UserDTO user = userService.doForbiddenById(id);
			if (user != null) {
				return ResponseMessage.success(true);
			} else {
				return ResponseMessage.fail(ErrorCode.SERVICE_ERROR.getCode(), "用户ID" + id + "设置黑名单失败");
			}
		} catch (ServiceException e) {
			return ResponseMessage.fail(e.getErrorCode().getCode(), e.getErrMsg());
		}
	}

	/**
	 * 根据用户ID移除黑名单
	 * 
	 * @param id 用户唯一标识
	 * @return 包含是否移除黑名单成功信息的实体
	 */
	@PostMapping(value = "/doUnforbiddenByUserId")
	public ResponseMessage<Boolean> doUnforbiddenByUserId(@RequestParam("id") Long id) {
		try {
			UserDTO user = userService.doUnforbiddenById(id);
			if (user != null) {
				return ResponseMessage.success(true);
			} else {
				return ResponseMessage.fail(ErrorCode.SERVICE_ERROR.getCode(), "用户ID" + id + "设置黑名单失败");
			}
		} catch (ServiceException e) {
			return ResponseMessage.fail(e.getErrorCode().getCode(), e.getErrMsg());
		}
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return 包含用户传输实体信息的实体
	 */
	@PostMapping(value = "/listAllUser")
	public ResponseMessage<List<UserDTO>> listAllUser() {
		List<UserDTO> userList = userService.listAllUser();
		logger.info(userList.toString());
		return ResponseMessage.success(userList);
	}

}
