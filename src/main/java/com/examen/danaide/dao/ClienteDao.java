package com.examen.danaide.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.danaide.model.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer>{

	Cliente findClienteById(Long id);
}
