package com.examen.danaide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/") 
	public String home() {
		return "loginFake";
	}
	
	@GetMapping("/index") 
	public String loginFake() {
		return "loginFake";
	}
	
	
	@GetMapping("/home") 
	public String redirect() {
		
		return "index";
	}


}
