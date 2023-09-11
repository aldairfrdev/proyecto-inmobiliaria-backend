package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.elpisito.models.dao.IUsuarioDAO;

import com.ipartek.springboot.backend.elpisito.models.entity.Usuario;



@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//Utilizamos el @bean declarado en la clase SecurityConfig 
	

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDAO.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		return usuarioDAO.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {
		//El usuario que llega aquí llega con el password sin hashear
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioDAO.save(usuario);
	}

	@Override
	public void deleteById(Long id) {
		usuarioDAO.deleteById(id);
		
	}
	
	

}
