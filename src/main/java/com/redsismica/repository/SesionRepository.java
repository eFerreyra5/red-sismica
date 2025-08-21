package com.redsismica.repository;

import com.redsismica.model.Sesion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionRepository extends JpaRepository<Sesion, Long> {

    Optional<Sesion> findByFechaHoraFinIsNull();
}