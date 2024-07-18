package todo.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoMember {
	private int todoMemberNo;
	private String id;
	private String pw;
	private String name;
}
