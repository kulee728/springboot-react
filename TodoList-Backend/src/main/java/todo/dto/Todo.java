package todo.dto;

import lombok.*;

//VO : Value Object (읽기전용. 이메일 인증번호 등)
//DTO : DB에 값 연동
//Entity : JPA DB 테이블만들지 않아도 알아서 DB의 역할을 해줌(??)

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
	private int todoNo;
	private String title;
	private String isDone;
	private int todoMemberNo;
}
