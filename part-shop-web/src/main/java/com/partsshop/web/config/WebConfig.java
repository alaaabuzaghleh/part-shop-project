package com.partsshop.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;

@EnableWebMvc
@Configuration
public class WebConfig  implements WebMvcConfigurer {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("I am in the Config");
        //specifying static resource location for themes related files(css etc)
		registry
        .addResourceHandler("/assets/**") 
        .addResourceLocations("classpath:/static/assets/") 
        .setCachePeriod( 3600 )
        .resourceChain(true) // 4.1
        .addResolver(new GzipResourceResolver()) // 4.1
        .addResolver(new PathResourceResolver()); //4.1    
		}

}
