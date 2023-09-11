package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.elpisito.models.dao.IPoblacionDAO;

import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;




@Service
public class PoblacionServiceImpl implements IPoblacionService {

	
	@Autowired
	private IPoblacionDAO poblacionDAO;
	
	@Override
	public List<Poblacion> findAll() {
		return (List<Poblacion>)poblacionDAO.findAll();
	}
	
	@Override
	public Poblacion findById(Long id) {
		return poblacionDAO.findById(id).orElse(null);
	}

	@Override
	public Poblacion save(Poblacion poblacion) {
		return poblacionDAO.save(poblacion);
	}

	@Override
	public void deleteById(Long id) {
		poblacionDAO.deleteById(id);
		
	}

}
