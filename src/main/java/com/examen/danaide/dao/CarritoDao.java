package com.examen.danaide.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.Cliente;

@Repository
public interface CarritoDao extends JpaRepository<Carrito, Integer> {
	
	List<Carrito> findByFechaCreacionLike (String fechaCreacion);
	
	Carrito findCarritoById(Long id);
	
	@Query("FROM Carrito c WHERE c.cliente = :cliente AND c.fechaCreacion LIKE %:fechaActual%")
	List<Carrito> getCarritosVigentesDelCliente (@Param ("cliente") Cliente cliente, @Param ("fechaActual") String fechaActual);
	
	
	@Query("FROM Carrito c WHERE c.cliente = :cliente AND c.fechaCreacion LIKE %:fechaActual% AND c.estado = 'PENDIENTE' ")
	Carrito getCarritoVigenteConEstadoPendiente(@Param ("cliente") Cliente cliente , @Param ("fechaActual") String fechaActual);
	
	@Query("FROM Carrito c WHERE c.cliente = :cliente AND c.fechaCreacion LIKE %:fechaActual% AND c.estado = 'FINALIZADO' ")
	List<Carrito> getCarritoVigenteConEstadoFinalizado(@Param ("cliente") Cliente cliente , @Param ("fechaActual") String fechaActual);

	@Transactional
	void deleteCarritoById(Long idCarrito);
	
}

