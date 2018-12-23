package com.partsshop.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.security.CurrentUser;
import com.partsshop.rest.security.UserPrincipal;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private MessageSource messageSource ; 
	
	@GetMapping("/roles")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<?> getUserRoles(@CurrentUser UserPrincipal currentUser, @RequestHeader("Accept-Language") Locale locale){
		List<String> roles = new ArrayList<>() ; 
		currentUser.getAuthorities().forEach(role -> roles.add(role.getAuthority()));
		
		if(roles.size()>0) {
			return new ResponseEntity<>(roles, HttpStatus.OK) ; 
		}else {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("user.roles.notfound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ; 
		}
		
	}
	
	

}
