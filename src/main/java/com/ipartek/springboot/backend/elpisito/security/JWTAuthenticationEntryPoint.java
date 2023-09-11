package com.ipartek.springboot.backend.elpisito.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * Este servicio es muy importante ya que vamos a decirle a Spring que vamos
 * a tener un end point de autenticación
 * Vamos a obtener el token a través de una petición POST y para ello hay
 * que crear un servicio (@Component) que implemente la interface AuthenticationEntrPoint
 * Este es un middleware de primer término que "recibe" el request y colapsa
 * todos los pasos ...
 */


@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint{

	
	@Override
	public void commence(
			HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
		
	}
	
	
}
