package com.ticketera.av.dtos;

import java.util.Date;

import lombok.Data;
@Data
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String password;
    private String temporalyPassword;
    
    private String email;
    private Date fechaAlta;
    private EmpresaDTO empresa;
    private boolean passwordProvisoria;
//    private String idEmpresa;
    private Date fechaBaja;
}
