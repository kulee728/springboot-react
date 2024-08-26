package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.Chicken;
import com.kh.service.ChickenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/chicken")
public class ChickenController {
	
	@Autowired // 참조
	private ChickenService chickenService;
	
	@GetMapping
	public List<Chicken> getAllChickens(){
		return chickenService.getAllChickens();
	}
	

	
	@PostMapping
	public Chicken saveChicken(@RequestBody Chicken chicken) {
		return chickenService.createChicken(chicken);
	}
	
	@GetMapping("{id}")
	public Chicken getChickenById(@PathVariable("id") Integer id) {
		return chickenService.findById(id);
	}
	
	@PutMapping("{id}")
	public Chicken updateChicken(@PathVariable Integer id,@RequestBody Chicken chicken) {
		return chickenService.updateChicken(id,chicken);
	}
	
	@DeleteMapping("{id}")
	public void deleteChicken(@PathVariable("id") Integer id) {
		chickenService.deleteChicken(id);
	}
	
	@GetMapping("/search")
	public List<Chicken> searchChickens(@RequestParam("query") String query){
		return chickenService.searchChickens(query);
	}
}






