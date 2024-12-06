package com.evastur.backend.dtos;

import com.evastur.backend.entities.Categoria;

/**
 * Almacena la información de una categoría, principalmente para poder enviarla al frontend.
 */
public class CategoriaDto {

	/**
	 * identificador de la categoría.
	 */
	private Long id;
	/**
	 * Nombre de la categoría.
	 */
	private String nombre;

	/**
	 * permite crear el dto a partir de la información almacenada en el objeto categoria.
	 * @param categoria
	 */
	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nombre = categoria.getNombre();
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}