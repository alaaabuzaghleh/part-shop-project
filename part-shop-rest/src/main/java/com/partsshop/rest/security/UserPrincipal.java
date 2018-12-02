package com.partsshop.rest.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.partsshop.rest.model.User;

public class UserPrincipal implements UserDetails {
	
	private String id ; 
	private String firstName ; 
	private String lastName ; 
	@JsonIgnore
	private String email ;
	@JsonIgnore 
	private String password ; 
	
	private Collection<? extends GrantedAuthority> authorities ; 
	
	public UserPrincipal(String id, String firstName, String lastName, String email, String password,Collection<? extends GrantedAuthority> authorities ) {
		this.id = id ; 
		this.firstName = firstName ; 
		this.lastName = lastName ; 
		this.email = email ; 
		this.password = password ; 		
		this.authorities = authorities;
	}
	
	public static UserPrincipal create(User user) {
		List<GrantedAuthority> roles  = user.getRoles().stream().map(role->
		   new SimpleGrantedAuthority(role.getRoleName()) 
		).collect(Collectors.toList()) ; 
		
		return new UserPrincipal(user.getId(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getPassword(), 
				roles
				) ; 
				
	}
	
	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities ; 
	}

	public String getPassword() {
		return password;
	}
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true; 
	}
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
