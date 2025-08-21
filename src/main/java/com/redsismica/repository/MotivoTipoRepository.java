package com.redsismica.repository;

import com.redsismica.model.MotivoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoTipoRepository extends JpaRepository<MotivoTipo, Long> {
}