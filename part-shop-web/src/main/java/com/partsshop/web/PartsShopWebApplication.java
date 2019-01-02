package com.partsshop.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.partsshop.web.exception.RestErrorHandler;

@SpringBootApplication
@ComponentScan(basePackages = {"com.partsshop.web"}) 
public class PartsShopWebApplication implements CommandLineRunner {
	static Logger LOG = LoggerFactory.getLogger(PartsShopWebApplication.class) ; 
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	public static void main(String[] args) {
		LOG.info("Application Server Start");
		SpringApplication.run(PartsShopWebApplication.class, args);
	}
	
	@Bean
    public RestTemplate getRestClient() {
		RestTemplate restTemplate = restTemplateBuilder.errorHandler(new RestErrorHandler())
		          .build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		
		return restTemplate ; 
    }
	
	
	@Override
	public void run(String... args) throws Exception {
		/*UserSignInRest userSignUp = new UserSignInRest() ; 
		userSignUp.setUserEmail("alaa.abuzaghleh@gmail.com");
		userSignUp.setPassword("1234567");
		
		MultiValueMap<String, String> header = new HttpHeaders() ; 
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept-Language", "ar");
		HttpEntity<?> requestEntity = new HttpEntity<>(userSignUp, header); 
		System.out.println("Here I am 00001");
		ResponseEntity<String> res = null ; 
		 res =  this.getRestClient().exchange("http://localhost:8080/api/v1/auth/signin", HttpMethod.POST, requestEntity, String.class);
		
		System.out.println(res.getStatusCode());
		ObjectMapper mapper = new ObjectMapper() ; 
		if(res.getStatusCode() == HttpStatus.OK) {
			
			JwtAuthenticationResponse token = mapper.readValue(res.getBody(), JwtAuthenticationResponse.class) ; 
			System.out.println(token.getAccessToken());
		}else {
			RestMessage msg = mapper.readValue(res.getBody(), RestMessage.class) ; 
			System.out.println("I am Here 88888\n" + msg.getMessages());
		}*/
		
		
		
		
	}
	
	
	
}
