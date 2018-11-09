package com.xqx.base.cat;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dianping.cat.servlet.CatFilter;

/**
 * 点评CAT启用配置
 */
@Configuration
public class CatFilterConfigure {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean catFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		CatFilter filter = new CatFilter();
		registration.setFilter(filter);
		registration.addUrlPatterns("/*");
		registration.setName("cat-filter");
		registration.setOrder(1);
		return registration;
	}
}
