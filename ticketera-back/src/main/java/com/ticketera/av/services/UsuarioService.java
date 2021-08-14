package com.ticketera.av.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.ticketera.av.dtos.UsuarioDTO;
import com.ticketera.av.model.Usuario;

public interface UsuarioService {
    public ResponseEntity<?> save(UsuarioDTO usuario);
    public List<Usuario> findAll();
    public Optional<UsuarioDTO> validateUser(String email, String password);
    public ResponseEntity<?> forgotPassword(String email);
    public ResponseEntity<Object> updatePassword(UsuarioDTO usuario);
}
