package todo.service;

import java.util.List;
import java.util.Map;

import todo.dto.Todo;
import todo.dto.TodoMember;

public interface TodoService {

	int idCheck(String id);
	int signup(TodoMember todoMember);
	Map<String,Object> login(TodoMember todoMember); //JSON 형식으로 만들어서 다시 controller에 던져줘야 하기때문 

	int todoDelete(int todoNo);
	int todoInsert(Todo todo);
	int todoUpdate(Todo todo);
	//List<Todo> selectTodoList();
}
