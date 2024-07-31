package com.kh.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mysql.cj.Session;

import jakarta.servlet.http.HttpSession;

@RestController
public class OAuthController {
	
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
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" 
	+ clientId + "&redirect_uri=" + redirectURI + "&state=" + state;
		return "<a href='"+ api_url + "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	
	/*
	 url에 {} 변수명 표시 X ==> @RequestParam ,  혹은 @RequestBody
	 url에 {} 변수명 표시 O ==> PathVariable 
	 
	 */
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state,HttpSession session) {
		
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
				     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectURI + "&code=" + code + "&state=" + state;
		RestTemplate restTemplate = new RestTemplate();

		Map<String,Object>response = restTemplate.getForObject(api_url,Map.class);
		System.out.println("Token Response : "+response); //response as token
		String accessToken = (String)response.get("access_token"); //네이버 개발자 문서 참고
		
		String userInfoUrl = "https://openapi.naver.com/v1/nid/me";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer"+accessToken); //네이버 개발자 에서 제공한 작성 양식
		HttpEntity<String> entity = new HttpEntity<>("",headers);
		
		//HttpEntity : 응답, 요청 모두 존재. (상세 기능 X)
		//ResponseEntity : 응답에 대한 상세 기능
		//RequestEntity : 요청에 대한 상세 기능
		ResponseEntity<Map> userInfoRes = restTemplate.exchange(userInfoUrl, HttpMethod.GET,entity,Map.class);
		Map<String,Object> userInfo = userInfoRes.getBody();
		
		session.setAttribute("userInfo",userInfo);
		
		/*
		 HttpHeaderes 인증에 대한 값을 Bearer 가져오기
		 인증알 위해 서버에서 갖오는 보안토큰.
		 사용자 인증 이후 API 요청할 때 사용한다. (페스티벌에서 팔찌 채우는것 처럼 로그인 후 팔찌를 채워준다. 
		 추후 어떤 요청을 할 때
		 헤더에 Authorization:Bearer{} 를 작성하고 요청해야한다.
		 Authorization : Bearer{}
		*/
	
		return "redirect:";
	}
	
	@GetMapping("/userInfo")
	private Map<String,Object> userInfo(HttpSession session){
		return (Map<String,Object>) session.getAttribute("userInfo");
	}
}
