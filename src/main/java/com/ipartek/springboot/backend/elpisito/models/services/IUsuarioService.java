package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;


import com.ipartek.springboot.backend.elpisito.models.entity.Usuario;

public interface IUsuarioService {
	
	List<Usuario> findAll();
	Usuario findById(Long id);
	Usuario save(Usuario usuario);
	void deleteById(Long id);

}
