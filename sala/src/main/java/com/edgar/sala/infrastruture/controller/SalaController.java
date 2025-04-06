package com.edgar.sala.infrastruture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.sala.application.dto.Sala;
import com.edgar.sala.application.service.SalaService;

@RestController
@RequestMapping("/salas")
public class SalaController {
    @Autowired
    private SalaService salaService;
    
    @GetMapping
    public List<Sala> getSalas(){
        return salaService.getAllSalas();
    }

    @GetMapping("/{id}")
    public Sala getSalaById(@PathVariable Long id) {
        return salaService.getSalaById(id);
    }

    @PostMapping
    public Sala createSala(@RequestBody Sala sala) {
        return salaService.createSala(sala);
    }
}
