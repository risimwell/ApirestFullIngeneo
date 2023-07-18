package com.jms.apirestfull.utils.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
Exeption peronalizada de estatus 401
*/

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorized extends Exception{
	
	
	public ApiUnauthorized(String message) {
		
		super(message);
	}
	 
		 
	 

}
