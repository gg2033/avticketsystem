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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketera.av.controller.EmpresaController;
import com.ticketera.av.dtos.EmpresaDTO;
import com.ticketera.av.jsons.EmpresaRest;
import com.ticketera.av.model.Empresa;
import com.ticketera.av.services.EmpresaService;
import com.ticketera.av.util.ResponseHandler;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/empresa")
public class EmpresaControllerImp implements EmpresaController {
	@Autowired
	private EmpresaService empresaService;	
	
	public static final ModelMapper modelMapper = new ModelMapper();
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getEmpresaById(@PathVariable("id") Long id) {
		EmpresaDTO empresa = null;
		try {
			empresa = empresaService.getEmpresaById(id);
			return ResponseEntity.ok(empresa);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la empresa");
		}
	}

	@Override
	@PostMapping
	public  ResponseEntity<Object> save(@RequestBody EmpresaRest empresaRest) throws Exception {
		Optional<EmpresaDTO> empresa = empresaService.findByCuit(empresaRest.getCuit());
		if(empresa.isEmpty()) {
			EmpresaDTO emp = empresaService.save(modelMapper.map(empresaRest, EmpresaDTO.class));
			return ResponseHandler.generateResponse("", HttpStatus.CREATED, modelMapper.map(emp, EmpresaRest.class));
		}else {
			return ResponseHandler.generateResponse("", HttpStatus.CREATED, modelMapper.map(empresa, EmpresaRest.class));
		}
		
		
	}

	@Override
	@GetMapping
	public List<EmpresaRest> findAll() {
		return empresaService.findAll().stream().map(empresa -> modelMapper.map(empresa, EmpresaRest.class)).collect(Collectors.toList());
	}

}
