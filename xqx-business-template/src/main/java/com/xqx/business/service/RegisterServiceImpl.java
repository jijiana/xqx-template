package com.xqx.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.base.util.RemoteRespCheckUtils;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.business.config.XxlJobConfig;
import com.xqx.business.dao.IUserDataFeignClient;
import com.xqx.business.dao.IXxlJobFeignClient;

@Service
public class RegisterServiceImpl implements IRegisterService {
	private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	@Autowired
	private IUserDataFeignClient feignClient;
	@Autowired
	private IXxlJobFeignClient jobFeignClient;
	@Autowired
	private XxlJobConfig xxlJobConfig;

	@Override
	public UserDTO findUserByNameAndPassword(String name, String password) throws ServiceException {
		ResponseMessage<?> remoteResp = feignClient.findUserByNameAndPassword(name, password);
		try {
			RemoteRespCheckUtils.checkResponse(remoteResp);
		} catch (CallRemoteServiceException e) {
			logger.info("查询用户信息失败{}", e.getErrMsg());
			throw new ServiceException(e.getErrorCode(), "微服务访问失败");
		}
		return new Gson().fromJson(remoteResp.getData().toString(), UserDTO.class);
	}

	@Override
	public void saveNameAndPassword(String name, String password) throws ServiceException {
		try {

			System.out.println("start" + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
			ResponseMessage<?> remoteResp = feignClient.insertNameAndPassword(name, password);
			System.out.println("end  " + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));
			RemoteRespCheckUtils.checkResponse(remoteResp);
		} catch (CallRemoteServiceException e) {
			logger.info("注册失败{}", e.getErrMsg());
			throw new ServiceException(e.getErrorCode());
		}

		String triggerJobResp = "";
		try {
			triggerJobResp = jobFeignClient.triggerJob(xxlJobConfig.getXxlJobDesc(), "3");
			RemoteRespCheckUtils.checkJobResponse(triggerJobResp);
		} catch (CallRemoteServiceException e) {
			logger.error("调用xxl-job返回信息：{}", triggerJobResp);
			logger.error("调用远程job失败", e);
			throw new ServiceException(ErrorCode.XXL_JOB_FAIL);
		}
	}
}
