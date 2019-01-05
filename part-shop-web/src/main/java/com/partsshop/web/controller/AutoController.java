package com.partsshop.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.partsshop.web.datatable.DataTableRequest;
import com.partsshop.web.datatable.DataTableResults;
import com.partsshop.web.datatable.PaginationCriteria;
import com.partsshop.web.dto.CarRest;
import com.partsshop.web.dto.Mapper;
import com.partsshop.web.service.AutoService;
import com.partsshop.web.util.AppUtil;

@Controller
@RequestMapping("/autos")
public class AutoController {
	@Autowired 
	private AutoService autoService ; 
	@Autowired 
	private Mapper mapper ; 

	
	@GetMapping(value= {"", "/"})
	public String goAutoPage(Model model) {
		return "pages/autos-list" ; 
	}
	
	@PostMapping("/list")
	@ResponseBody
	public String getAllAuto(@RequestBody DataTableRequest dataTableInRQ, HttpServletResponse response, Model model) {
		
		PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		
		//String baseQuery = "SELECT USER_ID as id, USER_NAME as name, SALARY as salary, (SELECT COUNT(1) FROM MYUSERS) AS total_records  FROM MYUSERS";
		String paginatedQuery = AppUtil.buildPaginatedQueryRest(pagination) ; 		
		System.out.println(paginatedQuery);
		
		ResponseEntity<String> res = this.autoService.getCarsFromRest(paginatedQuery) ; 
		
		if(res.getStatusCode() == HttpStatus.OK) {
			List<CarRest> ls = this.mapper.mapObject(res.getBody(), ArrayList.class) ; 
			DataTableResults<CarRest> dataTableResult = new DataTableResults<CarRest>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(ls);
			if (!AppUtil.isObjectEmpty(ls)) {
				Long count = this.mapper.mapObject(this.autoService.getCarsCountFromRest().getBody() , Long.class) ; 
				dataTableResult.setRecordsTotal(count.toString()) ; 
				dataTableResult.setPageSize(dataTableInRQ.getLength());
				Long recordAll = Long.parseLong(dataTableResult.getRecordsTotal()) ; 
				String totalPages = "" ; 
				if(recordAll % dataTableInRQ.getLength() == 0) {
					totalPages = String.valueOf( (recordAll / dataTableInRQ.getLength()))  ; 
				}else {
					 totalPages = String.valueOf( (recordAll / dataTableInRQ.getLength() + 1))  ; 
				}
				dataTableResult.setTotalPages(totalPages);
				
				dataTableResult.setRecordsFiltered(count.toString()) ;  
			
			}
			Gson gson = new Gson();
			GsonBuilder builder = new GsonBuilder(); 
			gson = builder.serializeNulls().create();
			return gson.toJson(dataTableResult);
			
		}else {// must changes later to other suitable error page
			return "pages/autos-list" ; 
		}
		
	}

}
