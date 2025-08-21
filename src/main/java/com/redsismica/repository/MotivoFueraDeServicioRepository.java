package com.redsismica.repository;

import com.redsismica.model.MotivoFueraDeServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoFueraDeServicioRepository extends JpaRepository<MotivoFueraDeServicio, Long> {
}