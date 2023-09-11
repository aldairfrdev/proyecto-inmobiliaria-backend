package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.springboot.backend.elpisito.models.dao.IInmuebleDAO;

import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;




@Service
public class InmuebleServiceImpl implements IInmuebleService {

	@Autowired
	private IInmuebleDAO inmuebleDAO;
	
	
	@Override
	public List<Inmueble> findAll() {
		return (List<Inmueble>)inmuebleDAO.findAll();
	}

	@Override
	public Inmueble findById(Long id) {
		return inmuebleDAO.findById(id).orElse(null);
	}

	@Override
	public Inmueble save(Inmueble inmueble) {
		return inmuebleDAO.save(inmueble);
	}

	@Override
	public void deleteById(Long id) {
		inmuebleDAO.deleteById(id);
		
	}

	@Override
	public List<Inmueble> findAllActive() {

		 List<Inmueble> todosLosInmuebles = (List<Inmueble>)inmuebleDAO.findAll();
		 
		 return todosLosInmuebles.stream()
		 .filter(i -> i.getActivo().equals(1))
		 .collect(Collectors.toList());

	}

	@Override
	public List<Inmueble> findAllPortada() {
		
		List<Inmueble> todosLosInmuebles = (List<Inmueble>)inmuebleDAO.findAll();
		 
		 return todosLosInmuebles.stream()
		 .filter(i -> i.getPortada().equals(1))
		 .collect(Collectors.toList());
		
	}

}
