package todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import todo.dto.Todo;
import todo.dto.TodoMember;

@Mapper
public interface TodoMapper {
	int idCheck(String id);
	int signup(TodoMember todoMember);
	TodoMember login(TodoMember todoMember);
	
	int todoDelete(int todoNo);
	int todoInsert(Todo todo);
	int todoUpdate(Todo todo);
	List<Todo> selectTodoList(int todoMemberNo);
}
