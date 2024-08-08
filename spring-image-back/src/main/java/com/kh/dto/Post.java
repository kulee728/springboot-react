package com.kh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id; //autoIncrement
	private String title;
	private String content;
	private String imageUrl;
	private String createdAt;  //default Value auto
}
