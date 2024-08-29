package com.kh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.kh.entity.Kh_pizza;
import java.util.List;


@Repository //mapper와 퍼시스턴트 프레임워크를 합쳐놓은 기능

public interface PizzaRepository  extends JpaRepository<Kh_pizza,Integer>{
	
	//검색과 같이 결과값이 여러개인 경우 return type > List
	//findby[column명]: select* where [column명] = [매개변수]
	//Containing = 특정 앞 단어 상관없이 앞뒤로 검색 %[값]%
	List<Kh_pizza> findByNameContainingIgnoreCase(String name);
	
	
	

}
