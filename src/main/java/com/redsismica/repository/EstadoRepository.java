package com.redsismica.repository;

import com.redsismica.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    List<Estado> findByAmbito(String ambito);
    List<Estado> findByNombreEstado(String nombreEstado);
}