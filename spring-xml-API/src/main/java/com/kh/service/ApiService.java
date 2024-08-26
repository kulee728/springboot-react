package com.kh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.content-type}")
	private String contentType;
	
	@Value("${api.key}")
	private String apiKey;
	
	public String getAirData() throws Exception {
		String url = baseUrl;
		url+="?serviceKey="+ URLEncoder.encode(apiKey,"UTF-8");
		url+="&sidoName="+ URLEncoder.encode("서울","UTF-8");
		url+="&returnType=xml"; //서울 지역 데이터 가져올 때 xml 로 가져오자.
		System.out.println("(1) url : "+url);
		// xml 은 옛날버전, json 은 신버전 
		URL requestUrl = new URL(url);
		System.out.println("(2) requestURL : "+requestUrl);
		HttpURLConnection uc =(HttpURLConnection)requestUrl.openConnection();
		//HttpURLConnection : 자바에서 특정 주소 연결함과 동시에 HTTPMethod 요청보냄
		uc.setRequestMethod("GET");
		uc.setRequestProperty("Content-Type",contentType);
		System.out.println("(3) uc : "+uc);

		BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
		StringBuilder response = new StringBuilder();
		String line;
		
		while((line=br.readLine()) !=null) {
			response.append(line);
		}
		
		System.out.println("(4) response : "+response);
		br.close();
		uc.disconnect();
		
		return response.toString();
		 
	}
}
