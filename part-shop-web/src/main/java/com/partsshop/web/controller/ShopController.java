package com.partsshop.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partsshop.web.dto.Mapper;
import com.partsshop.web.dto.ShopsRest;
import com.partsshop.web.service.ShopService;

@Controller
@RequestMapping("/auto-parts-shops")
public class ShopController {
	
	@Autowired 
	private ShopService shopService ; 
	@Autowired 
	private Mapper mapper ; 
	@GetMapping("/list")
	public String getAllShop(Model model) {
		ResponseEntity<String> res = this.shopService.getShopsFromRest() ; 
		if(res.getStatusCode().equals(HttpStatus.OK)) {
			ArrayList<ShopsRest> ls =  mapper.mapObject(res.getBody(), ArrayList.class) ; 
			model.addAttribute("shop_list", ls) ; 
			return "pages/shops-list"; 
		}else {
			return "pages/error-404"; 
		}
		
	}

}
