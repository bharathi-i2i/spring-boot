package com.pet.dog.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pet.dog.filter.Filter1;

@Configuration
public class MyFilterConfig {
	
	@Bean
	public FilterRegistrationBean<Filter1> registrationBean() {
		FilterRegistrationBean<Filter1> registrationBean = new FilterRegistrationBean<>();
		
		registrationBean.setFilter(new Filter1());
		registrationBean.addUrlPatterns("/*");
		
		return registrationBean;
	}

}
