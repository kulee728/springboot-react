package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/confirm")
public class PaymentController {
	//application.properties에 설정된 키 이름을 가져오기 위한 value
	@Value("${widgetSecretKey}") //특정 키 이름을 외부나 다른 곳에서 가져와 사용할때 $ 사용
	private String widgetSecretKey;
	
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic"+new String(Base64.getEncoder().encode((secretKey+":").getBytes()));
	}
	
	//결제 승인 API 
	
	@PostMapping("/widget") //widget으로 결제 정보가 들어오면 결제 확인 창구로 결제 정보(+사용자정보) 넘겨주기
	public ResponseEntity<?> confirmWidget(@RequestBody Map<String,String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(widgetSecretKey));
	}
	
	@PostMapping("/payment") //payment로 결제 정보가 들어오면 결제 확인 창구로 결제 정보(+사용자정보) 넘겨주기
	public ResponseEntity<?> confirmPayment(@RequestBody Map<String,String> requestBody){
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}

	@PostMapping("/brandpay") //payment로 결제 정보가 들어오면 결제 확인 창구로 결제 정보(+사용자정보) 넘겨주기
	public ResponseEntity<?> confirmBrandPay(@RequestBody Map<String,String> requestBody){
		return confirmBrandPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	private ResponseEntity<?> confirmPayment(Map<String,String> requestBody, String encodedKey){
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//      Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey 을 위에서 encodedKey 작성해줬음
		//		  "Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestBody,headers);
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST,entity,Map.class);                                                                                                                                                                                                                           
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
	
	private ResponseEntity<?> confirmBrandPayment(Map<String,String> requestBody, String encodedKey){
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//      Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey 을 위에서 encodedKey 작성해줬음
		//		  "Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestBody,headers);
		
		try{ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST,entity,Map.class);	
			return new ResponseEntity<>(response.getBody(), response.getStatusCode());
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/***
	 Entity 
	 HttpEntity : Http 요청, 또는 응답에 본문과 헤더를 포함하는 객체
	 >> 사용자가 요청한 응답을 전달할 때 사용.
	 본문 : 실제 전송될 데이터 (Body)
	 헤더 : HTTP 헤더 정보를 포함 (글자? 이미지 ? 동영상?)
	 HttpEntity <문자열,숫자,비워두기> abc = new HttpEntity<비어있어도 됨>("요청본문");
	    
	 ResponseEntity (Http를 상속받아 Http 기능에 응답 기능을 추가로 설정한 Entity)
	     :HttpEntity를 상속받아, Http 응답에 대한 추가적인 정보 제공. 상태코드를 포함,
	     클라이언트에게 응답을 보낼 때 사용한다,.
	 ResponseEntity <Type> : Type 에 해당되는 것은 String, Integer, 여러값이면 ?, 모르겠으면 비워두기
	 = new ResponseEntity("응답 본문",headers);
	 	
	 RequestEntity (Http상속, Http 기능 요청에 대한 기능을 추가로 설정한 Entity)
		:Http요청에 대한 추가적 제공
		URI와 HTTP 메서드(GET POST PUT DELETE)를 포함하고 있어, 서버로 요청을 보낼때 주로 사용한다.
`	 RequestEntity <Type> : Type 에 해당되는 것은 String, Integer, 여러값이면 ?, 모르겠으면 비워두기
	 = new RequestEntity("응답 본문",headers)	
	 
	 RequestEntity<String> req = new RequestEntity<>("요청본문",headers,HttpMethod.Post,url)
	 */
	
	/**
	 차이점 요약하기
	 HttpEntity 기본클래스 HTTp 요청 / 응답 본문 + 헤더 / 상태코드 없음
	 ResponseEntity HttpEntity 상속 / HTTp 응답 반환 / 응답 본문 + 헤더 / 상태코드 없음
	 RequestEntity HttpEntity 상속 / HTTP 요청 전송 / 응답 본문 + 헤더 / 상태코드 없음
	 
	 **/
	
	/*
	 * URL(주소값)과 URI(주소값과 식별값)
	 * URN(식별값, 고유한 이름)
	 * URI = URL + URN
	 */
}