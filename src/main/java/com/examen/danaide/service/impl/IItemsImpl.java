package com.examen.danaide.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examen.danaide.dao.CarritoDao;
import com.examen.danaide.dao.ClienteDao;
import com.examen.danaide.dao.CompraDao;
import com.examen.danaide.dao.ItemsDao;
import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.Cliente;
import com.examen.danaide.model.Compra;
import com.examen.danaide.model.ItemsCarrito;
import com.examen.danaide.service.IItems;



@Component
public class IItemsImpl implements IItems {

	
	@Autowired
	private ClienteDao repoCliente;
	
	@Autowired
	private CarritoDao repoCarrito;
	
	@Autowired
	private CompraDao repoCompra;
	
	@Autowired
	private ItemsDao repo;	
	
	private String fechaActual;
	
	private Double total;
	
	
	@Override
	public List<ItemsCarrito> getItemsDelCarritoSegunCliente(Long idCliente) {
		
		
		
		Date date = new Date();  		
        Timestamp ts = new Timestamp(date.getTime()); 
        this.fechaActual = ts.toString().substring(0,10);
        
        // para probar 
        this.fechaActual = "2020-08-29";
//        long idCliente = 1;
        
        Cliente cliente = repoCliente.findClienteById(idCliente);
        
        Carrito carrito = repoCarrito.getCarritoVigenteConEstadoPendiente(cliente, this.fechaActual);
			 

		return repo.getItemsDelCarritoSegunCliente(cliente, carrito);
	}

	@Override
	public void deleteItemCarritoById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void updateItemAndBuy(List<ItemsCarrito> lista) {
		
		Compra newCompra = new Compra();
		Carrito carrito = new Carrito();
		Cliente cliente = new Cliente();
		Date date = new Date(); 
		this.total = 00.00 ;
		for (ItemsCarrito i : lista) {
			i.setSubtotal(i.getCantidad() * i.getProducto().getPrecio());
			repo.save(i);
			carrito = i.getCarrito();
			cliente = carrito.getCliente();
			
			newCompra.setCarrito(carrito);
			newCompra.setCliente(cliente);
			newCompra.setFechaAbonada(date);
			
			this.total = this.total + (i.getSubtotal() * i.getCantidad());
		}
		newCompra.setTotal(this.total);
		
		repoCompra.save(newCompra);
		
	}

}
