package com.examen.danaide.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Carrito {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic (optional=false)
    @Column (name="carrito_id")
	private Long id;	
	private String fechaCreacion;
	private String estado;
//	
//	@OneToMany(mappedBy="carrito")
//    private List<ItemsCarrito> items;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private Cliente cliente;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private TipoCarrito tipo;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	private PromocionCalendario promo;
//	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name="cli_id", referencedColumnName="cli_id")
    private Cliente cliente;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn (name="tipo_carrito_id", referencedColumnName="tipo_carrito_id")
	private TipoCarrito tipo;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn (name="promo_id", referencedColumnName="promo_id")
    private PromocionCalendario promo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "id_compra")
	@JsonBackReference
    private Compra compra;
	
	


	
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoCarrito getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarrito tipo) {
		this.tipo = tipo;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public PromocionCalendario getPromo() {
		return promo;
	}

	public void setPromo(PromocionCalendario promo) {
		this.promo = promo;
	}
	
	
	

}
