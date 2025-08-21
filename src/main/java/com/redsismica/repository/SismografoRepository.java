package com.redsismica.repository;

import com.redsismica.model.Sismografo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SismografoRepository extends JpaRepository<Sismografo, Long> {
    Optional<Sismografo> findByIdentificadorSismografo(String identificadorSismografo);
    Optional<Sismografo> findByNroSerie(int nroSerie);
}