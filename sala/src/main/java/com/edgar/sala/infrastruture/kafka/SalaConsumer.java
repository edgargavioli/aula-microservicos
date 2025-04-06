package com.edgar.sala.infrastruture.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.edgar.sala.infrastruture.repository.SalaRepository;

@Service
public class SalaConsumer {
    private static final String TOPIC_RESPOSTA = "sala-verificada";

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "verificar-sala", groupId = "sala-group")
    public void verificarSala(String salaId) {
        boolean existe = salaRepository.existsById(Long.parseLong(salaId));
        kafkaTemplate.send(TOPIC_RESPOSTA, String.valueOf(existe));
    }
}
