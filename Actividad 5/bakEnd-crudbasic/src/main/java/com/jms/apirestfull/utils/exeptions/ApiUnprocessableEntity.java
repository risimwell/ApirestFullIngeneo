package com.jms.apirestfull.utils.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
Exeption peronalizada de estatus 422 
*/

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ApiUnprocessableEntity extends Exception{
	
	
	public ApiUnprocessableEntity(String message) {
		
		super(message);
	}

}
