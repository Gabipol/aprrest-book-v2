package com.company.books.backend.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Comentarios
 * 
 * 1) Serializable: que un objeto sea convertido en una secuencia de bytes,
 * lo que facilita su almacenamiento en archivos o transmisión a través de redes. Con
 * respecto a los beans se recomienda que sean serializables (esto no es obligatorio, pero se
 * recomienda paragarantizar su portabilidad).
 *
 */


@Entity //Esta clase va a persistir en un abase de datos (se mapea con una BBDD)
@Table(name="categorias") //Se indica el nombre de la tabla en la BBDD al iniciar este proyecto

public class Categoria implements Serializable{

	private static final long serialVersionUID = -2164553723990982332L;
	
	public Categoria() {
		super();
	}
	
	public Categoria(Long id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	//Campos de la tabla categorias
	@Id //Indica que el campo ID será el ID de la tabla categoria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Se indica que el id se conformará de forma automatica y secuencia
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	//Metodos de la clase (getters & setters)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
