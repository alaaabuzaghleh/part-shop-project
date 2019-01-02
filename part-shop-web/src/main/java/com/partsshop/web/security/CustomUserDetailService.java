package com.partsshop.web.security;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.partsshop.web.dto.JwtAuthenticationResponse;
import com.partsshop.web.dto.Mapper;
import com.partsshop.web.dto.RestMessage;
import com.partsshop.web.service.UserService;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired 
	private UserService userService  ; 
	@Autowired
	private JwtTokenProvider tokenProvider  ; 
	@Autowired 
	private Mapper mapper ; 
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null ; 
	}
	
	@SuppressWarnings("unchecked")
	public UserDetails loadUserByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
		System.out.println("Here 1111");
		ResponseEntity<String> jwt = this.userService.getUserFromRest(email, password) ;
		System.out.println("Here 1111");
		if(jwt.getStatusCode()==HttpStatus.OK) {
			JwtAuthenticationResponse res = mapper.mapObject(jwt.getBody(), JwtAuthenticationResponse.class) ; 
			// now we have to get the user token and send it to the rest to get the user role. 
			String token = res.getAccessToken() ; 
			ResponseEntity<String> roleRes = this.userService.getUserRoles(token) ; 
			if(roleRes.getStatusCode() == HttpStatus.OK) {
				// at this point we need to get the user id, and the roles set 
				// then construct the UserDetail object 
				ArrayList<String> ls = mapper.mapObject(roleRes.getBody(), ArrayList.class); 
				// Decode the token and get the 
				String userId = tokenProvider.getUserIdFromJWT(token) ; 
				List<GrantedAuthority> roles  = ls.stream().map(role->
				   new SimpleGrantedAuthority(role) 
				).collect(Collectors.toList()) ; 
				return new UserPrincipal(userId, "first", "last", email, password, token, roles);
			}else {
				System.out.println("Here 2222");
				RestMessage msg = mapper.mapObject(roleRes.getBody() , RestMessage.class);
				String message = String.join("\n", msg.getMessages()) ; 
				throw new UsernameNotFoundException(message) ; 
			}	
		}else {
			System.out.println("Here 3333");
			RestMessage msg = mapper.mapObject(jwt.getBody(), RestMessage.class) ; 
			String message = String.join("\n", msg.getMessages()) ; 
			throw new UsernameNotFoundException(message) ; 
		}
		
	}
	
	public UserDetails loadUserById(String id){
		return null ; 
	}

}
