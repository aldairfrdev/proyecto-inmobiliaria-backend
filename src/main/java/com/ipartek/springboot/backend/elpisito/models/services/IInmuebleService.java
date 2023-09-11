package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;


public interface IInmuebleService {
	
	List<Inmueble> findAll();
	List<Inmueble> findAllActive();
	List<Inmueble> findAllPortada();
	Inmueble findById(Long id);
	Inmueble save(Inmueble inmueble);
	void deleteById(Long id);

}
