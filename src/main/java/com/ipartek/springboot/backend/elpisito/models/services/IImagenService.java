package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;


public interface IImagenService {
	
	
	List<Imagen> findAll();
	Imagen findById(Long id);
	Imagen save(Imagen imagen);
	void deleteById(Long id);

}
