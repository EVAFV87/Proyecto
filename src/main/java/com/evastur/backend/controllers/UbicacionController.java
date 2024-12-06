package com.evastur.backend.controllers;

import com.evastur.backend.entities.Ubicacion;
import com.evastur.backend.repositories.UbicacionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UbicacionController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UbicacionController.class);
	
	@Autowired
	private UbicacionRepository ubicacionRepository;

	@GetMapping("/ubicacion")
	public List<Ubicacion> obtenerTodasLasUbicaciones() {
		return ubicacionRepository.findAll();
	}

	@PostMapping("/ubicacion/nueva")
	public Ubicacion agregarUbicacion(@RequestBody Ubicacion Ubicacion) {
		return ubicacionRepository.save(Ubicacion);
	}

	@PatchMapping("/ubicacion/editar/{id}")
	public Ubicacion editarUbicacion(@PathVariable(name = "id") Long id, @RequestBody Ubicacion Ubicacion) {
		Optional<Ubicacion> UbicacionExistente = ubicacionRepository.findById(id);
		if (UbicacionExistente.isPresent()) {
			Ubicacion UbicacionActual = UbicacionExistente.get();
			UbicacionActual.setPasillo(Ubicacion.getPasillo());
			UbicacionActual.setEstante(Ubicacion.getEstante());
			return ubicacionRepository.save(UbicacionActual);
		}
		return null;
	}

	@GetMapping("/ubicacion/{id}")
	public Ubicacion obtenerUbicacionPorId(@PathVariable(name = "id") Long id) {
		return ubicacionRepository.findById(id).orElse(null);
	}

	@DeleteMapping("/ubicacion/{id}")
	public void eliminarUbicacion(@PathVariable(name = "id") Long id) {
		ubicacionRepository.deleteById(id);
	}
};