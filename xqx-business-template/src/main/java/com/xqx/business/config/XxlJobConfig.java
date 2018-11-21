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
	@Value("${xxl.job.admin.xxlJobDesc}")
	private String xxlJobDesc;

	public String getAdminAddresses() {
		return adminAddresses;
	}

	public void setAdminAddresses(String adminAddresses) {
		this.adminAddresses = adminAddresses;
	}

	public String getXxlJobDesc() {
		return xxlJobDesc;
	}

	public void setXxlJobDesc(String xxlJobDesc) {
		this.xxlJobDesc = xxlJobDesc;
	}
	
	
}