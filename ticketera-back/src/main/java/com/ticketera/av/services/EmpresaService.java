package com.ticketera.av.services;

import java.util.List;
import java.util.Optional;

import com.ticketera.av.dtos.EmpresaDTO;

public interface EmpresaService {	
	public EmpresaDTO getEmpresaById(Long id) throws Exception;
	Optional<EmpresaDTO> findByCuit(Long cuit) throws Exception;
	public EmpresaDTO save(EmpresaDTO empresa);
	List<EmpresaDTO> findAll();
}
