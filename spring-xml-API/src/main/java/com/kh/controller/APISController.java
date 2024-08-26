package com.kh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/B552584/ArpltnInforInqireSvc") //공공데이터 - 대기오염정보 공통주소
@RestController
public class APISController {
	


	//1	getMsrstnAcctoRltmMesureDnsty	측정소별 실시간 측정정보 조회
	@GetMapping("/getMsrstnAcctoRltmMesureDnsty")
	public String get주소로측정하기() {
		
		
		
		return "result";
	}

	//2	통합대기환경지수 나쁨 이상 측정소 목록조회
	@GetMapping("/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")
	public String get나쁨측정소목록조회() {
		
		
		
		return "result";
	}
	
	//3	시도별 실시간 측정정보 조회
	@GetMapping("/getCtprvnRltmMesureDnsty") 
	public String getRealTimeInfobyLocation() {
		
		
		
		return "result";
	}
	
	//4	대기질 예보통보 조회
	@GetMapping("/getMinuDustFrcstDspth")
	public void 예보통보조회() {
		System.out.println("예보 통보 조회");
		
		
	}
	
	//5	초미세먼지 주간예보 조회
	@GetMapping("/getMinuDustWeekFrcstDspth")
	public void 주간예보조회() {

		System.out.println("주간 예보 조회");

	}
	
}
