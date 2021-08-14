package com.ticketera.av.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketera.av.dtos.EmpresaDTO;
import com.ticketera.av.model.Empresa;

public interface EmpresasRepository extends JpaRepository<Empresa, Long> {
	Optional<Empresa> findById(Long id);
	Empresa save(Empresa entity);
	List<Empresa> findAll();
	public Optional<EmpresaDTO> findByCuit(Long cuit);
}
