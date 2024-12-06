package com.evastur.backend.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evastur.backend.dtos.CategoriaDto;
import com.evastur.backend.entities.Categoria;
import com.evastur.backend.repositories.CategoriaRepository;

/**
 * 
 * Define los endpoints del servicio Rest que incluye las operaciones relacionadas con categorias.
 */
@RestController
@RequestMapping("/api/v1")
public class CategoriaController {

	/**
	 * Objeto para mostrar mensajes en el log de la aplicación.
	 */
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	/**
	 * Repositorio usado para las operaciones que implican acceso a la tabla categorias de la bd.
	 */
	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * Permite obtener todas las categorias almacenadas.
	 * @return Listado de categorias (Se devuelven dentro del dto CategoriaDto).
	 */
	@GetMapping("/categoria")
	public List<CategoriaDto> obtenerTodasLasCategorias() {
		log.info("Recuperando lista de categorias.");
		return categoriaRepository.findAll()
				.stream()
				.map(CategoriaDto::new)
				.toList();
	}

	@PostMapping("/categoria/nueva")
	public Categoria agregarCategoria(@RequestBody Categoria Categoria) {
		return categoriaRepository.save(Categoria);
	}

	@GetMapping("/categoria/{id}")
	public Categoria obtenerCategoriaPorId(@PathVariable(name = "id") Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/categoria/{id}")
	public void eliminarCategoria(@PathVariable(name = "id") Long id) {
		categoriaRepository.deleteById(id);
	}
}