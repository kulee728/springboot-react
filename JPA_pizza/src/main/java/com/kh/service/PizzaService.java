package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.entity.Kh_pizza;
import com.kh.repository.PizzaRepository;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	//치킨 메뉴 추가하기
	
	public Kh_pizza createPizza(Kh_pizza kh_pizza) {
		return pizzaRepository.save(kh_pizza);
	}
	
	//피자 메뉴 모두 보기
	public List<Kh_pizza> getAllPizzaMenu(){
		return pizzaRepository.findAll();
	}

	//피자 검색하기
	public List<Kh_pizza> findPizzaByName(String pizzaName){
		return pizzaRepository.findByNameContainingIgnoreCase(pizzaName);
	}
	
}
