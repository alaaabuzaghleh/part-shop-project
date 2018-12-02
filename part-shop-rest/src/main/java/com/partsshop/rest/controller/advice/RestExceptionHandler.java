package com.partsshop.rest.controller.advice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.exception.RestException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class) ; 
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";
    private static final String BAD_CREDENTIAL = "rest.badlogin" ; 
 
    @Autowired
    private MessageSource messageSource ; 
    
    @ExceptionHandler(value=BadCredentialsException.class)
    public ResponseEntity<RestMessage> handleBadCredentials(Locale locale){
    	String errorMessage = messageSource.getMessage(BAD_CREDENTIAL, null, locale);
        List<String> ls= new ArrayList<>() ; 
        ls.add(errorMessage);
        return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<RestMessage> handleIllegalArgument(RestException ex, Locale locale) {
    	    logger.info(locale.getLanguage());
    	    logger.info(ex.getArgs().toString());
        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);
        List<String> ls= new ArrayList<>() ; 
        ls.add(errorMessage);
        return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        BindingResult result = ex.getBindingResult();
        result.getAllErrors().forEach(System.out::println);
        System.out.println(locale.getLanguage());
       
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError ->messageSource.getMessage(objectError, locale) )
                .collect(Collectors.toList());
        
        return new ResponseEntity<>(new RestMessage(false,errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestMessage> handleExceptions(Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
        ex.printStackTrace();
        List<String> ls= new ArrayList<>() ; 
        ls.add(errorMessage);
        return new ResponseEntity<>(new RestMessage(false,ls), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
