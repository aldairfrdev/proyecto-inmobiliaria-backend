package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;

public interface ITipoService {
	
	List<Tipo> findAll();
	Tipo findById(Long id);
	Tipo save(Tipo tipo);
	void deleteById(Long id);

}
