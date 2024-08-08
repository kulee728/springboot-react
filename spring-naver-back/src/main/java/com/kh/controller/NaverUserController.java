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

import com.kh.dto.NaverUser;
import com.kh.dto.User;
import com.kh.service.NaverUserService;
import com.kh.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/naverusers")
public class NaverUserController {
	/*
	@Autowired
	NaverUserService service;
	
	@GetMapping
	public List<NaverUser> findAlluser() {
		return service.findAllNaverUser(); 
	}
	
	@PostMapping
	public void insertUser(@RequestBody NaverUser user) {
		service.insertNaverUser(user);
	}
	//axios.delete('/users',{params:{id}})와 만난다.
	@DeleteMapping() //혹은 DeleteMapping("/{id}") 하고 RequestParam 대신 PathVarialbe 쓰는 방법도 있다. ..
	public void deleteUser(@RequestParam("id") int id) { //RequestParam : 1개만, RequestBody : body 전체
		log.info("id 넘어왔어요" +id);
		service.deleteUser(id);
	}
	
	//axios.delete(`/users?id=${id}`);
	@DeleteMapping("/{id}") //두번째 방법
	public void deleteUser(@PathVariable int id) {
		service.deleteUser(id);
	}
	
	@PutMapping() //혹은 DeleteMapping("/{id}") 하고 RequestParam 대신 PathVarialbe 쓰는 방법도 있다. ..
	public void updateUser(@RequestParam("id") int id) { //RequestParam : 1개만, RequestBody : body 전체
		service.deleteUser(id);
	}
	*/
}
