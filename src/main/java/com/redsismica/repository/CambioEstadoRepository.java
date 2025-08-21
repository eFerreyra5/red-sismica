package com.redsismica.repository;

import com.redsismica.model.CambioEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CambioEstadoRepository extends JpaRepository<CambioEstado, Long> {
    
}