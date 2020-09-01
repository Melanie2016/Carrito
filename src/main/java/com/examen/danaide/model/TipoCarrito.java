package com.examen.danaide.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoCarrito {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic (optional=false)
    @Column (name="tipo_carrito_id")
	private Long id;
	private String descripcion;
	
//	@OneToMany(mappedBy="tipo")
//    private List<Carrito> carritos;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
