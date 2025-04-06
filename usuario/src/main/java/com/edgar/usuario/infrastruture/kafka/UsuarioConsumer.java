package com.edgar.usuario.infrastruture.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.edgar.usuario.infrastruture.repository.UsuarioRepository;

@Service
public class UsuarioConsumer {
    private static final String TOPIC_RESPOSTA = "usuario-verificado";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "verificar-usuario", groupId = "usuario-group")
    public void verificarUsuario(String usuarioId) {
        boolean existe = usuarioRepository.existsById(Long.parseLong(usuarioId));
        kafkaTemplate.send(TOPIC_RESPOSTA, String.valueOf(existe));
    }
}
