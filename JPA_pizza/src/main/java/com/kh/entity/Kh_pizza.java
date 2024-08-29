package com.kh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//lombok이 아닌 jakarta annotation으로 import
import jakarta.persistence.Table;
import lombok.*;

/**
DB에 테이블이 존재하는것을 연결 = DTO
DB에 테이블을 생성해주면서 연결 = entity
DB랑 관계 없음 = vo

 
 */

//@ Table(name = "pizza") DB에 테이블을 저장하길 원하는 경우. 

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kh_pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private double price;
	
	
}
