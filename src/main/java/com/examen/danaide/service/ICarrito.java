package com.examen.danaide.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.ItemsCarrito;

@Service
public interface ICarrito {

	List<Carrito> getCarritosVigentesDelCliente(Long idCliente);
	
	Carrito crearYDevolverCarrito(Carrito carrito);
	
	void deleteCarritoVigente(Long idCarrito);
	
	public List<Carrito> getCarritosFinalizadosSegunCliente(Long idCliente) ;
}
