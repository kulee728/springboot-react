package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.NaverUserService;

// 네이버로 회원가입 후 DB에 회원가입 정보를 등록하는 컨트롤러

@RestController
public class NaverRegistController {
	
	@Autowired
	private NaverUserService naverUserService;
	
	//회원가입을 위한 Post Mapping 작성
	//@PostMapping("서울특별시/강남구/역삼역3번출구") 
	@PostMapping("/NaverAPI/register") //프론트와 데이터를 주고받기 위한 만남의 장소 url api 부름
	public String insertNaverUser(@RequestBody NaverUser naverUser) {
		
		// DB에 React로 가져온 naverUser정보를 큰 수정없이 전체다 넣겠다.
		naverUserService.insertNaverUser(naverUser);
		
		
		
		//naverUserService.insertNaverUser(null);
		// null 이 들어갈 자리에는 React 에서 받아온 값을 넣어주는 공간
		// 처음에는 Java에서 어떤 값을 넣어줘야할지 모르기 때문에 null 로 설정이 되어있는 것일 뿐
		// null 자리에는 @RequestBody 나 @RequestParam으로 가져온 값을 작성
		// @RequestBody = 전체 (= 전체를 한 번에 집어넣는다는 것은 부분적으로 수정하거나 커스텀이 필요하지 않을 경우)
		// @RequestParam = 부분 수정 추가, 부분적으로 추가할 때 사용
		return "Naver API를 활용한 회원가입 성공!!!!";
	}
}