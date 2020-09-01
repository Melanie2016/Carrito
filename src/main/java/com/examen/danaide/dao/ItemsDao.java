package com.examen.danaide.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examen.danaide.model.Carrito;
import com.examen.danaide.model.Cliente;
import com.examen.danaide.model.ItemsCarrito;


@Repository
public interface ItemsDao extends JpaRepository<ItemsCarrito, Integer> {
	
	@Transactional
	void deleteById(Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM ItemsCarrito i WHERE i.carrito= :carrito")
	void deleteByCarrito( @Param ("carrito") Carrito carrito);
	
	// se debe pasar el carrito vigente 
	@Query("SELECT DISTINCT i FROM ItemsCarrito i INNER JOIN Carrito car ON i.carrito = :carrito JOIN Cliente cli ON car.cliente = :cliente  WHERE car.cliente = :cliente AND i.carrito= :carrito")
	List<ItemsCarrito> getItemsDelCarritoSegunCliente (@Param ("cliente") Cliente cliente, @Param ("carrito") Carrito carrito);
	
	

}
