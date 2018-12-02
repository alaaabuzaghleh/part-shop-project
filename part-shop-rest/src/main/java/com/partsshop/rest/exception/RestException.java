package com.partsshop.rest.exception;

public class RestException extends RuntimeException {
	
	private String message;
	
	private Object[] args;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	

}
