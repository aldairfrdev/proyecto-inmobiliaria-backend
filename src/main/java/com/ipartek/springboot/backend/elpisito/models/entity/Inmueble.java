package com.ipartek.springboot.backend.elpisito.models.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

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
@Table(name="inmuebles")
public class Inmueble implements Serializable {
	
	@Serial
	private static final long serialVersionUID = 7213886250683380290L;

	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY) //IDENTITY ES UN INCREMENTAL PARA MY SQL
	@Column
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="tipo")
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name="poblacion")
	private Poblacion poblacion;
	
	@Column
	private String via;//CALLE, PLAZA, CARRETERA
	
	@Column
	private String titular;//Maravilloso piso en Bilbao...
	
	@Column(name= "nombre_via")
	private String nombreVia;//Rue del Percebe
	
	@Column
	private String numero;
	
	@Column
	private String planta;
	
	@Column
	private String puerta; //A,B...
	
	@Column
	private String cp;
	
	@Column
	private String apertura; //EXTERIOR, INTERIOR
	
	@Column
	private String orientacion; //NORTE, SUR
	
	@Column(name="superficie_util")
	private String superficieUtil;
	
	@Column(name="superficie_construida")
	private String superficieConstruida;
	
	@Column
	private Integer precio;
	
	@Column(name="numero_habitaciones")
	private Integer nHabitaciones;
	
	@Column(name="numero_banhos")
	private Integer nBanhos;
	
	@Column (length = 3500)
	private String descripcion; 
	
	@Column(name="tipo_calefaccion")
	private String tipoCalefaccion;
	
	@Column
	private Integer amueblado;// 0 no amueblado, 1 amueblado
	
	@Column(name="numero_balcones")
	private Integer nBalcones;
	
	@Column(name="plazas_garaje")
	private Integer plazasGaraje; 
	
	@Column
	private Integer piscina; // 0 no piscina, 1 piscina
	
	@Column
	private Integer trastero; // 0 no trastero, 1 trastero
	
	@Column
	private Integer ascensor; // 0 no ascensor, 1 ascensor
	
	@Column
	private Integer jardin; // 0 no jardin, 1 jardin
	
	@Column
	private Integer tendedero; // 0 no tendedero, 1 tendedero
	
	@Column
	private Integer portada; // 0 no aparece en portada, 1 aparece en portada
	
	@Column  //(columnDefinition = "integer default 1")
	private Integer activo=1; // Si el inmueble está activo (no está "eliminado")
	
	
	@OneToMany(mappedBy = "inmueble")
	private Set<Imagen> imagenes;
	

}
