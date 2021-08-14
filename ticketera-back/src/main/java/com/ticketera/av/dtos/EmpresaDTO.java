package com.ticketera.av.dtos;

import lombok.Data;

@Data
public class EmpresaDTO {
	private String idEmpresa;
	private String nombreEmpresa;
	private String razonSocial;
	private long cuit;
}
