/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * 
 * Handles all configuration need it to start the application.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TagApplication {
	
	/**
	 * Start endpoint to start the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TagApplication.class, args);
	}

	@Bean HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
