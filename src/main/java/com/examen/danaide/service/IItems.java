package com.examen.danaide.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.danaide.model.ItemsCarrito;

@Service
public interface IItems {
	
	List<ItemsCarrito> getItemsDelCarritoSegunCliente( Long idCliente);

	void deleteItemCarritoById(Long id);
	
	void updateItemAndBuy(List<ItemsCarrito> lista);
	

}
