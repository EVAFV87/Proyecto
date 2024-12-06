package com.evastur.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "ropa")

public class Ropa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "proveedor")
	private String proveedor;
	
	@Column(columnDefinition = "TEXT")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@OneToMany(mappedBy = "ropa")
	private List<Prenda> prendas = new ArrayList<>();

	public Ropa() {
	}

	public Ropa(String nombre, Categoria categoria, String proveedor) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.proveedor = proveedor;
	}
	
	public Ropa(Long id, String nombre, Categoria categoria, String proveedor, List<Prenda> prendas) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.proveedor = proveedor;
		this.prendas = prendas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public List<Prenda> getPrendas() {
		return prendas;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Ropa [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", proveedor=" + proveedor
				+ ", prendas=" + prendas + "]";
	}
}