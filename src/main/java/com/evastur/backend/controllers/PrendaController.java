package com.evastur.backend.controllers;

import com.evastur.backend.dtos.CambiaStockDto;
import com.evastur.backend.dtos.PrendaDto;
import com.evastur.backend.entities.Prenda;
import com.evastur.backend.entities.Ropa;
import com.evastur.backend.entities.Ubicacion;
import com.evastur.backend.repositories.PrendaRepository;
import com.evastur.backend.repositories.RopaRepository;
import com.evastur.backend.repositories.UbicacionRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

/**
 * Define los endpoints del servicio Rest que incluye las operaciones relacionadas con prendas.
 */
@RestController
@RequestMapping("/api/v1")
public class PrendaController {

	/**
	 * Objeto para mostrar mensajes en el log de la aplicación.
	 */
	private static final Logger log = LoggerFactory.getLogger(PrendaController.class);

	/**
	 * Repositorio usado para las operaciones que implican acceso a la tabla prendas de la bd.
	 */
	@Autowired
	private PrendaRepository prendaRepository;

	/**
	 * Repositorio usado para las operaciones que implican acceso a la tabla ubicaciones de la bd.
	 */
	@Autowired
	private UbicacionRepository ubicacionRepository;

	@Autowired
	private RopaRepository ropaRepository;

	@GetMapping("/prenda")
	public List<PrendaDto> obtenerTodasLasPrendas() {
		return prendaRepository.findAll().stream().map(PrendaDto::new).toList();
	}

	@PostMapping("/prenda/nueva/{idUbicacion}/{idRopa}")
	@Transactional
	public PrendaDto agregarPrenda(@PathVariable(name = "idUbicacion") Long idUbicacion,
			@PathVariable(name = "idRopa") Long idRopa, @RequestBody Prenda prenda) {

		log.debug("Entrando en agregarPrenda");

		Optional<Ubicacion> optUbicacion = ubicacionRepository.findById(idUbicacion);
		Optional<Ropa> optRopa = ropaRepository.findById(idRopa);
		if (!optUbicacion.isPresent()) {
			log.warn("No existe ubicacion {}", idUbicacion);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else if (!optRopa.isPresent()) {
			log.warn("No existe ropa {}", idRopa);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			prenda = prendaRepository.save(prenda);

			Ubicacion ubicacion = optUbicacion.get();
			ubicacion.setPrenda(prenda);

			Ropa ropa = optRopa.get();
			prenda.setRopa(ropa);

			log.info("Agregada prenda {}", prenda);
			return new PrendaDto(prenda);
		}
	}

	@PatchMapping("/prenda/editar/{id}")
	@Transactional
	public PrendaDto editarPrenda(@PathVariable Long id, @RequestBody Prenda Prenda) {
		Optional<Prenda> PrendaExistente = prendaRepository.findById(id);
		if (PrendaExistente.isPresent()) {
			Prenda PrendaActual = PrendaExistente.get();
			// actualizamos los campos de la Prenda
			PrendaActual.setColor(Prenda.getColor());
			PrendaActual.setStock(Prenda.getStock());
			PrendaActual.setTalla(Prenda.getTalla());
			PrendaActual.setUbicacion(null);
			return new PrendaDto(PrendaActual);
		}
		return null;
	}

	@GetMapping("/prenda/{id}")
	public PrendaDto obtenerPrendaPorId(@PathVariable Long id) {
		return new PrendaDto(prendaRepository.findById(id).orElse(null));
	}

	@DeleteMapping("/prenda/{id}")
	public void eliminarPrenda(@PathVariable Long id) {
		prendaRepository.deleteById(id);
	}

	/**
	 * Permite obtener todas las prendas asociadas a una ropa concreta.
	 * @param id Identificador de la ropa a la que pertenecerán las prendas.
	 * @return Listado de prendas (Se devuelven dentro del dto PrendaDto).
	 */
	@GetMapping("/ropa/{id}/prenda")
	public List<PrendaDto> obtenerPrendaPorRopa(@PathVariable("id") Long id) {
		log.info("Mostrando prendas de {}", id);
		List<Prenda> prendas = prendaRepository.findByRopaId(id);
		ArrayList<PrendaDto> result = new ArrayList<>();
		for (Prenda prenda : prendas) {
			result.add(new PrendaDto(prenda));
		}
		return result;
	}

	/**
	 * Permite incrementar el stock de una prenda concreta.
	 * @param idRopa Identificador de la ropa a la que pertenece la prenda.
	 * @param idPrenda Identificador de la prenda cuyo stock se incrementará.
	 * @param dto Contiene el valor en que se incrementará el stock. Debe ser positivo, si no lo fuese
	 * se pasa a valor absoluto antes de realizar el incremento, para evitar que este método pueda usarse para
	 * decrementar el stock.
	 * @return Listado de prendas de la ropa a la que pertenece la prenda modificada.
	 */
	@PatchMapping("/ropa/{idRopa}/prenda/{idPrenda}/incstock")
	@Transactional
	public List<PrendaDto> incrementarStock(@PathVariable("idRopa") Long idRopa, @PathVariable("idPrenda") Long idPrenda, @RequestBody CambiaStockDto dto) {
		Optional<Prenda> prenda = prendaRepository.findById(idPrenda);
		if (prenda.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		int inc = Math.abs(dto.getInc());
		log.info("Incrementando stock en {}", inc);
		prenda.get().modificarStock(inc);
		List<Prenda> prendas = prendaRepository.findByRopaId(idRopa);
		
		ArrayList<PrendaDto> result = new ArrayList<>();
		for (Prenda p : prendas) {
			result.add(new PrendaDto(p));
		}
		return result;	
	}

	/**
	 * Permite decrementar el stock de una prenda concreta.
	 * @param idRopa Identificador de la ropa a la que pertenece la prenda.
	 * @param idPrenda Identificador de la prenda cuyo stock se incrementará.
	 * @param dto Contiene el valor en que se decrementará el stock. Debe ser positivo, si no lo fuese
	 * se pasa a valor absoluto antes de realizar el decremento, para evitar que este método pueda usarse para
	 * incrementar el stock.
	 * @return Listado de prendas de la ropa a la que pertenece la prenda modificada.
	 */
	@PatchMapping("/ropa/{idRopa}/prenda/{idPrenda}/decstock")
	@Transactional
	public List<PrendaDto> decrementarStock(@PathVariable("idRopa") Long idRopa, @PathVariable("idPrenda") Long idPrenda, @RequestBody CambiaStockDto dto) {
		Optional<Prenda> prenda = prendaRepository.findById(idPrenda);
		if (prenda.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		int dec = Math.abs(dto.getInc());
		log.info("Decrementando stock en {}", dec);
		prenda.get().modificarStock(-dec);
		List<Prenda> prendas = prendaRepository.findByRopaId(idRopa);
		
		ArrayList<PrendaDto> result = new ArrayList<>();
		for (Prenda p : prendas) {
			result.add(new PrendaDto(p));
		}
		return result;	
	}
};