package com.jms.apirestfull.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jms.apirestfull.dto.LoginRequest;
import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.dto.UsersDTO;
import com.jms.apirestfull.security.TokenGenerator;
import com.jms.apirestfull.services.interfaces.IUsersService;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;
import com.jms.apirestfull.validator.UserValidatorImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class Apicontroller {

@Autowired
private IUsersService usersService;

@Autowired
private UserValidatorImpl userValidator;

@Autowired
private TokenGenerator tokenGenerator; // Inyectar una instancia de TokenGenerator

@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> index() {

return ResponseEntity.ok(this.usersService.finAll());

}

@GetMapping(value = "/by/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> findByUsername(@PathVariable("username") String username) {

return ResponseEntity.ok(this.usersService.finByUsername(username));
}

@GetMapping(value = "/byId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> findById(@PathVariable("userId") int userId) {

return ResponseEntity.ok(this.usersService.finByUserId(userId));
}

@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> saveUser(@RequestBody UserRequest request) throws ApiUnprocessableEntity {

this.userValidator.validator(request);
this.usersService.save(request);

return ResponseEntity.ok(Boolean.TRUE);

}

@DeleteMapping(value = "/{userId}/delete")
public ResponseEntity<Object> deleteUser(@PathVariable int userId) {

this.usersService.deleteById(userId);
return ResponseEntity.ok(Boolean.TRUE);

}

@PutMapping(value = "/{userId}/update")
public ResponseEntity<Object> updateUser(@RequestBody UserRequest request, @PathVariable int userId) {

this.usersService.update(request, userId);
return ResponseEntity.ok(Boolean.TRUE);

}

// Definir el método login con la anotación @PostMapping
@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Object> login(@RequestBody LoginRequest request) throws ApiUnprocessableEntity {
	try {
		// Llamar al método login del servicio y obtener el token
		String token = usersService.login(request);
		// Crear una respuesta con el código 200 (OK)y el token como cuerpo
		return ResponseEntity.ok(token);
		} catch (ApiUnprocessableEntity e) {
		// Si hay una excepción, crear una respuesta con el código 401 (Unauthorized) y el mensaje de error como cuerpo
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
}}
