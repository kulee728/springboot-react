package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.LoginService;

@RestController
public class LoginController {

	@Autowired 
	private LoginService loginService;
	
	/*
	@PostMapping("/login")
	public ResponseEntity<String> login(
			@RequestParam("id") String id, @RequestParam("password") String password
			//RequestBody를 받는 방식도 있겠다.
			) {
		NaverUser user = loginService.login(id, password);
		if(user != null) {
			return ResponseEntity.ok("로그인 성공");
		}
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failed from mybatis");
	}
	*/
	@PostMapping("/login")
	public ResponseEntity<String> login(
			@RequestParam("id") String id, @RequestParam("password") String password
			//RequestBody를 받는 방식도 있겠다.
			) {
		NaverUser user = loginService.login(id, password);
		if(user != null) {
			return ResponseEntity.ok("로그인 성공");
		}
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login failed from mybatis");
	}
}
