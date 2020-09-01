package com.examen.danaide.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.danaide.dao.CarritoDao;
import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.ItemsCarrito;
import com.examen.danaide.service.ICarrito;

@RestController
@RequestMapping("/carritoRest")
public class CarritoRest {

	@Autowired
	private CarritoDao repo;	
	
	@Autowired
	private ICarrito servicio;
	
	
	@GetMapping
	@RequestMapping("/listar")
	public List<Carrito> listar(){		
		return repo.findAll();
	}
	
	@PostMapping(path = "/getCarritos", consumes = "application/json", produces = "application/json")
	public List<Carrito> getCarritosDelCliente(@RequestBody Long idCliente){
		
		List<Carrito> c = servicio.getCarritosVigentesDelCliente(idCliente);
		//List<Carrito> c2 = repo.findByFechaCreacionLike("%2020-08-29%");
		
		return c;
	}
	
	

	@PostMapping(path = "/crearCarrito", consumes = "application/json", produces = "application/json")
	public Carrito crearCarrito(@RequestBody Carrito carrito){
		
		
		return servicio.crearYDevolverCarrito(carrito);
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long idCarrito){
		
		servicio.deleteCarritoVigente(idCarrito);
	}
	
	
	@GetMapping
	@RequestMapping("/listarCarritosFinalizados/{idCliente}")
	public List<Carrito> listarCarritosFinalizados(@PathVariable("idCliente") Long idCliente){		
		
//		long id= idCliente;
		return servicio.getCarritosFinalizadosSegunCliente(idCliente);
	}

	
}
