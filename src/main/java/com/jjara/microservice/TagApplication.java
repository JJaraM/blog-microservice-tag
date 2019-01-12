/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
}
