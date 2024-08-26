package com.kh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class APIService {
	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.key}")
	private String apiKey;
	
	@Value("${api.content-type}")
	private String contentType;

	public String getApiData(String endpoint) {
		//endpoint : api 가 끝나는 지점
		
		String url = baseUrl+endpoint;
		HttpHeaders header = new HttpHeaders(); 
		
		return "";
	}
	
	
}
