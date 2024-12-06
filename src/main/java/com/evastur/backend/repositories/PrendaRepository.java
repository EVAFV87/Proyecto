package com.evastur.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evastur.backend.entities.Prenda;
import com.evastur.backend.entities.Ropa;

public interface PrendaRepository extends JpaRepository<Prenda, Long> {

	List<Prenda> findByRopaId(Long id);

}