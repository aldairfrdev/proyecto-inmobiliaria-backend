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

import com.ipartek.springboot.backend.elpisito.models.entity.Provincia;

import com.ipartek.springboot.backend.elpisito.models.services.IProvinciaService;


@RestController
@RequestMapping("/api")
public class ProvinciaRestController {
	
	@Autowired
	private IProvinciaService provinciaService;
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/provincias")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Provincia> resultado = null;
		
		try {
			
			resultado = provinciaService.findAll();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Provincia>>(resultado, HttpStatus.OK);
		
		
	}
	
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/provincia/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Provincia provincia = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			provincia = provinciaService.findById(id);
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		
		if(provincia == null) {
			response.put("mensaje", "La provincia ID: " + id.toString() + " no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Provincia>(provincia,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PostMapping("/provincia")
	public ResponseEntity<?> create(@RequestBody Provincia provincia){
		
		Provincia  provinciaNew=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			provinciaNew = provinciaService.save(provincia);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear la provincia en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La provincia: " + provinciaNew.getNombre() + " ha sido creada con éxito");
		response.put("provincia", provinciaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PutMapping("/provincia")
	public ResponseEntity<?> update(@RequestBody Provincia provincia){
		
		Long id = provincia.getId();
		Provincia provinciaActual = provinciaService.findById(id);//El que está actualmente en la BBDD
		Provincia provinciaUpdated=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(provinciaActual==null) {
			response.put("mensaje", "Error: no se pudo editar la provincia con Id: ".concat(id.toString()).concat(" no existe en la BBDD"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			provinciaUpdated = provinciaService.save(provincia);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la provincia en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","La provincia ha sido actualizada con éxito");
		response.put("provincia", provinciaUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@DeleteMapping("/provincia/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			provinciaService.deleteById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la provincia en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		
		response.put("mensaje", "La provincia ha sido eliminada con éxito de la BBDD");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}


}
