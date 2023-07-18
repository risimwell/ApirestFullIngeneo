
package com.jms.apirestfull.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {

private static final String SECRET_KEY = "camilo1212"; // La misma clave que usas para generar el token

@Autowired
private TokenGenerator tokenGenerator; // La clase que genera el token

@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
throws Exception {
// Obtener el token del encabezado Authorization
String token = request.getHeader("Authorization");
// Si el token es nulo o vacío, lanzar una excepción con el código 401 (Unauthorized)
if (token == null || token.isEmpty()) {
throw new IOException("Token is required");
}
// Si el token es válido, continuar con la ejecución
if (tokenGenerator.validate(token)) {
return true;
}
// Si el token es inválido, lanzar una excepción con el código 401 (Unauthorized)
else {
throw new IOException("Invalid token");
}
}
}