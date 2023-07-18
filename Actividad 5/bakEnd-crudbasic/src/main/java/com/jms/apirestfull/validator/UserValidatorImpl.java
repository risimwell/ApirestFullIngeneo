package com.jms.apirestfull.validator;

import org.springframework.stereotype.Component;

import com.jms.apirestfull.dto.LoginRequest;
import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;

@Component
public class UserValidatorImpl implements UserValidator {

	@Override
	public void validator(UserRequest request) throws ApiUnprocessableEntity {
		
		if (request.getFirstname() == null || request.getFirstname().isEmpty()) {
			this.message("el nombre es obligatorio");
		}
		
		if (request.getFirstname().length() <4) {
			this.message("el nombre es muy corto minimo 4 caracteres ");
		}
		
		if (request.getLastname()== null || request.getLastname().isEmpty()) {
			this.message("el apellido es obligatorio");
		}
		
		if (request.getLastname().length() <4) {
			this.message("el apellido es muy corto minimo 4 caracteres");
		}
		
		if (request.getUsername() == null || request.getUsername().isEmpty()) {
			this.message("el Nombre de Usuario  es obligatorio");
		}
		
		if (request.getUsername().length() <3) {
			this.message("el Nombre de Usuario es muy corto");
		}
		

		if (request.getPassword() == null || request.getPassword().isEmpty()) {
			this.message("el contraseña es obligatorio");
		}
		
		if (request.getPassword().length() <8) {
			this.message("la contraseña debe tener 8 caracteres ");
		}
		
	}
	
	private void message(String message) throws ApiUnprocessableEntity {
		
		throw new ApiUnprocessableEntity(message);
		
	}
	
	// Definir el método validator con el parámetro LoginRequest
	public void validator(LoginRequest request) throws ApiUnprocessableEntity {

	// Validar que el username no sea nulo o vacío
	if (request.getUsername() == null || request.getUsername().isEmpty()) {
	// Lanzar una excepción con el código de error 422 (Unprocessable Entity)
	this.message("El username es requerido");
	}

	// Validar que el password no sea nulo o vacío
	if (request.getPassword() == null || request.getPassword().isEmpty()) {
	// Lanzar una excepción con el código de error 422 (Unprocessable Entity)
	this.message("El password es requerido");
	}

	}

}
