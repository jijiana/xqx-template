package com.xqx.oauth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.oauth.servic.IBlackListService;

/**
 * 黑名单管理
 */
@RestController
public class BlackListController {
	private static final Logger logger = LoggerFactory.getLogger(BlackListController.class);

	@Autowired
	private IBlackListService blackListService;

	/**
	 * 示例 <br/>
	 * 获取所有用户信息
	 * 
	 * @return 包含用户传输实体信息的实体
	 * @throws ServiceException 业务异常，包括：访问远程服务失败错误
	 */
	@PostMapping(value = "/listAllUser")
	public ResponseMessage<List<UserDTO>> listAllUser() throws ServiceException {
		List<UserDTO> userList = blackListService.listAllUser();
		logger.info(userList.toString());
		return ResponseMessage.success(userList);
	}

	/**
	 * 加入黑名单
	 * 
	 * @param userId 用户唯一标识
	 * @return 加入黑名单是否成功的实体
	 * @throws ServiceException 业务异常，包括：访问远程服务失败错误，未知异常错误
	 */
	@PostMapping(value = "/addBlackList")
	public ResponseMessage<Boolean> addBlackList(@RequestParam("userId") Long userId) throws ServiceException {
		logger.info("添加黑名单：{}", userId);

		blackListService.addBlackList(userId);

		return ResponseMessage.success(true);
	}

	/**
	 * 从黑名单删除用户唯一标识
	 * 
	 * @param userId 用户唯一标识
	 * @return 删除黑名单是否成功的实体
	 * @throws ServiceException业务异常，包括：访问远程服务失败错误，未知异常错误
	 */
	@PostMapping(value = "/removeBlackList")
	public ResponseMessage<Boolean> removeBlackList(@RequestParam("userId") Long userId) throws ServiceException {
		logger.info("删除黑名单：{}", userId);

		blackListService.removeBlackList(userId);

		return ResponseMessage.success(true);
	}

}
