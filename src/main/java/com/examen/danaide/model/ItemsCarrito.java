package com.examen.danaide.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ItemsCarrito {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic (optional=false)
    @Column (name="items_id")
	private Long id;
	private Integer cantidad;
	private Double subtotal;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Carrito carrito;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Producto producto;

	@ManyToOne (optional=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name="prod_id", referencedColumnName="prod_id")
    private Producto producto = null;
	
	@ManyToOne (optional=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name="carrito_id", referencedColumnName="carrito_id")
    private Carrito carrito = null;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
	
}
