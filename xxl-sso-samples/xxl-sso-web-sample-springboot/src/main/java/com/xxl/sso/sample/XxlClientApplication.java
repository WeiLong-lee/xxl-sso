package com.xxl.sso.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author xuxueli 2018-03-22 23:41:47
 */
@SpringBootApplication
public class XxlClientApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
