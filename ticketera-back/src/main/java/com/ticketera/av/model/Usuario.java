package com.ticketera.av.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import lombok.Data;


@Data
@Entity
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Usuario implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(name = "contraseña")
    private String password;
    @Transient
    private String temporalyPassword;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private  boolean passwordProvisoria;
//    @ManyToOne
//    @JoinColumn(name="id_empresa")
//    private Long idEmpresa;
//    
    @ManyToOne()
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;
    
//    private String idEmpresa;
    
    @Column(name = "fecha_baja", nullable = true)
    private Date fechaBaja;

//    public Usuario(Long id) {
//        this.id = id;
//    }
//
//    public Usuario() {
//    }
}


