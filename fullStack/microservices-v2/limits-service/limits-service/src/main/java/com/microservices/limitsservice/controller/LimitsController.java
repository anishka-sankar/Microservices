package com.microservices.limitsservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limitsservice.bean.Limit;
import com.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	Configuration config;
	
	@GetMapping("/limits")
	public Limit getLimits() {
		//return new Limit(1,10);
		return new Limit(config.getMinimum(),config.getMaximum());
	}
}
