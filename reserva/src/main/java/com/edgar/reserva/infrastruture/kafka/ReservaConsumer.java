package com.edgar.reserva.infrastruture.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReservaConsumer {
    private boolean usuarioExiste = false;
    private boolean salaExiste = false;

    @KafkaListener(topics = "usuario-verificado", groupId = "reserva-group")
    public void consumirRespostaUsuario(String mensagem) {
        usuarioExiste = Boolean.parseBoolean(mensagem);
    }

    @KafkaListener(topics = "sala-verificada", groupId = "reserva-group")
    public void consumirRespostaSala(String mensagem) {
        salaExiste = Boolean.parseBoolean(mensagem);
    }

    public boolean isUsuarioExiste() {
        return usuarioExiste;
    }

    public boolean isSalaExiste() {
        return salaExiste;
    }
}
