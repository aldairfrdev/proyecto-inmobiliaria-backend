package com.ipartek.springboot.backend.elpisito.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	/*
	 * Tenemos anotado un objeto de la clase UserDetailsService como @Servicio
	 * y es lo único que necesita SpringSecurity para tener un User ACTIVO
	 * que se le suele cargar 
	 * 
	 * 
	 * 
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		return usuarioDAO.findOneByEmail(username).map(usuario ->{
			
			//Si la BBDD devuelve un solo valor de un campo "rol" de la BBDD
			var authorities = List.of(new SimpleGrantedAuthority(usuario.getRol()));
			
			
			//Si la BBDD devuelve un set o un list... es decir varios roles...
			/*var roles = usuario.getRoles(); //Existiendo en la tabla de usuarios 
			var authorities = roles.stream()
					.map(rol -> new SimpleGrantedAuthority(rol.getName())
							.collect(Collectors.toList());
			*/	
			
			
			//User extiende UserDetails
			return new User(usuario.getEmail(), usuario.getPassword(), authorities);
			
		}).orElseThrow( ()-> new UsernameNotFoundException("Usuario no encontrado"));
		
	}
	
	


}
