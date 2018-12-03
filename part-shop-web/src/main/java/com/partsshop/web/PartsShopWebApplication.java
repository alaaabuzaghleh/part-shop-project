package com.partsshop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.partsshop.web"}) 
public class PartsShopWebApplication {
    //test
	static Logger LOG = LoggerFactory.getLogger(PartsShopWebApplication.class) ; 
	public static void main(String[] args) {
		LOG.info("Application Server Start");
		SpringApplication.run(PartsShopWebApplication.class, args);
	}
}
