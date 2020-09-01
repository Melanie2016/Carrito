package com.examen.danaide.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PromocionCalendario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic (optional=false)
    @Column (name="promo_id")
	private Long id;
	private Date fechaDesde;
	private Date fechaHasta;
	private String descripcion;
	
//	@OneToMany(mappedBy="promo")
//    private List<Carrito> carritos;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	
	
	
	
	
}
