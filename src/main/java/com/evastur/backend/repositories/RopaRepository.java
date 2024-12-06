package com.evastur.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evastur.backend.entities.Ropa;

public interface RopaRepository extends JpaRepository<Ropa, Long> {
	
	List<Ropa> findByCategoriaId(Long id);
}