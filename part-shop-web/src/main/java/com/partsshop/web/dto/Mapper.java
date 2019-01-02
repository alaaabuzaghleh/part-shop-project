package com.partsshop.web.dto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Mapper {
	
	@Autowired
	private ObjectMapper mapper ; 
	
	public <T> T mapObject(String value, Class<T> result) {
		
		try {
			return mapper.readValue(value, result) ;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return null ; 
		
	}

}
