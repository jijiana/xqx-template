package com.xqx.user.data.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xqx.base.exception.ErrorCode;
import com.xqx.base.exception.ServiceException;
import com.xqx.base.pojo.dto.UserDTO;
import com.xqx.user.data.dao.IUserRepository;
import com.xqx.user.data.pojo.entity.UserDO;
import com.xqx.user.data.util.POJOConverterUtils;

/**
 * @CacheConfig 缓存设置。cacheNames：当前默认缓存名称；keyGenerator：默认的key生成策略
 */
@Service
@CacheConfig(cacheNames = "user", keyGenerator = "wiselyKeyGenerator")
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserRepository userRepostory;

	/**
	 * Cacheable(value="cacheName", key ="#id",sync = true, unless = "#user==null")
	 * 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
	 * <ul>
	 * <li>value：缓存key的前缀。</li>
	 * <li>key：缓存key的后缀。</li>
	 * <li>sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。为true时与unless不兼容</li>
	 * <li>unless 表示条件表达式成立的话不放入缓存。用于方法执行后校验</li>
	 * </ul>
	 */

	@Override
	@Cacheable(sync = true)  // @CachePut 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存
	public UserDTO getUserByNameAndPassword(String name, String password) throws ServiceException {

		if (StringUtils.isBlank(name)) {
			logger.info("参数Name不能为Null");
			throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT, "参数Name不能为Null");
		}
		if (StringUtils.isBlank(password)) {
			logger.info("参数Password不能为Null");
			throw new ServiceException(ErrorCode.ILLEGAL_ARGUMENT, "参数Password不能为Null");
		}
		UserDO userDO = userRepostory.findByNameAndPassword(name, password);
		if (userDO == null) {
			logger.info("未找到用户");
			throw new ServiceException(ErrorCode.DAO_NOTFOUND, "未找到用户");
		}
		return POJOConverterUtils.toUserDTO(userDO);
	}

}
