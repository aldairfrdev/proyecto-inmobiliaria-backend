package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="usuarios")
public class Usuario implements Serializable{

	@Serial
	private static final long serialVersionUID = -5128137720317122129L;
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) //IDENTITY ES UN INCREMENTAL PARA MY SQL
	@Column
	private Long id;
	
	@Column(unique = true)
	private String user;
	
	@Column
	private String password;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String rol = "ROLE_USER";
	
	@Column  //(columnDefinition = "integer default 1")
	private Integer activo=1; // Si el usuario está activo (no está "eliminado")

}
