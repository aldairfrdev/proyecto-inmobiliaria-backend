package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.elpisito.models.dao.IImagenDAO;

import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;


@Service
public class ImagenServiceImpl implements IImagenService {

	@Autowired
	private IImagenDAO imagenDAO;
	
	@Override
	public List<Imagen> findAll() {
		return (List<Imagen>)imagenDAO.findAll();
	}

	@Override
	public Imagen findById(Long id) {
		return imagenDAO.findById(id).orElse(null);
	}

	@Override
	public Imagen save(Imagen imagen) {
		return imagenDAO.save(imagen);
	}

	@Override
	public void deleteById(Long id) {
		imagenDAO.deleteById(id);
		
	}

}
