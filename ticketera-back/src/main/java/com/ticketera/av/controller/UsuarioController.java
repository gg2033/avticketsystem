package com.ticketera.av.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ticketera.av.jsons.UsuarioRest;

public interface UsuarioController {
    public ResponseEntity<?> save(UsuarioRest usuario);
    public List<UsuarioRest> findAll();
    public ResponseEntity<?> validateUser(UsuarioRest usuarioRest);
    public ResponseEntity<Object> updatePassword(UsuarioRest usuarioRest);
    public ResponseEntity<?> forgotPassword(String email);
    
}
