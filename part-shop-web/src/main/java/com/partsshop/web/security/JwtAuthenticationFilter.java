package com.partsshop.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired 
	private JwtTokenProvider tokenProvider ; 
	@Autowired 
	private CustomUserDetailService customUserDetailsService ; 
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain)
			throws ServletException, IOException {
		System.out.println("I am Here in THE FILTET ========= OUT");
		//System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
		try {
			
			if(SecurityContextHolder.getContext() == null || 
					SecurityContextHolder.getContext().getAuthentication() == null || 
					!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				System.out.println("I am Here in THE FILTET ========= IN");
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
			}
			System.out.println("I am Here in THE FILTET ========= OUT");
//			  String jwt = this.getJwtFromRequest(httpServletRequest) ;
//			  if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//				  String id = tokenProvider.getUserIdFromJWT(jwt) ; 
//				  UserDetails userDetails = customUserDetailsService.loadUserById(id) ; 
//				  UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) ; 
//				  authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//				  SecurityContextHolder.getContext().setAuthentication(authentication);
//			  }
			  
		}catch(Exception exp) {
			logger.error("Could not set user authentication in security context", exp);
		}
		
		chain.doFilter(httpServletRequest, httpServletResponse);
		

	}
	
//	private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }

}
