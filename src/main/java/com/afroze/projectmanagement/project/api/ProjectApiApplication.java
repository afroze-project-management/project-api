package com.afroze.projectmanagement.project.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
public class ProjectApiApplication {

	private final Logger logger;

	public ProjectApiApplication() {
		this.logger = LoggerFactory.getLogger(ProjectApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApiApplication.class, args);
	}

	@Bean
	public EurekaInstanceConfigBean eurekaInstanceConfig(InetUtils inetUtils) {
		EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.info(e.getLocalizedMessage());
		}

		config.setIpAddress(ip);
		config.setPreferIpAddress(true);

		return config;
	}
}
