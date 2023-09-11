package com.ipartek.springboot.backend.elpisito.security;

import lombok.Data;

@Data
public class JWTRequest {
	
	
	private String username;
	private String password;
	
}
