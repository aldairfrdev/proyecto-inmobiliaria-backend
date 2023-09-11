package com.ipartek.springboot.backend.elpisito.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	/*
	 * En esta clase vamos a definir varios parámetros 
	 * como tiempo de validez del token, el nombre secreto,
	 * conseguir los claims dentro de un objeto Claims
	 * 
	 */
	
	public static final long JWT_TOKEN_VALIDITY = 1200; //segundos = 20 minutos
	public static final String JWT_SECRET = "IODNXCSUEMAWQLEPSJENCFUTWXTYDGHZQNFDJ";
	
	
	///////////////////////////////////////////////////////////////////////
	//MÉTODOS PARA OBTENER INFORMACIÓN DEL TOKEN
	///////////////////////////////////////////////////////////////////////
	
	
	//Este método nos va a devolver los claims del token
	private Claims getAllClaimsFromToken(String token) {
		
		//Creamos una llave partiendo del JWT_SECRET
		final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)); 
		
		//Vamos a realizar el proceso inverso a la creación del token
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	//Este método recibe los 'claims' en un obejto Claims y devuelve obejtos 
	//del tipo del claim (String, Vaca etc)
	public <T> T getClaimsFromToken(String token, Function<Claims,T> claimsResolver) {
		
		
		final Claims claims = this.getAllClaimsFromToken(token);
		
		return claimsResolver.apply(claims);
		
	}
				
	//Este método devuelve la fecha de expiración del token
	private Date getExpirationDateFromToken(String token) {
											//clase | método de la clase
		return this.getClaimsFromToken(token, Claims::getExpiration); //c->c.getExpiration()
	}
	
	//Vamos a comprobar si el tooken está expirado...
	private boolean isTokenExpired(String token) {
		
		final Date expirationDate = this.getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	//Vamos a conseguir el nombre de usuario en el toekn (se supone que lo vamos a guardar)
	public String getUsernameFromToken(String token) {
											//clase | método de la clase
		return this.getClaimsFromToken(token, Claims::getSubject); //c->c.getSubject()
	}
	
	//Este método va a comprobar si el token es válido: no caducado y que el usuario sea válido
	public boolean validateToken(String token, UserDetails userDetails) {
		
		final String usernameFromUserDetails = userDetails.getUsername();
		final String usernameFromJWT = this.getUsernameFromToken(token);
		
		return usernameFromUserDetails.equals(usernameFromJWT) && !this.isTokenExpired(token);
		
		
	}
	
	
	///////////////////////////////////////////////////////////////////////
	//MÉTODOS PARA CREAR EL TOKEN
	///////////////////////////////////////////////////////////////////////
	
	private String getToken(Map<String, Object> claims, String subject) {
		
		
		final Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis())) //ahora
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)) //JWT_TOKEN_VALIDITY son segundos y deben ser milisegundos
				.signWith(key)
				.compact();
	}
		
	public String generateToken(UserDetails userDetails) {
		
		final Map<String, Object> claims = Collections.singletonMap("ROLES", userDetails.getAuthorities().toString());
		
		//Aquí es donde podríamos pasar datos en el objeto claims del token que estamos creando
		return this.getToken(claims, userDetails.getUsername());
	}
	
}




