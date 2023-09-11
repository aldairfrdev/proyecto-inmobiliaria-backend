package com.ipartek.springboot.backend.elpisito.models.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;


@Repository
public interface IImagenDAO extends CrudRepository<Imagen, Long> {

}
