package com.evastur.backend.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

/**
 * Información de una categoria de ropa.
 */
@Entity
@Table(name = "Categoria")

public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificador unico.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * Nombre de la categoría de ropa
	 */
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Lista de ropa de esta categoria. Se incluye para implementar la asociación en la BD
	 * entre ropa y categoria a traves de JPA.
	 */
	@OneToMany(mappedBy = "categoria")
	private List<Ropa> ropas;

	public Categoria() {
	}

	public Categoria(Long id, String nombre, List<Ropa> ropas) {
		this.id = id;
		this.nombre = nombre;
		this.ropas = ropas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ropa> getRopas() {
		return ropas;
	}

	public void setRopas(List<Ropa> ropas) {
		this.ropas = ropas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", ropas=" + ropas + "]";
	}
}