package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.dto.BCUser;
/*
 mybatis mapper를 생략하고 DB쿼리를 짜려면
 SQL알아서 작성해주는 JpaRepository
 //findByEmail() 이런 findBy같은 구문 규칙을 외워야 한다. 
  
 */
public interface BCUserRepository extends JpaRepository<BCUser,Integer> {
	//BCUser saveUser();
	//save -> 기본적인 SQL은 이미 내장되어있다.
	
	BCUser findByEmail(String email);
	//	
}
