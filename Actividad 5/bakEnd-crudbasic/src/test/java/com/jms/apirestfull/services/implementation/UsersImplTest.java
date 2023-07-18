package com.jms.apirestfull.services.implementation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.jms.apirestfull.dto.LoginRequest;
import com.jms.apirestfull.dto.UserRequest;
import com.jms.apirestfull.dto.UsersDTO;
import com.jms.apirestfull.entities.Users;
import com.jms.apirestfull.repository.UsersRepository;
import com.jms.apirestfull.security.TokenGenerator;
import com.jms.apirestfull.utils.exeptions.ApiUnprocessableEntity;
import com.jms.apirestfull.utils.hash.BCrypt;
import com.jms.apirestfull.utils.helpers.MHelpers;

@ExtendWith(MockitoExtension.class) // Usar la extensión de Mockito
public class UsersImplTest {

@Mock // Crear un mock de UsersRepository
private UsersRepository usersRepository;

@Mock // Crear un mock de TokenGenerator
private TokenGenerator tokenGenerator;

@InjectMocks // Crear un objeto real de UsersImpl e inyectarle los mocks
private UsersImpl usersImpl;

@Test // Crear un método de prueba para el método findAll
public void testFindAll() {
// Crear una lista de usuarios para simular los datos de la base de datos
List<Users> users = new ArrayList<>();
users.add(new Users(1, "user1", "pass1", "name1", "lastname1"));
users.add(new Users(2, "user2", "pass2", "name2", "lastname2"));

// Simular el comportamiento del método usersRepository.findAll para devolver la lista de usuarios
when(usersRepository.findAll()).thenReturn(users);

// Llamar al método usersImpl.findAll y obtener el resultado
List<UsersDTO> result = usersImpl.finAll();

// Verificar que el resultado tenga el mismo tamaño que la lista de usuarios
assertEquals(users.size(), result.size());

// Verificar que el resultado tenga los mismos datos que la lista de usuarios, usando la clase MHelpers para convertir los objetos
for (int i = 0; i < users.size(); i++) {
assertEquals(MHelpers.modelMapper().map(users.get(i), UsersDTO.class), result.get(i));
}
}

@Test // Crear un método de prueba para el método finByUsername
public void testFinById() {
// Crear un usuario para simular los datos de la base de datos
Users user = new Users(1, "user1", "pass1", "name1", "lastname1");

// Crear un Optional de Users con el usuario
Optional<Users> optionalUser = Optional.of(user);

// Simular el comportamiento del método usersRepository.findByUsername para devolver el Optional de Users según el username
when(usersRepository.findByUsername("user1")).thenReturn(optionalUser);

// Llamar al método usersImpl.finByUsername con el username "user1" y obtener el resultado
UsersDTO result = usersImpl.finByUsername("user1");

// Verificar que el resultado sea igual al usuario que se esperaba, usando la clase MHelpers para convertir los objetos
assertEquals(MHelpers.modelMapper().map(user, UsersDTO.class), result);
}
@Test // Crear un método de prueba para el método finByUsername
public void testFinByUsername() {
// Crear un usuario para simular los datos de la base de datos
Users user = new Users(1, "user1", "pass1", "name1", "lastname1");

// Crear un Optional de Users con el usuario
Optional<Users> optionalUser = Optional.of(user);

// Simular el comportamiento del método usersRepository.findByUsername para devolver el Optional de Users según el username
when(usersRepository.findByUsername("user1")).thenReturn(optionalUser);

// Llamar al método usersImpl.finByUsername con el username "user1" y obtener el resultado
UsersDTO result = usersImpl.finByUsername("user1");

// Verificar que el resultado sea igual al usuario que se esperaba, usando la clase MHelpers para convertir los objetos
assertEquals(MHelpers.modelMapper().map(user, UsersDTO.class), result);
}

@Test // Crear un método de prueba para el método save
public void testSave() {
// Crear un objeto UserRequest con los datos que quieras
UserRequest userRequest = new UserRequest("user3", "pass3", "name3", "lastname3");

// Simular el comportamiento del método usersRepository.save para que no haga nada
doAnswer(invocation -> invocation.getArgument(0)).when(usersRepository).save(any(Users.class));

// Crear un ArgumentCaptor de Users para capturar el argumento del método usersRepository.save
ArgumentCaptor<Users> captor = ArgumentCaptor.forClass(Users.class);

// Llamar al método usersImpl.save con el objeto userRequest
usersImpl.save(userRequest);

// Verificar que el método usersRepository.save se haya llamado una vez con el objeto Users correspondiente al UserRequest
verify(usersRepository).save(captor.capture());
Users user = captor.getValue();
assertAll(
() -> assertEquals(userRequest.getUsername(), user.getUsername()),
() -> assertEquals(userRequest.getFirstname(), user.getFirstname()),
() -> assertEquals(userRequest.getLastname(), user.getLastname())
);
}



}