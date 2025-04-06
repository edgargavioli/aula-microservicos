package com.edgar.usuario.infrastruture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.usuario.application.dto.Usuario;
import com.edgar.usuario.application.service.UsuarioService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.getAllUsuarios();
            return usuarios;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter usuários: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{email}")
    public Usuario getUsuarioByEmail(@PathParam(value = "email") String email) {
        try {
            Usuario usuario = usuarioService.getUsuarioByEmail(email);
            return usuario;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter usuário por email: " + e.getMessage(), e);
        }
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioCreated = usuarioService.createUsuario(usuario);
            return usuarioCreated;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage(), e);
        }
    }
}
