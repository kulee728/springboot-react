package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/naver")
public class NaverLoginController {
	
	/*
	 24-07-29 React <> Spring Boot 연동 컨트롤러
	 OAuthController 와 api 주소 동일, 나타나는 충돌을 막기 위해 
	@RequestMapping 주소 변경
	 
	 */
	
	
	
	/* @Value annotation은 .properties (application / config) 에 작성한 변수(키)를 가져올 수 있다. */
	
	@Value("${naver.client-id}")
	private String clientId;
	
	@Value("${naver.client-secret}")
	private String clientSecret;
	
	@Value("${naver.state}")
	private String state;
	
	@Value("${naver.redirect-uri}")
	private String redirectURI;
	
	
	@GetMapping("/naverLogin")
	public String naverLogin() {
		log.info("억까야");
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" 
				+ clientId + "&redirect_uri=" + redirectURI + "&state=" + state;
					return "3<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	
	/*
	 url에 {} 변수명 표시 X ==> @RequestParam ,  혹은 @RequestBody
	 url에 {} 변수명 표시 O ==> PathVariable 
	 
	 */
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state) {
		//네이버에서 로그인을 성공했을 때, 받는 값
		//왜 RequestParam..? >> 공식.
		// 1. client-id = 어디 회사에 들어왔는지
		// 2. clientSecret: 회사 들어오기 위한 비밀번호
		// 3. redirectUri : 들어오기 위한 심사를 무사히 완료 했으면 내보내는 위치 설정
		// 4. code : 네이버로부터 무사히 들어왔다는(로그인) 인증 코드 받기.
		// 5. state : CSRF 공격 방지
		
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
				     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectURI + "&code=" + code + "&state=" + state;
		RestTemplate restTemplate = new RestTemplate();
		//RestTemplate > HTTP method 와 상호작용하기 위한 형식, 데이터를 JSON 형식으로 처리함
		//api_rul 주소로 응답받은 결과를 String = 문자로 가져와서 사용하겠다.
		String response = restTemplate.getForObject(api_url,String.class);
		return response;
	}
	
}
