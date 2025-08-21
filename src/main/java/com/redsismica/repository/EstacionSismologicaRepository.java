package com.redsismica.repository;

import com.redsismica.model.EstacionSismologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstacionSismologicaRepository extends JpaRepository<EstacionSismologica, Long> {
    Optional<EstacionSismologica> findByNombre(String nombre);
}