package com.edgar.sala.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.sala.application.dto.Sala;
import com.edgar.sala.infrastruture.repository.SalaRepository;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    public Sala createSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public Sala getSalaById(Long id) {
        return salaRepository.findById(id).orElse(null);
    }
    
    public List<Sala> getAllSalas() {
        return salaRepository.findAll();
    }
}
