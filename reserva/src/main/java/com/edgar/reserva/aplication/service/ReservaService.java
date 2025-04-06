package com.edgar.reserva.aplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.reserva.aplication.dto.Reserva;
import com.edgar.reserva.infrastruture.kafka.ReservaConsumer;
import com.edgar.reserva.infrastruture.kafka.ReservaProducer;
import com.edgar.reserva.infrastruture.repository.ReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaProducer reservaProducer;

    @Autowired
    private ReservaConsumer reservaConsumer;

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id).orElse(null);
    }

    public Reserva createReserva(Reserva reserva) {
        reservaProducer.verificarUsuario(reserva.getUsuario_id());
        reservaProducer.verificarSala(reserva.getSala_id());

        long timeout = 5000;
        long startTime = System.currentTimeMillis();

        while (!reservaConsumer.isUsuarioExiste() || !reservaConsumer.isSalaExiste()) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Timeout ao verificar usuário ou sala. Verifique se os IDs são válidos.");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread interrompida durante a verificação de usuário e sala.", e);
            }
        }
        if (!reservaConsumer.isUsuarioExiste()) {
            throw new RuntimeException("Usuário não existe");
        }

        if (!reservaConsumer.isSalaExiste()) {
            throw new RuntimeException("Sala não existe");
        }

        return reservaRepository.save(reserva);
    }
}
