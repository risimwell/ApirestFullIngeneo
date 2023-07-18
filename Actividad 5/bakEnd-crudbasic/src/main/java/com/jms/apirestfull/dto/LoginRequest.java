package com.jms.apirestfull.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginRequest implements Serializable{
	
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	

}

