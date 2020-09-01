package com.examen.danaide.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.danaide.dao.ClienteDao;
import com.examen.danaide.model.Cliente;

@RestController
@RequestMapping("/homeRest")
public class HomeRest {
	
	
	@Autowired
	private ClienteDao repo;
	
	@GetMapping
	@RequestMapping("/redirect/{id}")
	public Cliente listaJson( @PathVariable("id") Integer id){
	
		long idCliente = id;
		return repo.findClienteById(idCliente);
	}

}
