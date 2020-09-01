package com.examen.danaide.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic (optional=false)
    @Column (name="prod_id")
	private Long id;

	private String descripcion;
	
	private Double precio;
	
//	@OneToMany(mappedBy="producto", fetch = FetchType.LAZY)
//    private List<ItemsCarrito> items;

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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

//	public List<ItemsCarrito> getItems() {
//		return items;
//	}
//
//	public void setItems(List<ItemsCarrito> items) {
//		this.items = items;
//	}
	
	
	

	
	
}
