package com.jms.apirestfull.security;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SpringBootTest
public class InterceptorJwtIOTest {

@Mock
private TokenGenerator tokenGenerator;

@Mock
private HttpServletRequest request;

@Mock
private HttpServletResponse response;

@InjectMocks
private InterceptorJwtIO interceptor;

@BeforeEach
public void setUp() {
request = mock(HttpServletRequest.class); // crea un nuevo mock de request
}


@Test
public void testPreHandle() throws Exception {
// Caso de prueba con un token válido
// Arrange
String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBbGljZSIsImlzcyI6Imh0dHBzOi8vZXhhbXBsZS5jb20iLCJhdWQiOiJodHRwczovL2V4YW1wbGUuY29tIiwiZXhwIjoxNjY5NDY0Mzk5LCJpYXQiOjE2Njk0NjM5OTMsIm5iZiI6MTY2OTQ2Mzk5M30.8wYk7w2aQYf3yZt7vGgq6g8y0o4mVlF1H0XxO3k5fLs";
when(request.getHeader("Authorization")).thenReturn(token);
when(tokenGenerator.validate(token)).thenReturn(true);

// Act
boolean result = interceptor.preHandle(request, response, null);

// Assert
assertTrue(result);
verify(request).getHeader("Authorization");
verify(tokenGenerator).validate(token);

// Caso de prueba con un token inválido
// Arrange
token = "invalid-token";
when(request.getHeader("Authorization")).thenReturn(token);
when(tokenGenerator.validate(token)).thenThrow(new SignatureException("Invalid signature"));

// Act and Assert
assertThrows(SignatureException.class, () -> interceptor.preHandle(request, response, null));
verify(request).getHeader("Authorization");
verify(tokenGenerator).validate(token);

// Caso de prueba sin token
// Arrange
when(request.getHeader("Authorization")).thenReturn(null);

// Act and Assert

assertThrows(IOException.class, () -> interceptor.preHandle(request, response, null));
verify(request).getHeader("Authorization");
verifyNoInteractions(tokenGenerator);
}
}