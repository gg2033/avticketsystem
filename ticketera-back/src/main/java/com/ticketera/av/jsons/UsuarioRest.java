package com.ticketera.av.jsons;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRest {

	

	@JsonProperty("id")
    private Long id;
	@JsonProperty("nombre")
    private String nombre;
	@JsonProperty(access = Access.WRITE_ONLY, required = false)
    private String password;
	@JsonProperty(access = Access.WRITE_ONLY, required = false)
	private String temporalyPassword;
	@JsonIgnore
	private Date fechaAlta;
	@JsonIgnore
	private Date fechaBaja;
	@JsonProperty("email")
    private String email;
    @JsonProperty("empresa")
    private EmpresaRest empresa;
    
    @JsonProperty("idEmpresa")
    private String idEmpresa;
    
    @JsonProperty("isProvisoria")
    private boolean passwordProvisoria;
    
    @JsonProperty(access = Access.READ_ONLY, required = false)
    private String jwt;

}