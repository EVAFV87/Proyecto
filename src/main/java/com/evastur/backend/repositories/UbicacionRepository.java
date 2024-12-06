package com.evastur.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evastur.backend.entities.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

}