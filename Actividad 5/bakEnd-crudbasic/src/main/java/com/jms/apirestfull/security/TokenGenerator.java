package com.jms.apirestfull.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.json.JSONObject;


import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
	
	
	private static final String SECRET_KEY = "camilo1212";

	public String generate(String username) {
	// Aquí puedes personalizar el contenido del token con los datos que quieras
	// Por ejemplo, puedes incluir el username y el rol del usuario
	String token = Jwts.builder()
	.setSubject(username)
	.setIssuedAt(new Date())
	.setExpiration(new Date(System.currentTimeMillis() + 66000)) // 1 hora de duración
	.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	.compact();
	JSONObject jsonToken = new JSONObject();
    jsonToken.put("token", token);
    return token;
	
	}

	public boolean validate(String token) {
	    try {
	        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
	        // Si no hay excepción, el token es válido
	        return true;
	    } catch (SignatureException e) {
	        // El token tiene una firma inválida
	        System.out.println("Invalid signature: " + e.getMessage());
	        // Realiza alguna acción adicional para manejar la firma inválida
	        // Por ejemplo, lanzar una excepción personalizada o guardar registros
	        // throw new SignatureValidationException("Invalid signature");
	        // logError("Invalid signature: " + e.getMessage());
	    } catch (ExpiredJwtException e) {
	        // El token ha expirado
	        System.out.println("Token expired: " + e.getMessage());
	        // Realiza alguna acción adicional para manejar el token expirado
	        // Por ejemplo, lanzar una excepción personalizada o guardar registros
	        // throw new TokenExpiredException("Token expired");
	        // logError("Token expired: " + e.getMessage());
	    } catch (Exception e) {
	        // Otra excepción
	        System.out.println("Error: " + e.getMessage());
	        // Realiza alguna acción adicional para manejar otras excepciones
	        // Por ejemplo, lanzar una excepción personalizada o guardar registros
	        // throw new CustomException("Token validation error");
	        // logError("Error: " + e.getMessage());
	    }
	    // Si hay excepción, el token no es válido
	    return false;
	}

	
	
	
	
}


