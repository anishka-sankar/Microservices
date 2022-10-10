package com.microservices.ConfigurationProperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties("db") // can access members of db which has same member names
public class DbDetails {
	
	
	private String userName;
	private String host;
	private Integer port;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	@Override
	public String toString() {
		return "DbDetails [userName=" + userName + ", host=" + host + ", port=" + port + "]";
	}
}
