package com.edgar.usuario.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edgar.usuario.application.dto.Usuario;
import com.edgar.usuario.infrastruture.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public Usuario createUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return usuario;
    }
    public Usuario getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));    
    }
    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
}
