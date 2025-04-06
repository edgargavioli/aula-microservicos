package com.edgar.reserva.infrastruture.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReservaProducer {
    private static final String TOPIC_USUARIO = "verificar-usuario";
    private static final String TOPIC_SALA = "verificar-sala";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void verificarUsuario(Long usuarioId) {
        kafkaTemplate.send(TOPIC_USUARIO, usuarioId.toString());
    }

    public void verificarSala(Long salaId) {
        kafkaTemplate.send(TOPIC_SALA, salaId.toString());
    }
}
