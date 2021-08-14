package com.ticketera.av.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name= "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name= "id_empresa", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEmpresa;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "razon_social")
	private String razonSocial;
	@Column(name = "cuit" , unique = true)
	private long cuit;

//	@OneToMany( mappedBy = "empresa")
//	private List<Usuario> usuarios;
	
	public Empresa(Long id) {
		this.idEmpresa = id;
	}

	public Empresa() {
	}
}
