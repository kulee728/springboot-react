package todo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import todo.dto.Todo;
import todo.dto.TodoMember;
import todo.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoMapper todoMapper;

	@Override
	public int idCheck(String id) {
		return todoMapper.idCheck(id);
	}

	@Override
	public int signup(TodoMember todoMember) {
		return todoMapper.signup(todoMember);
	}

	@Override
	public Map<String, Object> login(TodoMember todoMember) {
		TodoMember loginMember = todoMapper.login(todoMember);
		Map<String,Object> map = new HashMap<>();
		map.put("loginMember",loginMember);
		loginMember.getTodoMemberNo();
		if(loginMember != null) {
			List<Todo> todoList = todoMapper.selectTodoList(loginMember.getTodoMemberNo()) ;
			map.put("todoList", todoList);
		}
		//map.loginMember , map.todoList 두개
		return map;
	}

	@Override
	public int todoDelete(int todoNo) {
		return todoMapper.todoDelete(todoNo);
	}

	@Override
	public int todoInsert(Todo todo) {
		int result = todoMapper.todoInsert(todo);
		return result>0 ? todo.getTodoNo() : 0; // 제대로 insert 됐다면 todoNo, 아니면 0을 넣는다.
	} //리액트에서 키를 받아서 넣을 것임.

	@Override
	public int todoUpdate(Todo todo) {
		return todoMapper.todoUpdate(todo);
	}
}
