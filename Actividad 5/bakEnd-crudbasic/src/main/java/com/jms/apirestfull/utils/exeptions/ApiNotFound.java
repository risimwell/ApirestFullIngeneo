package com.jms.apirestfull.utils.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
Exeption peronalizada de estatus 404 
*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends Exception{
	
	
	public ApiNotFound(String message) {
		
		super(message);
	}

}
