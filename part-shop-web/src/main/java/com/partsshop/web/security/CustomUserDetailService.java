package com.partsshop.web.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.partsshop.web.dto.JwtAuthenticationResponse;
import com.partsshop.web.service.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired 
	private UserService userService ; 
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null ; 
	}
	
	public UserDetails loadUserByEmailAndPassword(String email, String password) {
		JwtAuthenticationResponse jwt = this.userService.getUserFromRest(email, password) ;
		List<String> ls = this.userService.getUserRoles(jwt.getAccessToken()) ; 
		
		return null ; 
	}
	
	public UserDetails loadUserById(String id){
		return null ; 
	}

}
