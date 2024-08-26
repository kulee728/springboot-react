package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.service.ApiService;

@RestController
@RequestMapping("/api")
public class APIController {
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/air-pollution")
	public String getAirData() throws Exception {
		String airData = apiService.getAirData();
		System.out.println(airData);
		return airData;
	}
	
	/* 오직 return 으로 전달만 진행할 때 사용
	@GetMapping("/air-pollution")
	public String getAirData() throws Exception {
		//return apiService.getAirData();
	}
	*/
}
