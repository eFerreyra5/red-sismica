package com.redsismica.repository;

import com.redsismica.model.OrdenDeInspeccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeInspeccionRepository extends JpaRepository<OrdenDeInspeccion, Long> {    
}