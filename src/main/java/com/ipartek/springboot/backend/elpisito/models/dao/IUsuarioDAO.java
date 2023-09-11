package com.ipartek.springboot.backend.elpisito.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.elpisito.models.entity.Usuario;


@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{
	
	// JPA Hibernate permite construir métodos personalizados
	// siguiendo unas reglas (Derived Query Methods in Spring)
	// https://www.java4coding.com/contents/spring/springdatajpa/spring-data-jpa-method-naming-conventions
	// https://www.baeldung.com/spring-data-derived-queries
	
	Optional<Usuario>findOneByEmail(String email);

}
