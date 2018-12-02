package com.partsshop.rest.dto;

import java.util.List;

public class RestMessage {

	private boolean success;
    private List<String> messages;

    public RestMessage(boolean success, List<String> messages) {
        this.messages = messages;
        this.success = success ; 
    }

	public boolean isSuccess() {
		return success;
	}

	public List<String> getMessages() {
		return messages;
	}

	
    
    
}
