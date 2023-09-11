package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="poblaciones")
public class Poblacion implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -2901928667362501212L;

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) //IDENTITY ES UN INCREMENTAL PARA MY SQL
	@Column
	private Long id;
	
	@Column
	private String nombre;//ALGORTA, ERANDIO, BILBAO...
	
	@Column  //(columnDefinition = "integer default 1")
	private Integer activo=1; // Si el tipo está activo (no está "eliminado")
	
	@JsonIgnore
	@OneToMany(mappedBy = "poblacion", cascade=CascadeType.ALL)
	private Set<Inmueble> inmuebles;
	
	@JsonIgnoreProperties
	@ManyToOne
	@JoinColumn(name="provincia")
	private Provincia provincia;

	
	
	

}
