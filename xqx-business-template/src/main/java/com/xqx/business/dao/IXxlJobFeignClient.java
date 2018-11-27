package com.xqx.business.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dianping.cat.Cat;
import com.xqx.business.dao.IXxlJobFeignClient.FeignClientFallbackFactory;

import feign.hystrix.FallbackFactory;

/**
 * 远程调用xxl-job，各个模块通用。
 */
@FeignClient(name = "xxl-job-admin", fallbackFactory = FeignClientFallbackFactory.class)
public interface IXxlJobFeignClient {

	/**
	 * 根据job描述，执行指定参数任务， 返回包含200则表示成功
	 */
	@PostMapping("/jobinfo/trigger/desc")
	String triggerJob(@RequestParam("jobDesc") String jobDesc, @RequestParam("executorParam") String executorParam);

	/**
	 * 根据job id，执行指定参数任务， 返回包含200则表示成功
	 */
	@PostMapping("/jobinfo/trigger")
	String triggerJob(@RequestParam("id") int id, @RequestParam("executorParam") String executorParam);

	@Component
	public static class FeignClientFallbackFactory implements FallbackFactory<IXxlJobFeignClient> {
		private static final Logger logger = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

		@Override
		public IXxlJobFeignClient create(Throwable cause) {
			// 埋点
			Cat.logError("服务降级", cause);
			logger.error("访问失败，服务降级", cause);
			return new IXxlJobFeignClient() {
				@Override
				public String triggerJob(String jobDesc, String executorParam) {
					return "500";
				}
				
				@Override
				public String triggerJob(int id, String executorParam) {
					return "500";
				}
			};
		}
	}
}
