package com.partsshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.partsshop.web.dto.UserSignInRest;
import com.partsshop.web.dto.UserSignUpRest;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/login")
	public String goLogin(Model userSignInRequest) {
		userSignInRequest.addAttribute("userSignInRequest", new UserSignInRest());
		return "pages/login_v3.html";
	}

	
	@PostMapping("/login")
	public String handleLogin(@ModelAttribute UserSignInRest userSignInRequest, RedirectAttributes messages) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userSignInRequest.getUserEmail(), userSignInRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			return "redirect:/";
		} catch (Exception exp) {
		    messages.addFlashAttribute("login_error", "email/password error") ; 
		}
		return "redirect:/users/login";
	}
	
	
	@GetMapping("/signup")
	public String goRegister(Model userSignUpRequest) {
		userSignUpRequest.addAttribute("userSignUpRequest", new UserSignUpRest());
		return "pages/register_v3.html";
	}

	@GetMapping("/logout")
	public String handleLogOut() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/users/login" ; 
	}
	

}
