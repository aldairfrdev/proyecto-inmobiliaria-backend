package com.ipartek.springboot.backend.elpisito.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class AuthController {

	
	private final AuthenticationManager authenticationManager;
	private final JWTUserDetailsService jwtUserDetaisService;
	private final JWTService jwtService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> postToken (@RequestBody JWTRequest request){
		
		this.authenticate(request);
		
		final var userDetails = this.jwtUserDetaisService.loadUserByUsername(request.getUsername());
		
		final String token = jwtService.generateToken(userDetails);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization", "Bearer " + token);
		
		return ResponseEntity.ok()
				.headers(headers)
				.body(new JWTResponse(token));
	}
	
	
	private void authenticate (JWTRequest request) {
		
		try {
				
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			
		} catch (BadCredentialsException e) {
			
			throw new RuntimeException(e.getMessage());
			
		} catch (DisabledException e) {
			
			throw new RuntimeException(e.getMessage());
		}
		
	}
}
