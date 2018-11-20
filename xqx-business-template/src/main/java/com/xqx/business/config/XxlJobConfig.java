package com.xqx.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 * 
 */
@Configuration
public class XxlJobConfig {

	@Value("${xxl.job.admin.addresses}")
	private String adminAddresses;
	@Value("${xxl.job.admin.xxlJobDecName}")
	private String xxlJobDecName;

	public String getAdminAddresses() {
		return adminAddresses;
	}

	public void setAdminAddresses(String adminAddresses) {
		this.adminAddresses = adminAddresses;
	}

	public String getXxlJobDecName() {
		return xxlJobDecName;
	}

	public void setXxlJobDecName(String xxlJobDecName) {
		this.xxlJobDecName = xxlJobDecName;
	}

}