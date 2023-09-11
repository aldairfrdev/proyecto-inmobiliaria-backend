package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="imagenes")
public class Imagen implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 4891274750789788380L;

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) //IDENTITY ES UN INCREMENTAL PARA MY SQL
	@Column
	private Long id;
	
	@Column
	private String nombre; //272636838393903003098.jpg
	
	//EJEMPLOS DE NOTACIONES
	//@Column(nullable=false) esta notación sirve para que la columna no pueda ser nula
	//@Column(unique=true)
	
	@Column  //(columnDefinition = "integer default 1")//Esta notación sirve para que esta columna en la BBDD se cree como default con valor 1 de tipo int
	private Integer activo=1; // Si la imagen está activa(no está "eliminado")
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="inmueble")
	private Inmueble inmueble;
	
	
	

}
