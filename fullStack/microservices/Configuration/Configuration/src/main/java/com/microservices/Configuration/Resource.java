package com.microservices.Configuration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.ConfigurationProperties.DbDetails;

@RestController
@RefreshScope
public class Resource {
	
	@Value("${my.msg}")
	private String msg;
	

	@Value("${my.greeting : default value}") //picks default value if value is not resolved
	private String greeting;
	
	@Value("static value")
	private String value;
	
	@Value("${my.list.values}") 
	private List<String> list;
	
	@Value("#{${dbValues}}") //after # it evaluates as Spring Expression language(SPEL)
	private Map<String,String> keyValue;
	
	@Autowired
	private DbDetails details;
	
	@GetMapping("/hello")
	public String getMsg() {
		return greeting + " " + list + " "+ keyValue;
	}
	
	@GetMapping("/db")
	public String getDetails() {
		return details.getHost() + details.getUserName() + details.getPort();
	}

}
