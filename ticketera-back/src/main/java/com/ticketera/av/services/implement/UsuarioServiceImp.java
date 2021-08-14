package com.ticketera.av.services.implement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ticketera.av.dtos.UsuarioDTO;
import com.ticketera.av.jsons.EmpresaRest;
import com.ticketera.av.jsons.UsuarioRest;
import com.ticketera.av.model.Usuario;
import com.ticketera.av.repository.UsuarioRepositoy;
import com.ticketera.av.services.UsuarioService;
import com.ticketera.av.util.MailService;
import com.ticketera.av.util.ResponseHandler;

@Service
public class UsuarioServiceImp implements UsuarioService {
    public static String messageOk = "Se ha guardado correctamente";
    public static String messageMailUnique = "El mail ya se encuentra registrado";
    public static String messageInternalError = "Hubo un error desconocido";
    public static String messagePasswordError = "El password es incorrecto";
    public static String messageMailError = "El mail no se encuentra registrado";

    public static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    UsuarioRepositoy usuarioRepositoy;
    
    @Autowired
    MailService mailService;

    @Override
    public ResponseEntity<?> save(UsuarioDTO usuario) {


        try{
           usuario.setPassword("123456");
            usuario.setFechaAlta(new Date());
            usuario.setFechaBaja(null);
            usuario.setPasswordProvisoria(true);
            Usuario user = usuarioRepositoy.save(modelMapper.map(usuario, Usuario.class));
            mailService.sendMail("Para validar el Email por favor ingresa al siguiente enlace : ", usuario.getEmail());
            return ResponseHandler.generateResponse(messageOk, HttpStatus.OK, modelMapper.map(user, UsuarioRest.class));
        }catch(DataIntegrityViolationException e){
            System.out.println(e.getMessage());
            if(e.getMostSpecificCause().getMessage().contains("usuario.Mail_UNIQUE")){
                return ResponseHandler.generateResponse(messageMailUnique, HttpStatus.BAD_REQUEST, null);
            }else{
                return ResponseHandler.generateResponse(messageInternalError, HttpStatus.INTERNAL_SERVER_ERROR, null);

            }
        }
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepositoy.findAll();
    }

    @Override
    public Optional<UsuarioDTO> validateUser(String email, String password){
//       try{
    		Optional<UsuarioDTO> usuario=Optional.empty();
            Optional<Usuario> user = usuarioRepositoy.findByEmail(email);
            if(!user.isEmpty() && user.get().getPassword().equals(password)){
            	usuario = Optional.of(modelMapper.map(user.get(), UsuarioDTO.class));
//                return ResponseHandler.generateResponse("ok", HttpStatus.OK, modelMapper.map(usuario, null).get());
            }
            return usuario;
//           return ResponseHandler.generateResponse(messagePasswordError, HttpStatus.NOT_FOUND, null);
//        }catch(Exception e){
//           return ResponseHandler.generateResponse(messageMailError, HttpStatus.NOT_FOUND, null);

//        }
    }


    @Override
    public ResponseEntity<Object> updatePassword(UsuarioDTO usuario) {
            Optional<Usuario> user = usuarioRepositoy.findByEmail(usuario.getEmail());
            if(user.isPresent() && user.get().getPassword().equals(usuario.getTemporalyPassword())) {
            	
                 usuarioRepositoy.updateContraseña(user.get().getId(), usuario.getPassword());
                 return ResponseHandler.generateResponse("Se actualizo correctamente!", HttpStatus.OK, user);
            }
            else if(user.isPresent() && !user.get().getPassword().equals(usuario.getTemporalyPassword())) {
            	 return ResponseHandler.generateResponse("La password provisoria no fue cargada correctamente", HttpStatus.NOT_FOUND,null);
            }
            else {
            	  return ResponseHandler.generateResponse(messageMailError, HttpStatus.NOT_FOUND,null);
            }
           
    }

	@Override
	public ResponseEntity<?> forgotPassword(String email) {
		 Optional<Usuario> user =usuarioRepositoy.findByEmail(email);
		 if(user.isPresent()) {
			 usuarioRepositoy.updateContraseña(user.get().getId(), "123456");
			 mailService.sendMail("Su nueva password es 123456: ", email);
//			 return ResponseHandler.generateResponse("Se actualizo correctamente!", HttpStatus.OK, user);
			 return ResponseHandler.generateResponse(messageMailError, HttpStatus.OK, modelMapper.map(user, EmpresaRest.class));
		 }else {
//			 return ResponseHandler.generateResponse(messageMailError, HttpStatus.NOT_FOUND, null);
			 return ResponseHandler.generateResponse(messageMailError, HttpStatus.NOT_FOUND, null);
		 }
		
	}

}
