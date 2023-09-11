package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="provincias")
public class Provincia implements Serializable{
	
	@Serial
	private static final long serialVersionUID = -7043170071241565050L;


	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) //IDENTITY ES UN INCREMENTAL PARA MY SQL
	@Column
	private Long id;
	
	
	@Column
	private String nombre;//MADRID, BARCELONA, BIZKAIA...
	
	@Column  //(columnDefinition = "integer default 1")
	private Integer activo=1; // Si el tipo está activo (no está "eliminado")
	
	@JsonIgnore
	@OneToMany(mappedBy = "provincia")
	private Set<Poblacion> poblaciones;
	
	

}
