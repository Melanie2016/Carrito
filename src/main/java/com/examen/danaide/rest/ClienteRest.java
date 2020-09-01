package com.examen.danaide.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.danaide.dao.ClienteDao;
import com.examen.danaide.model.Cliente;

@RestController
@RequestMapping("/clienteRest")
public class ClienteRest {
	
	@Autowired
	private ClienteDao repo;
	
	@PostMapping(path = "/getCliente", consumes = "application/json", produces = "application/json")
	public Cliente crearCarrito(@RequestBody Long id){
		
		return repo.findClienteById(id);
	}
}
