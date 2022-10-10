package com.microservices.Configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.microservices.ConfigurationProperties","com.microservices.Configuration"})
public class ConfigurationApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ConfigurationApplication.class, args);
	}

}
