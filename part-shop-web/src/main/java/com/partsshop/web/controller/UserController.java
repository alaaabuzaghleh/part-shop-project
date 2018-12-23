package com.partsshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/login")
	public String goLogin() {
		return "pages/login";
	}

}
