package com.kh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kh.dto.Chicken;

// mybatis - mapper 이 두가지를 설정 
// @Repository  @Mapper 는 interface로 시작

@Repository
public interface ChickenRepository extends JpaRepository<Chicken, Integer> {
	
	//검색은 기본으로 제공하지 않기때문에 필수로 작성해줘야 한다.
	List<Chicken> findByChickenNameContainingIgnoreCase(String query);
	//findByChickenName = 어떤 column 이름에서 검색할 것인지
	//Like : %...% 와 같은 기능
	//IgnoreaCase : 대소문자 구분없이 검색
	
	
	
	
	// 전체보기 전체넣기 전체수정하기 전체삭제하기 와 같은 기본 기능은
	// JpaRepository 안에 모두 들어있음
	
	// select * from chicken
	//Chicken findById(Integer id);
	//findByEmailPassword 이런식으로 여러 parameter Where절을 만드는 형식을 JPA에 그릴 수 있다.
	
}
