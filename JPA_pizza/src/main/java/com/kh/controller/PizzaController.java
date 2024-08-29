package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.entity.Kh_pizza;
import com.kh.service.PizzaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pizza")
@Slf4j
public class PizzaController {

	@Autowired
	PizzaService service;
	
	@GetMapping("/all")
	public List<Kh_pizza> getAllPizza(){
		return service.getAllPizzaMenu();
	}
	
	@GetMapping("/search")
	public List<Kh_pizza> searchPizzaByName(@RequestParam("query") String name){
		log.info(name);
		return service.findPizzaByName(name);
	}
	
	@PostMapping("/save")
	public Kh_pizza savePizza(@RequestBody Kh_pizza p) {
		return service.createPizza(p);
	}
}
