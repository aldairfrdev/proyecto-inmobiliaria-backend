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

import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;

import com.ipartek.springboot.backend.elpisito.models.services.IInmuebleService;


@RestController
@RequestMapping("/api")
public class InmuebleRestController {
	
	@Autowired
	private IInmuebleService inmuebleService;
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/inmuebles")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = null;
		
		try {
			
			resultado = inmuebleService.findAll();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Inmueble>>(resultado, HttpStatus.OK);
		
		
	}
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/inmuebles-activos")
	public ResponseEntity<?> findAllActive(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = null;
		
		try {
			
			resultado = inmuebleService.findAllActive();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Inmueble>>(resultado, HttpStatus.OK);
		
	}	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/inmuebles-portada")
	public ResponseEntity<?> findAllPortada(){
		
		Map<String, Object> response = new HashMap<>();
		List<Inmueble> resultado = null;
		
		try {
			
			resultado = inmuebleService.findAllPortada();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Inmueble>>(resultado, HttpStatus.OK);
	}
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/inmueble/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		
		Inmueble inmueble = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			inmueble = inmuebleService.findById(id);
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		
		if(inmueble == null) {
			response.put("mensaje", "El inmueble ID: " + id.toString() + " no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Inmueble>(inmueble,HttpStatus.OK);
		
	}
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PostMapping("/inmueble")
	public ResponseEntity<?> create(@RequestBody Inmueble inmueble){
		
		
		Inmueble  inmuebleNew=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			inmuebleNew = inmuebleService.save(inmueble);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear el inmueble en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El Inmueble: " + inmuebleNew.getId() + " ha sido creado con éxito");
		response.put("inmueble", inmuebleNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PutMapping("/inmueble")
	public ResponseEntity<?> update(@RequestBody Inmueble inmueble){
		
		Long id = inmueble.getId();
		Inmueble inmuebleActual = inmuebleService.findById(id);//El que está actualmente en la BBDD
		Inmueble inmuebleUpdated=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(inmuebleActual==null) {
			response.put("mensaje", "Error: no se pudo editar el inmueble con Id: ".concat(id.toString()).concat(" no existe en la BBDD"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			inmuebleUpdated = inmuebleService.save(inmueble);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el inmueble en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","El inmueble ha sido actualizado con éxito");
		response.put("inmueble", inmuebleUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
		
	}
	

	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@DeleteMapping("/inmueble/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			inmuebleService.deleteById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el inmueble en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		
		response.put("mensaje", "El inmueble ha sido eliminado con éxito de la BBDD");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	

}
