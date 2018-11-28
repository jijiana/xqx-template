package com.xqx.business.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dianping.cat.Cat;
import com.xqx.base.vo.ResponseMessage;
import com.xqx.business.dao.IUserDataFeignClient.UserDataFeignClientFallbackFactory;

import feign.hystrix.FallbackFactory;

/**
 * @FeignClient 注解为feign客户端 <br/>
 *              name：微服务实例名称，不区分大小写 <br/>
 *              fallbackFactory：熔断机制 <br/>
 *              fallback：熔断实现类
 *
 */
@FeignClient(name = "xqx-data-template", fallbackFactory = UserDataFeignClientFallbackFactory.class)
//@FeignClient(name = "xqx-data-template", fallback = UserDataFeignClientFallBack.class)
public interface IUserDataFeignClient {

	/**
	 * 注意：返回类型必须要有无参构造器 此类注解 @RequestParam 不能少
	 * 
	 * @PostMapping 需要访问的微服务的api
	 * @RequestParam 参数映射根据实际情况改变，也可以是 @PathVariable 等
	 * @Cacheable 给此方法添加本地缓存， <br/>
	 *            unless：为过滤逻辑， "#result==null":返回结果为null则不缓存，sync=true 与 unless不兼容
	 *            value：application.properties配置文件中的spring.cache.cache-names属性的值
	 *            keyGenerator：com.xqx.business.config.CacheConfig.wiselyKeyGenerator()方法名
	 */
	@PostMapping("/findUserByNameAndPassword")
	@Cacheable(value = "user", keyGenerator = "wiselyKeyGenerator", unless = "#result==null")
	ResponseMessage<?> findUserByNameAndPassword(@RequestParam("name") String name,
			@RequestParam("password") String password);

	@PostMapping("/insertNameAndPassword")
	ResponseMessage<?> insertNameAndPassword(@RequestParam("name") String name,
			@RequestParam("password") String password);

	@Component
	public static class UserDataFeignClientFallbackFactory implements FallbackFactory<IUserDataFeignClient> {
		private static final Logger logger = LoggerFactory.getLogger(UserDataFeignClientFallbackFactory.class);

		@Override
		public IUserDataFeignClient create(Throwable cause) {
			// 埋点
			Cat.logError("服务降级", cause);
			logger.info("访问失败，服务降级,{}", cause.getMessage());
			return new UserDataFeignClientFallBack();
		}
	}
}
