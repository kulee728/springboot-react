package com.kh.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor//필수생성자
public class NaverUser {
	private String id;
	private String email;
	private String nickname;
	private String name;
	private String gender;
	private String profileImage;
	private String password;

}