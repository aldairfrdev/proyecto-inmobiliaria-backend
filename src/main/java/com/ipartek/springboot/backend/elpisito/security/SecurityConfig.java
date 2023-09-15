package com.ipartek.springboot.backend.elpisito.security;

import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig {

	
	@Bean
	@Autowired
	SecurityFilterChain securityFilterChain(HttpSecurity http, JWTValidationFilter jwtValidationFilter) throws Exception {
		
		
		http
		.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeRequests(auth -> {
			
			//Podemos jugar con el SELECTOR: .anyRequest(), requestMatchers(9 por un lado
			//Podemos jugar con el AUTORIZADOR: .oermitAll(), authenticated() y .hasRole() por otro
			//haciendo combinaciones entre un selector y un autorizador
			
			
			//auth.anyRequest().permitAll();
			//auth.anyRequest().authenticated();
			
			//auth.requestMatchers("/api/inmuebles").authenticated();
			//auth.requestMatchers("api/**").hasRole("USER");
			//auth.requestMatchers("api/**").hasRole("USER,ADMIN,READER");
			//auth.requestMatchers("api/**").authenticated();
			//auth.requestMatchers("api/inmuebles").hasAnyRole("USER");
			//auth.anyRequest().hasRole("USER"); //Es obligatorio para Spring Security que en la BBDD esté anotado como "ROLE_NOMBRE"
			//auth.anyRequest().permitAll();
			
			//auth.requestMatchers("/authenticate").permitAll();
			//auth.requestMatchers("/media/**").permitAll();
			//auth.requestMatchers("api/**").hasRole("USER");
			
			
			//auth.anyRequest().permitAll();
			auth.anyRequest().authenticated();
			auth.requestMatchers("/authenticate").authenticated();
			
			
		}); //.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
		
		
		http.addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class);
		
		http.cors( cors -> corsConfigurationSource());
		
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
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		var config = new CorsConfiguration();
		
		config.setAllowedOrigins(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		config.setAllowedHeaders(List.of("*"));
		
		var source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
	
	//Ejemplo de otro tipo de configuración de CORS menos específico
	/*
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		
		var config = new CorsConfiguration();
		
		
		//config.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));	
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedOrigins(List.of("*"));
		config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS","PATCH"));
		config.setAllowCredentials(true);
		config.setExposedHeaders(List.of("Authorization")); //De esta forma podemos acceder a la info de "Authorization"
															//(headers del respnse) por ejemplo en un interceptor de Angular
		var source = new UrlBasedCorsConfigurationSource();
		
		
		
	}
	*/
}
