package com.examen.danaide.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.danaide.dao.ItemsDao;
import com.examen.danaide.model.ItemsCarrito;
import com.examen.danaide.service.IItems;

@RestController
@RequestMapping("/itemsRest")
public class ItemsRest {
	
	
	
	@Autowired
	private ItemsDao repo;	
	
	@Autowired
	private IItems service ; 
	
	
	@GetMapping
	@RequestMapping("/listar")
	public List<ItemsCarrito> listar(){		
//		return repo.listarElPrimero();
		return repo.findAll();
	}
	
	
	@GetMapping
	@RequestMapping("/listarPorCarrito/{idCliente}")
	public List<ItemsCarrito> listarItemsDelCarrito(@PathVariable("idCliente") Long idCliente){		
		
//		long id= idCliente;
		return service.getItemsDelCarritoSegunCliente(idCliente);
	}

	
	@DeleteMapping(value = "/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long idItems){
		service.deleteItemCarritoById(idItems);
		 
	}
	
	@PutMapping(path = "/updateItem", consumes = "application/json", produces = "application/json")
	public void modificar(@RequestBody ItemsCarrito item){
		 repo.save(item); // si existe modifica, si no inserta 
	}
	
	
	@PostMapping(path = "/updateItemAndBuy", consumes = "application/json", produces = "application/json")
	public void modificar(@RequestBody List<ItemsCarrito> items){
		 service.updateItemAndBuy(items);
	}
	
	
}
