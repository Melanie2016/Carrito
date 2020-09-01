package com.examen.danaide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarritoController {
	
	@GetMapping("/carrito") 
    public String mostrarCarritos(
        @RequestParam(name="id", required=false, defaultValue="0") Long id) {
	
         return "carrito"; 
  }
	
	 
	
}
