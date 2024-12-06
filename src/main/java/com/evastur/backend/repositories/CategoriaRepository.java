package com.evastur.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evastur.backend.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}