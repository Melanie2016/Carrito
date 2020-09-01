package com.examen.danaide.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.danaide.dao.ProductoDao;
import com.examen.danaide.model.Producto;

@RestController
@RequestMapping("/productosRest")
public class ProductosRest {
	
	@Autowired
	private ProductoDao repo;	



	@GetMapping
	@RequestMapping("/listar")
	public List<Producto> listarCatalogo(){		

		return repo.findAll();
	}
}
