package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;


import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;

public interface IProvinciaService {
	
	List<Provincia> findAll();
	Provincia findById(Long id);
	Provincia save(Provincia provincia);
	void deleteById(Long id);

}
