package com.ticketera.av.controller.implement;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketera.av.dtos.UsuarioDTO;
import com.ticketera.av.dtos.ValidateUserDTO;
import com.ticketera.av.jsons.UsuarioRest;
import com.ticketera.av.services.UsuarioService;
import com.ticketera.av.util.ResponseHandler;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	UsuarioService usuarioService;

	public static final ModelMapper modelMapper = new ModelMapper();
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody ValidateUserDTO validateUserDTO) {
		Optional<UsuarioDTO> user = usuarioService.validateUser(validateUserDTO.getUser(), validateUserDTO.getPassword());
		if(user.isPresent()) {
			UsuarioRest userRest = modelMapper.map(user, UsuarioRest.class);
			userRest.setJwt(createJwt());
			return ResponseHandler.generateResponse("", HttpStatus.OK, userRest );
		}else {
			return ResponseHandler.generateResponse("Usuario o contraseña invalidos", HttpStatus.NOT_FOUND, null);
		}
	}
	
	private String createJwt() {
		return "$token";
	};

}
