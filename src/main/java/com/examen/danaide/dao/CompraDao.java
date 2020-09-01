package com.examen.danaide.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.danaide.model.Compra;

@Repository 
public interface CompraDao  extends JpaRepository<Compra, Long> {

}
