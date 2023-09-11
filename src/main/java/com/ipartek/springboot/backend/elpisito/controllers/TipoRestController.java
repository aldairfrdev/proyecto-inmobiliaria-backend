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

import com.ipartek.springboot.backend.elpisito.models.entity.Tipo;
import com.ipartek.springboot.backend.elpisito.models.services.ITipoService;

@RestController
@RequestMapping("/api")
public class TipoRestController {
	
	@Autowired
	private ITipoService tipoService;
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/tipos")
	public ResponseEntity<?> findAll(){
		
		Map<String, Object> response = new HashMap<>();
		List<Tipo> resultado = null;
		
		try {
			
			resultado = tipoService.findAll();
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la petición en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		return new ResponseEntity<List<Tipo>>(resultado, HttpStatus.OK);
		
		
	}
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@GetMapping("/tipo/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		
		Tipo tipo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			tipo = tipoService.findById(id);
			
			
		}catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar la consulta en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
		
		
		if(tipo == null) {
			response.put("mensaje", "El tipo ID: " + id.toString() + " no existe en la BBDD");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<Tipo>(tipo,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PostMapping("/tipo")
	public ResponseEntity<?> create(@RequestBody Tipo tipo){
		
		Tipo  tipoNew=null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoNew = tipoService.save(tipo);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al crear el tipo en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El Tipo: " + tipoNew.getNombre() + " ha sido creado con éxito");
		response.put("tipo", tipoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	//@CrossOrigin(origins= {"http://localhost:4200"})
	@PutMapping("/tipo")
	public ResponseEntity<?> update(@RequestBody Tipo tipo){
		
		Long id = tipo.getId();
		Tipo tipoActual = tipoService.findById(id);//El que está actualmente en la BBDD
		Tipo tipoUpdated=null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(tipoActual==null) {
			response.put("mensaje", "Error: no se pudo editar el tipo con Id: ".concat(id.toString()).concat(" no existe en la BBDD"));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			tipoUpdated = tipoService.save(tipo);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el tipo en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		response.put("mensaje","El tipo ha sido actualizado con éxito");
		response.put("tipo", tipoUpdated);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
		
		
	
	}
	
	
	
	
	@DeleteMapping("/tipo/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoService.deleteById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el tipo en la BBDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
		}
		
		response.put("mensaje", "El tipo ha sido eliminado con éxito de la BBDD");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	
	}

}
