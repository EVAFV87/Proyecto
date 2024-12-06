package com.evastur.backend.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "ubicacion")

public class Ubicacion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "pasillo")
	private Long pasillo;

	@Column(name = "estante")
	private Long estante;

	@OneToOne
	@JoinColumn(name = "idPrenda")
	private Prenda prenda;

	public Ubicacion() {
	}

	public Ubicacion(Long id, Long pasillo, Long estante, Prenda prenda) {
		this.id = id;
		this.pasillo = pasillo;
		this.estante = estante;
		this.prenda = prenda;
	}

	public Long getId() {
		return id;
	}

	public Long getPasillo() {
		return pasillo;
	}

	public void setPasillo(Long pasillo) {
		this.pasillo = pasillo;
	}

	public Long getEstante() {
		return estante;
	}

	public void setEstante(Long estante) {
		this.estante = estante;
	}

	public Prenda getPrenda() {
		return prenda;
	}

	public void setPrenda(Prenda prenda) {
		this.prenda = prenda;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Ubicacion [id=" + id + ", pasillo=" + pasillo + ", estante=" + estante + ", prenda="
				+ prenda + "]";
	}

}
