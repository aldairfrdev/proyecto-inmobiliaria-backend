package com.ipartek.springboot.backend.elpisito.security;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JWTValidationFilter extends OncePerRequestFilter{

	private final JWTService jwtService;
	private final JWTUserDetailsService jwtUserDetailsService;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String AUTHORIZATION_HEADER_BEARER = "Bearer ";
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		
		final var requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
		String username = null;
		String jwt = null;
		
		if(Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith(AUTHORIZATION_HEADER_BEARER)) {
			
			jwt = requestTokenHeader.substring(7);
			
			try {
				
				username = jwtService.getUsernameFromToken(jwt);
				
			} catch(IllegalArgumentException e) {
				
				
				
			} catch(ExpiredJwtException e) {
				
				
				
			}
			
		} // end if
	
		if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
			
			final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
			
			if(this.jwtService.validateToken(jwt, userDetails)) {
																								//usuario y password    //roles de usuario
				var usernameAndPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
				usernameAndPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernameAndPasswordAuthenticationToken);
			
			}
		}
		
		filterChain.doFilter(request, response);
	}
	

	
	
}
