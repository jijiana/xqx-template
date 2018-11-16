package com.xqx.oauth.servic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.CallRemoteServiceException;
import com.xqx.base.exception.ErrorCode;
import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.oauth.dao.IRemoteUserDao;

@Service
public class BlackListervicImpl implements IBlackListService {

	private static final Logger logger = LoggerFactory.getLogger(BlackListervicImpl.class);

	@Autowired
	private IRemoteUserDao remoteUserDao;

	@Override
	public List<UserDTO> listAllUser() throws ServiceException {
		try {
			return remoteUserDao.listAllUser();
		} catch (CallRemoteServiceException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addBlackList(Long userId) throws ServiceException {
		try {
			BLACK_LIST.add(userId);
			remoteUserDao.addBlackList(userId);
		} catch (CallRemoteServiceException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			logger.error("添加黑名单失败", e);
			throw new ServiceException(e, ErrorCode.UNKNOWN_ERROR, "添加黑名单失败");
		}
	}

	@Override
	public void removeBlackList(Long userId) throws ServiceException {
		try {
			BLACK_LIST.remove(userId);
			remoteUserDao.doUnforbiddenByUserId(userId);
		} catch (CallRemoteServiceException e) {
			throw new ServiceException(e);
		} catch (Exception e) {
			logger.error("删除黑名单失败", e);
			throw new ServiceException(e, ErrorCode.UNKNOWN_ERROR, "删除黑名单失败");
		}
	}

}
