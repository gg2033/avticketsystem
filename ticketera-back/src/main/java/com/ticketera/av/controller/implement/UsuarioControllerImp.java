package com.ticketera.av.controller.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketera.av.controller.UsuarioController;
import com.ticketera.av.dtos.UsuarioDTO;
import com.ticketera.av.jsons.UsuarioRest;
import com.ticketera.av.services.UsuarioService;
import com.ticketera.av.util.Constants;
import com.ticketera.av.util.ResponseHandler;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/usuario")

public class UsuarioControllerImp implements UsuarioController {


    @Autowired
    UsuarioService usuarioService;
	public static final ModelMapper modelMapper = new ModelMapper();

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UsuarioRest usuario) {
        return usuarioService.save(modelMapper.map(usuario, UsuarioDTO.class));
    }

    @Override
    @GetMapping
    public List<UsuarioRest> findAll() {
        return usuarioService.findAll().stream().map(userDto -> modelMapper.map(userDto, UsuarioRest.class)).collect(Collectors.toList());
    }

    @Override
    @PostMapping("/validateUser")
    public ResponseEntity<?> validateUser(@RequestBody UsuarioRest usuarioRest) {
        Optional<UsuarioDTO>usuario=usuarioService.validateUser(usuarioRest.getEmail(),usuarioRest.getPassword());
        if(!usuario.isEmpty()) {
        	   return ResponseHandler.generateResponse("ok", HttpStatus.OK, modelMapper.map(usuario.get(), UsuarioRest.class));
        }
        else {
        	 return ResponseHandler.generateResponse("Password o mail Invalido", HttpStatus.NOT_FOUND, null);
        }
    }
    @Override
    @PostMapping("/forgotPassword")
	public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        return usuarioService.forgotPassword(email);
    }
    
    @Override
    @PutMapping("/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody UsuarioRest usuarioRest) {
        return usuarioService.updatePassword(modelMapper.map(usuarioRest, UsuarioDTO.class));
    }


}
