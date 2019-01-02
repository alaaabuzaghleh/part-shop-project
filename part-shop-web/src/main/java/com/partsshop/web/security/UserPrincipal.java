package com.partsshop.web.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.partsshop.web.dto.User;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = -3885276606596711204L;
	private String id ; 
	private String firstName ; 
	private String lastName ; 
	@JsonIgnore
	private String email ;
	@JsonIgnore 
	private String password ; 
	@JsonIgnore
	private String token; 
	
	private Collection<? extends GrantedAuthority> authorities ; 
	
	public UserPrincipal(String id, String firstName, String lastName, String email, String password, String token,Collection<? extends GrantedAuthority> authorities ) {
		this.id = id ; 
		this.firstName = firstName ; 
		this.lastName = lastName ; 
		this.email = email ; 
		this.password = password ; 		
		this.authorities = authorities;
		this.token = token ; 
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

	public String getToken() {
		return token;
	}
	

}
