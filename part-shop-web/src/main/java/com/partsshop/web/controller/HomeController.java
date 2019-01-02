package com.partsshop.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping(value= {"", "/"})
	public String goHomePage(Authentication auth) {
		System.out.println(auth.getPrincipal().getClass()) ; 
		return "pages/index" ; 
	}
	

}
