package com.xqx.oauth.app;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * 此注解中包含了
 * 
 * @SpringCloudApplication 表明程序启动入口，包含如下
 *                         <ul>
 *                         <li>@SpringBootApplication 表明该类为程序启动类</li>
 *                         <li>@EnableDiscoveryClient 表明开启服务发现，Eureka</li>
 *                         <li>@EnableCircuitBreaker 表明开启Hystrix</li>
 *                         </ul>
 * @EnableApolloConfig 表明程序应用apollo提供配置
 * @EnableCaching 表明开启缓存，需要显示的指定
 * @ComponentScan 配置spring扫描包的路径
 */
@SpringCloudApplication
@EnableApolloConfig
@EnableCaching
@ComponentScan(basePackages = { "com.xqx.oauth", "com.xqx.base" })
public class XqxOauthApplication {

	/**
	 * 程序启动入口
	 */
	public static void main(String[] args) {
		SpringApplication.run(XqxOauthApplication.class, args);
	}

	/**
	 * 远程访问模板注册
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		// 获取RestTemplate默认配置好的所有转换器
		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
		// 默认的MappingJackson2HttpMessageConverter在第7个 先把它移除掉
		messageConverters.remove(6);
		// 添加上GSON的转换器
		messageConverters.add(6, new GsonHttpMessageConverter());

		return restTemplate;
	}

	/**
	 * hystrix注册
	 */
	@Bean
	public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
		HystrixMetricsStreamServlet hystrixServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<HystrixMetricsStreamServlet>(
				hystrixServlet, "/hystrix.stream");
		registrationBean.setLoadOnStartup(1);
		registrationBean.setName(HystrixMetricsStreamServlet.class.getName());
		return registrationBean;
	}

}
