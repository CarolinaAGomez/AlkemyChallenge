package com.AlkemyCB.SpringJavaJwt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SengridConfig {
	
//TOMA EL VALOR QUE SE ENCUENTRA EN APLICATION PROPERTY.
	@Value("$(spring.sendgrid.api-key)")
	private String key;
	
	@Bean
	public SendGrid getSengrid() {
		return new SendGrid(key);
	}
		

}
