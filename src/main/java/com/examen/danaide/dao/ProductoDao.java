package com.examen.danaide.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examen.danaide.model.Producto;


public interface ProductoDao extends JpaRepository<Producto, Integer>{
	
	@Query (value = "SELECT TOP 1 FROM producto",nativeQuery = true)
	List<Producto> listarElPrimero();

}
