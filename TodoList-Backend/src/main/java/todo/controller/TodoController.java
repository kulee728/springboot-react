package todo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import todo.dto.Todo;
import todo.dto.TodoMember;
import todo.service.TodoService;

@RestController
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	/**
	 * Id 중복검사
	 * @Param id
	 * @Return 중복:1, 사용가능 : 0
	 */
	@GetMapping("/idCheck")
	public int idCheck(@RequestParam("id") String id) {
		return service.idCheck(id);
	}
	
	/**
	 *  회원 가입
	 *  @param member
	 *  @return 성공 :1, 실패 : 0
	 */
	@PostMapping("/signup")
	public int signup(@RequestBody TodoMember member) {
		return service.signup(member);
	}
	
	/**
	 *  로그인
	 *  @param member
	 *  @return 회원정보, /todoList 실패: null
	 */
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody TodoMember member) {
		return service.login(member);
	}
	
	/** 할 일 추가
	 * @param todo
	 * @return 성공 1 : 실패 0 
	 */
	@PostMapping("/insertTodo")
	public int insert(@RequestBody Todo todo) {
		return service.todoInsert(todo);
	}
	
	/** 할 일 수정
	 *  @param todo
	 *  @return 성공 1 : 실패 0
	 *  update 의 경우 PutMapping 사용. POST 해도 문제는 없다.
	 */
	@PutMapping("/todo")
	public int updateTodo(@RequestBody Todo todo) {
		return service.todoUpdate(todo);
	}
	
	/** 할 일 삭제
	 *  @param todoNo
	 *  @return 성공 1 : 실패 0
	 *  delete 의 경우 DeleteMapping 사용. POST 해도 문제는 없다.
	 */
	@DeleteMapping("/todo")
	public int updateTodo(@RequestBody int todo) {
		return service.todoDelete(todo);
	}
	
	/*
	 * http 메서드 GET POST PUT DELETE
	 * >> http : 웹에서 사용자가 회원가입,로그인 같은 요청을 보내고 데이터를 전송할 때 사용하는 기능을 정의한 규약
	 * 	POST(C) : 서버에 데이터를 추가하기 위한 요청 (view -> controller body에 사용자 정보가 포함되어 DB로 전송한다)
	 *  GET(R) : 서버로 부터 데이터를 조회하기 위한 요청
	 *  PUT(U) : 서버의 데이터를 수정하기 위한 요청 (/mypage : 기존 사용자가 자신의 정보 수정해달라 서버에 요청)
	 *  DELETE(D) : 서버의 데이터를 삭제하기 위한 요청 (delete /user/1) : 회원번호가 1인 사용자 삭제
	 * 
	 * 
	 */

}
