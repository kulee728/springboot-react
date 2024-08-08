package com.kh.controller;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.dto.NaverUser;
//이 친구들을 위해 
// implementation 'com.fasterxml.jackson.core:jackson-databind:2.14.0'
// gradle build dependencies 에 추가한다.
// JSON 데이터를 Java 객체로 변환하거나 Java 객체를 JSON 데이터로 변환한다.
import com.kh.service.NaverUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController //얘가 찐입니다.
public class NaverRegistController {
	
	@Autowired
	NaverUserService service;
	
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
	
	@GetMapping("/callback")
	public ResponseEntity<String> callback(@RequestParam("code") String code, @RequestParam("state") String state){
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
				     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectURI + "&code=" + code + "&state=" + state;
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(api_url,String.class);
		System.out.println("Responsed Callback :"+response);
		String accessToken = getToken(response); //위 주소에서 작성한 토큰을 가져오겠다.
		String redirectUrl = "http://localhost:3000/userinfo?access_token=" + accessToken;
		HttpHeaders header = new HttpHeaders();
		header.add("Location", redirectUrl);
		
		return new ResponseEntity<>(header,HttpStatus.FOUND);
	}
	
	//인증받은 토큰을 전달
	private String getToken(String res) {
		ObjectMapper om = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = om.readTree(res);
			return jsonNode.get("access_token").asText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	
	//callback 에서 가져온 user정보가 들어가는 주소
	@GetMapping("/userinfo")
	public ResponseEntity<String> getUserInfo
	(@RequestParam("access_token") String accessToken){
		String userInfoUrl = "https://openapi.naver.com/v1/nid/me";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+ accessToken);
		headers.set("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		//entity 무사히 넘어오는걸까?
		System.out.println("Entity : "+entity);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> res = restTemplate.exchange(userInfoUrl,HttpMethod.GET,entity,String.class);
		System.out.println("Response Status Code : "+res.getStatusCode()); // 로그인 성공 실패
		System.out.println("Response Headers: "+res.getHeaders()); // 자격 인증 정보 
		System.out.println("Response Body : "+res.getBody()); //로그인 정보
				
		 try {
			JSONObject jObject = new JSONObject(res.getBody());
			JSONObject userJson = jObject.getJSONObject("response");

			NaverUser nu = new NaverUser();
			nu.setId(userJson.getString("id"));
			nu.setEmail(userJson.getString("email"));
			
		    String name = userJson.getString("name");
		    byte[] bytes = name.getBytes(StandardCharsets.ISO_8859_1);
		    name = new String(bytes,StandardCharsets.UTF_8);
			nu.setName(name);
			
			nu.setPassword(userJson.getString("password"));
			nu.setProfileImage(userJson.getString("profile_image"));
			
		} catch (JSONException e) {
			e.printStackTrace();
			log.info("DB push as JSON ERROR!");
		}
		
	
		
		if(!res.getStatusCode().is2xxSuccessful()) {
			System.out.println("fail Status code : "+res.getStatusCode());
		}
		return res;
	}
	
	@PostMapping("/signup")
	public String signUpControl(@RequestBody NaverUser nu) {
		service.insertNaverUser(nu);
		return "true";
	}
	
}
