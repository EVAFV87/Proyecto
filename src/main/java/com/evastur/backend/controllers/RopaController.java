package com.evastur.backend.controllers;

import com.evastur.backend.dtos.NuevaRopaDto;
import com.evastur.backend.dtos.RopaDto;
import com.evastur.backend.entities.Categoria;
import com.evastur.backend.entities.Ropa;
import com.evastur.backend.repositories.CategoriaRepository;
import com.evastur.backend.repositories.RopaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Define los endpoints del servicio Rest que incluye las operaciones relacionadas con ropa.
 */
@RestController
@RequestMapping("/api/v1")
public class RopaController {

	/**
	 * Objeto para mostrar mensajes en el log de la aplicación.
	 */
	private static final Logger log = LoggerFactory.getLogger(RopaController.class);

	/**
	 * Repositorio usado para las operaciones que implican acceso a la tabla ropas de la bd.
	 */
	@Autowired
	private RopaRepository ropaRepository;

	/**
	 * Repositorio usado para las operaciones que implican acceso a la tabla categorias de la bd.
	 */
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping("/ropa")
	public List<Ropa> obtenerTodasLasUbicaciones() {
		return ropaRepository.findAll();
	}

	@PostMapping("/ropa/nueva")
	public RopaDto agregarRopa(@RequestBody NuevaRopaDto dto) {
		
		Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No existe la categoria " +  dto.getCategoriaId()));
		
		Ropa ropa = new Ropa(dto.getNombre(), categoria, dto.getProveedor());
		ropa = ropaRepository.save(ropa);
		return new RopaDto(ropa);
	}

	@PatchMapping("/ropa/editar/{id}")
	public Ropa editarRopa(@PathVariable Long id, @RequestBody Ropa Ropa) {
		Optional<Ropa> RopaExistente = ropaRepository.findById(id);
		if (RopaExistente.isPresent()) {
			Ropa RopaActual = RopaExistente.get();
			RopaActual.setCategoria(Ropa.getCategoria());
			RopaActual.setProveedor(Ropa.getProveedor());
			return ropaRepository.save(RopaActual);
		}
		return null;
	}

	/**
	 * Permite obtener la ropa asociada al identificador pasado.
	 * @param id Identificador de la ropa a retornar.
	 * @return Información de la ropa (Se devuelven dentro del dto RopaDto).
	 */
	@GetMapping("/ropa/{id}")
	public RopaDto obtenerRopaPorId(@PathVariable("id") Long id) {
		log.info("Ver ropa con id {}", id);
		Ropa ropa = ropaRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return new RopaDto(ropa);
	}

	@DeleteMapping("/ropa/{id}")
	public void eliminarRopa(@PathVariable Long id) {
		ropaRepository.deleteById(id);
	}

	/**
	 * Permite obtener toda la ropa asociada a una categoria concreta.
	 * @param id Identificador de la categoria a la que pertenecerá la ropa.
	 * @return Listado de ropa (Se devuelven dentro del dto RopaDto).
	 */
	@GetMapping("/categoria/{id}/ropa")
	public List<RopaDto> obtenerRopaPorCategoria(@PathVariable("id") Long id) {
		log.info("Listado de ropa");
		List<Ropa> ropas = ropaRepository.findByCategoriaId(id);
		ArrayList<RopaDto> result = new ArrayList<>();
		for (Ropa ropa : ropas) {
			result.add(new RopaDto(ropa));
		}
		return result;
	}
};