package com.jms.apirestfull.services.implementation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jms.apirestfull.dto.LoginRequest;
import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.dto.UsersDTO;
import com.jms.apirestfull.entities.Users;
import com.jms.apirestfull.repository.UsersRepository;
import com.jms.apirestfull.security.TokenGenerator;
import com.jms.apirestfull.services.interfaces.IUsersService;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;
import com.jms.apirestfull.utils.hash.BCrypt;
import com.jms.apirestfull.utils.helpers.MHelpers;
import org.json.JSONObject;

import lombok.Data;


@Data
@Component

public class UsersImpl implements IUsersService {
	
	@Autowired
	private TokenGenerator tokenGenerator;
	
	@Autowired
	private	UsersRepository usersRepository;
	
	@Override
	public List<UsersDTO> finAll() {
		
		List<UsersDTO> dto= new ArrayList<>();
		
		Iterable<Users> users = this.usersRepository.findAll();
		
		for (Users user: users) {
			
			UsersDTO userDTo = MHelpers.modelMapper().map(user, UsersDTO.class);
			
			dto.add(userDTo);		
			}
		
	
		return dto;
	}

	@Override
	public UsersDTO finByUsername(String usermame) {
		Optional<Users> users = this.usersRepository.findByUsername(usermame);
		
		return MHelpers.modelMapper().map(users.orElse(null), UsersDTO.class);
	}

	

	@Override
	public UsersDTO finByUserId(int useId) {
		Optional<Users> users = this.usersRepository.findById(useId);

		return MHelpers.modelMapper().map(users.orElse(null), UsersDTO.class);
		}

	@Override
	public void save(UserRequest user) {
		
		Users users = MHelpers.modelMapper().map(user, Users.class);
		
		users.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) );
	
		this.usersRepository.save(users);
		
	}

	
	@Override
	public void update(UserRequest request, int userId) {
		
		Optional<Users> users = this.usersRepository.findById(userId);
		
		Users user= users.get();
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setUsername(request.getUsername());
		
		if (!request.getPassword().isEmpty()) {
			
		user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()) );
	}
		this.usersRepository.save(user);
	}	
	
	@Override
	public void saveAll(List<UserRequest> users) {
		
		List<Users> u = new ArrayList<>();
		
		for (UserRequest user : users) {
			
			Users us = MHelpers.modelMapper().map(user, Users.class);
			
			u.add(us);
			
		}
		
		this.usersRepository.saveAll(u);
	}

	@Override
	public void deleteById(int userId) {
		
		this.usersRepository.deleteById(userId);
		
	}
	
	private UsersDTO convertToUsersDTO(final Users users) {
		
		return MHelpers.modelMapper().map(users, UsersDTO.class);
		
		
	}

	@Override
	public String generateToken(UsersDTO user) {
		
		return tokenGenerator.generate(user.getUsername());
		
		
	}

	@Override
	public String login(LoginRequest request) throws ApiUnprocessableEntity {
	    // Buscar el usuario por el username
	    UsersDTO user = this.finByUsername(request.getUsername());
	    // Verificar que el usuario exista y que la contraseña coincida
	    if (user == null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
	        // Lanzar una excepción con el código de error 401 (Unauthorized)
	        throw new ApiUnprocessableEntity("Invalid credentials");
	    }
	    // Generar un token con los datos del usuario
	    String token = this.generateToken(user);
	    
	    // Crear un objeto JSON con el token y el ID del usuario
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("token", token);
	    jsonObject.put("userId", user.getId()); // <-- Agregar el ID del usuario al objeto JSON
	    
	    // Devolver el objeto JSON como cadena
	    return jsonObject.toString();
	}

}
