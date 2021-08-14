package com.ticketera.av.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpresaRest {
	

	@JsonProperty(value="idEmpresa")
	private Long idEmpresa;
	@JsonProperty("nombre")
	private String nombre;
	@JsonProperty("razonSocial")
	private String razonSocial;
	@JsonProperty("cuit")
	private long cuit;

}
