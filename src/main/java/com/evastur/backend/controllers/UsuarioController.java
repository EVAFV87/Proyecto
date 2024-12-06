package com.evastur.backend.controllers;

import com.evastur.backend.entities.Usuario;
import com.evastur.backend.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/usuario")
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping("/usuario/nuevo")
	public Usuario agregarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@PatchMapping("/usuario/editar/{id}")
	public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
		if (usuarioExistente.isPresent()) {
			Usuario usuarioActual = usuarioExistente.get();
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setRol(usuario.getRol());
			usuarioActual.setClave(usuario.getClave());
			return usuarioRepository.save(usuarioActual);
		}
		return null;
	}

	@GetMapping("/usuario/{id}")
	public Usuario obtenerUsuarioPorId(@PathVariable Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/usuario/{id}")
	public void eliminarUsuario(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}
};
