package com.partsshop.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.partsshop.rest.model.User;
import com.partsshop.rest.repo.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired 
	private UserRepo userRepo ; 

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = this.userRepo.findByEmail(email) 
				.orElseThrow(()-> new UsernameNotFoundException("User with emai: " + email +"  not found ")) ;
		
		return UserPrincipal.create(user) ; 
  
	}
	
	public UserDetails loadUserById(String id){
		User user = this.userRepo.findById(id) 
				.orElseThrow(()-> new UsernameNotFoundException("User with id: " + id +"  not found ")) ;
		
		return UserPrincipal.create(user) ; 
	}

}
