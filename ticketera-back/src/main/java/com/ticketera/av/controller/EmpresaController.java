package com.ticketera.av.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ticketera.av.jsons.EmpresaRest;

public interface EmpresaController {
	public ResponseEntity<?> getEmpresaById(Long id);
	public ResponseEntity<Object> save(EmpresaRest emrpesa) throws Exception;
	public List	<?> findAll();
}
