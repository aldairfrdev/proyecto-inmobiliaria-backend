package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;


import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;

public interface IPoblacionService {

	List<Poblacion> findAll();
	Poblacion findById(Long id);
	Poblacion save(Poblacion poblacion);
	void deleteById(Long id);
	
}
