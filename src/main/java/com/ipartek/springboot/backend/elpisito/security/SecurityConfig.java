package com.ipartek.springboot.backend.elpisito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

	
	@Bean
	@Autowired
	SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception {
		
		
		http
		.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeRequests(auth -> {
			
			
			auth.anyRequest().permitAll();
			
			//auth.anyRequest().authenticated();
			
			
			
		}).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		
		
		http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
		
		http.cors( cors -> cors.disable());
		
		http.csrf( csrf -> csrf.disable() );
		
		return http.build();
	}
	
	/*
	 * Una aplicación de Spring Security solo puede tener un password encriptado
	 * Y además es obligatorio que lo tenga.
	 * Al estar anotado como @Bean esta permanentemente "cargado" en
	 * la aplicación y por lo tanto es el que está activo.
	 */
	
	
	@Bean //Este método devuelve un @Bean que es password encoder
		//un @Bean lo puedo usar cualquier parte del programa
	public PasswordEncoder passwordEncoder() {
		
	
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
	}
	
	
}
