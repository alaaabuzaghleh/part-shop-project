package com.partsshop.rest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.partsshop.rest.model.UserRoles;

@Document(collection="users")
public class User {
	
	@Id 
	private String id ; 
	private String firstName ; 
	private String lastName ; 
	private String email ; 
	private String password ; 
	private List<UserRoles> roles ; 
	
	public User() {}
	
	public User(String firstName, String lastName, String email, String password, 
			List<UserRoles> roles) {
		
		this.firstName = firstName ; 
		this.lastName = lastName ; 
		this.email = email ; 
		this.password = password ; 
		this.roles = roles ; 
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		 return this.id + ", " + this.getFirstName() + ", " + this.getLastName() + ", " + this.getEmail() + ", " + this.getRoles().toString() ; 
	}

}
