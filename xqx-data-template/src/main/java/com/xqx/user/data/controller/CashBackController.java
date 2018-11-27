package com.xqx.user.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xqx.base.exception.ServiceException;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.user.data.service.ICashBackService;

@RestController
public class CashBackController {

	private static Logger logger = LoggerFactory.getLogger(CashBackController.class);

	@Autowired
	private ICashBackService cashBackService;

	/**
	 * 返金接口
	 * 
	 * @param param 返金金额
	 * @return
	 * @throws ServiceException
	 * @throws NumberFormatException
	 */
	@PostMapping(value = "/doCashBackForUser")
	public ResponseMessage<Boolean> doCashBackForUser(@RequestParam(value = "param") String param)
			throws NumberFormatException, ServiceException {
		logger.info("返金");
		cashBackService.doCashBackForUser(Long.valueOf(param));
		return ResponseMessage.success(true);
	}
}
