package com.edgar.reserva.infrastruture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.reserva.aplication.dto.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
    
}
