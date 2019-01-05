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
import com.partsshop.web.dto.PartsCategoryRest;
import com.partsshop.web.dto.ShopsRest;
import com.partsshop.web.service.AutoPartsService;


@Controller
@RequestMapping("/auto-parts")
public class AutoPartsController {
	
	@Autowired 
	private AutoPartsService autoPartsService ; 
	@Autowired 
	private Mapper mapper ; 
	@GetMapping("/list")
	public String getAllAutoParts(Model model) {
		ResponseEntity<String> res = this.autoPartsService.getPartsFromRest(); 
		if(res.getStatusCode().equals(HttpStatus.OK)) {
			ArrayList<PartsCategoryRest> ls =  mapper.mapObject(res.getBody(), ArrayList.class) ; 
			model.addAttribute("auto_parts_list", ls) ; 
			return "pages/auto-parts-list"; 
		}else {
			return "pages/error-404"; 
		}
		
	}
	
	
	

}
