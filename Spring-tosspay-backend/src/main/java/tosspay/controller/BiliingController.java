package tosspay.controller;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/billing")
public class BiliingController {
	//value 값 이용해서 apiSecretKey 가져오기
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic"+new String(Base64.getEncoder().encode((secretKey+":").getBytes()));
	}
	
	//RestTemplate header와 body 최종으로 작성할 공간 생성
	
	//map 같이 만들기
	private final Map<String,String> billingMap = new ConcurrentHashMap<>();
	
	//server.js에서 confirm-billing url 참조해서 코드 완성하기
	@PostMapping("/confirm-billing")
	public ResponseEntity<?> confirmBilling(@RequestBody Map<String,String> requestBody){
		return confirmBilling(requestBody,encodeSecretKey(apiSecretKey));
	}	
	private ResponseEntity<?> confirmBilling(Map<String, String> requestBody, String encodedKey) {
		String billingKey = billingMap.get(requestBody.get("customerKey"));
		String url = "https://api.tosspayments.com/v1/billing/"+billingKey;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", encodedKey);
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestBody,headers);
		ResponseEntity<Map> res = restTemplate.exchange(url,HttpMethod.POST,entity,Map.class);
		//
		billingMap.put(requestBody.get("customerKey"), res.getBody().get("billingKey").toString());
		//billingKey 정기 결제에 관련 키 값이 들어있음
		
		return new ResponseEntity<>(res.getBody(),res.getStatusCode());
	}
}
