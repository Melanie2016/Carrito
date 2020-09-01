package com.examen.danaide.service.impl;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.danaide.dao.CarritoDao;
import com.examen.danaide.dao.ClienteDao;
import com.examen.danaide.dao.ItemsDao;
import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.Cliente;
import com.examen.danaide.model.ItemsCarrito;
import com.examen.danaide.service.ICarrito;

@Component
public class ICarritoImpl implements ICarrito {
	
	@Autowired
	private CarritoDao repo;
	
	@Autowired
	private ItemsDao repoItems;
	
	@Autowired
	private ClienteDao repoCliente;
	
	
	
	
	
	private String fechaActual;

	@Override
	public List<Carrito> getCarritosVigentesDelCliente(Long idCliente) {
		
		Date date = new Date();  		
        Timestamp ts = new Timestamp(date.getTime()); 
        this.fechaActual = ts.toString().substring(0,10);
        
        // para probar 
        this.fechaActual = "2020-08-29";
//        long idCliente = 1;
        Cliente cliente = repoCliente.findClienteById(idCliente);
        
        List<Carrito> lista = repo.getCarritosVigentesDelCliente(cliente, this.fechaActual);
		return lista;
	}

	@Override
	public Carrito crearYDevolverCarrito(Carrito carrito) {
		
		return repo.save(carrito);
		
	}

	@Override
	public void deleteCarritoVigente(Long idCarrito) {
		
		Carrito carrito = repo.findCarritoById(idCarrito);
		
		repoItems.deleteByCarrito(carrito);
		
		repo.deleteCarritoById(idCarrito);
		
	}

	@Override
	public List<Carrito> getCarritosFinalizadosSegunCliente(Long idCliente) {
			
		Date date = new Date();  		
        Timestamp ts = new Timestamp(date.getTime()); 
        this.fechaActual = ts.toString().substring(0,10);
         
        this.fechaActual = "2020-08-29";
//        long idCliente = 1;
        
        Cliente cliente = repoCliente.findClienteById(idCliente);
        

		return repo.getCarritoVigenteConEstadoFinalizado(cliente, fechaActual);
	}
}
