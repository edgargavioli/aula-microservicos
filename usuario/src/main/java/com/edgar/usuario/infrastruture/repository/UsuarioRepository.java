package com.edgar.usuario.infrastruture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgar.usuario.application.dto.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
