package com.evastur.backend.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")

public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "rol")
	private String rol;

	@Column(name = "clave")
	private String clave;

	public Usuario() {
	}

	public Usuario(Long id, String nombre, String rol, String clave) {
		this.id = id;
		this.nombre = nombre;
		this.rol = rol;
		this.clave = clave;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", rol=" + rol + ", clave=" + clave + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (rol.equals("ALMACEN")) {
			return List.of(new SimpleGrantedAuthority("ROLE_ALMACEN"));
		}
		else {
			return List.of(new SimpleGrantedAuthority("ROLE_TRABAJADOR"));
		}
	}

	@Override
	public String getPassword() {
		return clave;
	}

	@Override
	public String getUsername() {
		return nombre;
	}

}
