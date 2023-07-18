package com.jms.apirestfull.services.interfaces;

import java.util.List;


import org.springframework.stereotype.Service;

import com.jms.apirestfull.dto.LoginRequest;
import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.dto.UsersDTO;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;



@Service
public interface IUsersService {
	
	List<UsersDTO> finAll();
	
	UsersDTO finByUsername(String usermame);
	
	UsersDTO finByUserId(int userId);
	
	void save(UserRequest user);
	
	void saveAll(List<UserRequest> users);
	
	void deleteById(int userId);
	
	void update(UserRequest user, int userId);
	
	String generateToken(UsersDTO user);
	
	String login(LoginRequest request) throws ApiUnprocessableEntity;
	
	

}
