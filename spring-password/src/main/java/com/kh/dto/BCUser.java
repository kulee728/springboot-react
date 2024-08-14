package com.kh.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BCUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 올라가는 시퀀스 . NextVAL, auto increment
	private int id;
	private String username;
	private String email;
	private String password;
}
