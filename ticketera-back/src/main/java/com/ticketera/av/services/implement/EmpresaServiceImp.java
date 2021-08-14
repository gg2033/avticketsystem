package com.ticketera.av.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketera.av.dtos.EmpresaDTO;
import com.ticketera.av.model.Empresa;
import com.ticketera.av.repository.EmpresasRepository;
import com.ticketera.av.services.EmpresaService;

@Service
public class EmpresaServiceImp implements EmpresaService {
	@Autowired
	private EmpresasRepository empresaRepositoty;
	
	public static final ModelMapper modelMapper = new ModelMapper();
	@Override
	public EmpresaDTO getEmpresaById(Long id) throws Exception{
			return modelMapper.map(empresaRepositoty.findById(id).orElseThrow(()->new Exception("No se pudo encontrar la empresa")), EmpresaDTO.class);

	}
	@Override
	public EmpresaDTO save(EmpresaDTO empresa) {
		Empresa em = modelMapper.map(empresa, Empresa.class);
		try{
			return modelMapper.map(empresaRepositoty.save(em), EmpresaDTO.class);
		
		}catch(Exception e){
			System.out.printf(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EmpresaDTO> findAll() {
		return empresaRepositoty.findAll().stream().map(empresa -> modelMapper.map(empresa, EmpresaDTO.class)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<EmpresaDTO> findByCuit(Long cuit) throws Exception {
		// TODO Auto-generated method stub
		Optional<EmpresaDTO> em = empresaRepositoty.findByCuit(cuit);
		return em;
	}

}
