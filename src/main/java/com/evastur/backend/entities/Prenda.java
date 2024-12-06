package com.evastur.backend.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "prenda")

public class Prenda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "color")
	private String color;

	@Column(name = "talla")
	private String talla;

	@Column(name = "stock")
	private Long stock;

	@ManyToOne
	@JoinColumn(name = "idRopa")
	private Ropa ropa;

	@OneToOne(mappedBy = "prenda", cascade = CascadeType.ALL)
	private Ubicacion ubicacion;

	public Prenda() {
	}

	public Prenda(Long id, String color, String talla, Long stock, Ropa ropa, Ubicacion ubicacion) {
		this.id = id;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
		this.ropa = ropa;
		this.ubicacion = ubicacion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Ropa getRopa() {
		return ropa;
	}

	public void setRopa(Ropa ropa) {
		this.ropa = ropa;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Prenda [id=" + id + ", color=" + color + ", talla=" + talla + ", stock=" + stock + ", ropa=" + ropa
				+ ", ubicacion=" + ubicacion + "]";
	}

	public void modificarStock(int inc) {
		this.stock += inc;		
		if (this.stock <0) this.stock = 0L;
	}

}