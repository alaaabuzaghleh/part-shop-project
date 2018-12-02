package com.partsshop.rest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Autowired 
	private MessageSource messageSource ; 
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class) ; 

	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exp)
			throws IOException, ServletException {
		logger.error("Responding with unauthorized error. Message - {} ", exp.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
	                messageSource.getMessage("rest.badlogin", null, httpServletRequest.getLocale()));
		
	}

}
