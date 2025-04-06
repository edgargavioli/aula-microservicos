package com.edgar.sala.infrastruture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.sala.application.dto.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    
}
