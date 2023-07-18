package com.jms.apirestfull.validator;

import org.springframework.stereotype.Service;

import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;

/**
Interface para la validacion de datos recibidos para la creacion de usuarios
*/


@Service
public interface UserValidator {
	
	void validator(UserRequest request ) throws ApiUnprocessableEntity; 

}
