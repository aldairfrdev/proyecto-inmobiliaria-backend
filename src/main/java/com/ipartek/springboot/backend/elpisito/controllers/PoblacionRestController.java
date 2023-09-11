package com.ipartek.springboot.backend.elpisito.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.springboot.backend.elpisito.models.entity.Poblacion;

import com.ipartek.springboot.backend.elpisito.models.services.IPoblacionService;



@RestController
@RequestMapping("/api")
public class PoblacionRestController {
	
	@Autowired
	private IPoblacionService poblacionService;

	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/poblaciones")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Poblacion> resultado = null;
		
		try {
			
			resultado = poblacionService.findAll();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Poblacion>>(resultado, HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/poblacion/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Poblacion poblacion = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			poblacion = poblacionService.findById(id);
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		
		if(poblacion == null) {
			response.put("mensaje", "La población ID: " + id.toString() + " no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Poblacion>(poblacion,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PostMapping("/poblacion")
	public ResponseEntity<?> create(@RequestBody Poblacion poblacion){
		
		Poblacion  poblacionNew=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			poblacionNew = poblacionService.save(poblacion);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la población en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La población: " + poblacionNew.getNombre() + " ha sido creado con éxito");
		response.put("poblacion", poblacionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PutMapping("/poblacion")
	public ResponseEntity<?> update(@RequestBody Poblacion poblacion){
		
		Long id = poblacion.getId();
		Poblacion poblacionActual = poblacionService.findById(id);//El que está actualmente en la BBDD
		Poblacion poblacionUpdated=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(poblacionActual==null) {
			response.put("mensaje", "Error: no se pudo editar la población con Id: ".concat(id.toString()).concat(" no existe en la BBDD"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			poblacionUpdated = poblacionService.save(poblacion);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la población en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","La población ha sido actualizada con éxito");
		response.put("poblacion", poblacionUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@DeleteMapping("/poblacion/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			poblacionService.deleteById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la población en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		
		response.put("mensaje", "La población ha sido eliminada con éxito de la BBDD");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	

}
