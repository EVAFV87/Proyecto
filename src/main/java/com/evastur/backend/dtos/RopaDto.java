package com.evastur.backend.dtos;

import com.evastur.backend.entities.Ropa;

/**
 * Permite recoger la información de un objeto ropa que se envia al frontend.
 */
public class RopaDto {

	/**
	 * Identificador unico
	 */
	private Long id;
	/**
	 * Nombre por el que se reconoce este tipo de prenda
	 */
	private String nombre;
	/**
	 * Nombre del fabricante de este tipo de ropa.
	 */
	private String proveedor;
	/**
	 * URL que apunta al fichero de imagen asociado a este tipo de ropa.
	 */
	private String imagen;
	/**
	 * Características detalladas de este tipo de ropa.
	 */
	private String descripcion;

	/**
	 * Permite crear un dto de ropa a partir de la información incluida en un objeto Ropa.
	 * @param ropa Objeto del que se extraerá la información.
	 */
	public RopaDto(Ropa ropa) {
		this.id = ropa.getId();
		this.imagen = "http://localhost:8080/imagenes/" + ropa.getId() + ".jpg";
		this.nombre = ropa.getNombre();
		this.proveedor = ropa.getProveedor();
		this.descripcion = ropa.getDescripcion();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getImagen() {
		return imagen;
	}
	
	public String getProveedor() {
		return proveedor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}