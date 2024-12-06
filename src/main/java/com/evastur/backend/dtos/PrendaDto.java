package com.evastur.backend.dtos;

import com.evastur.backend.entities.Prenda;

/**
 * Permite enviar la información asociada a una prenda que se muestra dentro de una ropa.
 */
public class PrendaDto {
	/**
	 * Identificador de la prendar
	 */
	private Long id;
	/**
	 * Color de la prenda
	 */
	private String color;
	/**
	 * Talla de la ropa mostrada.
	 */
	private String talla;
	/**
	 * Cantidad de stock disponible en el almacen de la prenda
	 */
	private Long stock;
	/**
	 * Pasillo en el que se encuentra la prenda.
	 */
	private Long pasillo;
	/**
	 * Estante en el que se encuentra la prenda.
	 */
	private Long estante;

	/**
	 * Crea un dto a partir de los datos de una prenda. Para rellenar la información del dto se usan tambien
	 * objetos asociados a la prenda, como la ubicación.
	 * @param prenda
	 */
	public PrendaDto(Prenda prenda) {
		this.id = prenda.getId();
		this.color = prenda.getColor();
		this.talla = prenda.getTalla();
		this.stock = prenda.getStock();
		if (prenda.getUbicacion() != null) {
			this.pasillo = prenda.getUbicacion().getPasillo();
			this.estante = prenda.getUbicacion().getEstante();
		}
		else {
			this.pasillo = 0L;
			this.estante = 0L;
		}
	}

	public PrendaDto(Long id, String color, String talla, Long stock, Long pasillo, Long estante) {
		this.id = id;
		this.color = color;
		this.talla = talla;
		this.stock = stock;
		this.pasillo = pasillo;
		this.estante = estante;
	}

	public Long getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public String getTalla() {
		return talla;
	}

	public Long getStock() {
		return stock;
	}

	public Long getPasillo() {
		return pasillo;
	}

	public Long getEstante() {
		return estante;
	}

}
