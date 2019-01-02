package com.partsshop.web.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.partsshop.web.dto.CarRest;
import com.partsshop.web.dto.Mapper;
import com.partsshop.web.service.AutoService;

@Controller
@RequestMapping("/autos")
public class AutoController {
	@Autowired 
	private AutoService autoService ; 
	@Autowired 
	private Mapper mapper ; 
	@GetMapping("/list")
	public String getAllAuto(Model model) {
		ResponseEntity<String> res = this.autoService.getCarsFromRest() ; 
		if(res.getStatusCode().equals(HttpStatus.OK)) {
			ArrayList<CarRest> ls =  mapper.mapObject(res.getBody(), ArrayList.class) ; 
			model.addAttribute("auto_list", ls) ; 
			return "pages/autos-list"; 
		}else {
			return "pages/autos-list"; 
		}
		
	}

}
