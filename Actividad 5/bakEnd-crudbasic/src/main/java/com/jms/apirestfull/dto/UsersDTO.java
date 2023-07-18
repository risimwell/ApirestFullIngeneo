package com.jms.apirestfull.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data

public class UsersDTO implements Serializable {
	
	private String id;
	private String firstname;
	private String lastname;
	private String password;
	private Date createAt;
	private Date updateAT;
	


private String username; // El atributo que guarda el nombre de usuario

//Otros atributos del usuario

public String getUsername() {
return this.username; // El método que devuelve el nombre de usuario
}


}