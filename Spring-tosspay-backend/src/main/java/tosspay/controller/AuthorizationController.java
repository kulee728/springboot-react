package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//인증 컨트롤러

@RestController
public class AuthorizationController {

	// @Value("${application.properties에작성한변수명}") : spring boot에서
	// application.properties에 작성 한 값을 가져온다.
	@Value("${apiSecretKey}")
	private String apiSecretKey;

	private final RestTemplate restTemplate = new RestTemplate();
	// 보낼 http request를 담을 변수

	/*
	 * const encryptedWidgetSecretKey = "Basic " + Buffer.from(widgetSecretKey +
	 * ":").toString("base64"); const encryptedApiSecretKey = "Basic " +
	 * Buffer.from(apiSecretKey + ":").toString("base64"); toss에서 명시한 API 키 형식을
	 * 참조한다.
	 */
	// 주어진 비밀키를 인코딩해서 HTTP헤더에 비밀키를 넣을 수 있도록 형식을 맞춘다.
	private String encodeSecretKey(String secretKey) {
		return "Basic " + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
	}

	/***
	 * Base64 : ASCII to value (char -> 0~63 사이 값으로 encoding 방식.. human text (ASCII
	 * binary) to base text)
	 */

	/**
	 * app.get("/callback-auth", function (req, res) { const { customerKey, code } =
	 * req.query;
	 * fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token",{
	 * method: "POST", 
	 *  headers: { 
	 *  	Authorization: encryptedApiSecretKey,
	 * 		"Content-Type": "application/json", 
	 * 	}, 
	 * 	body: JSON.stringify({ 
	 * 		grantType: "AuthorizationCode", 
	 * 		customerKey, code, 
	 * }),
	 *  이부분에 대한 response 작성
	 */
	@GetMapping("/callback-auth")
	public ResponseEntity<?> callbackAuth(
			@RequestParam String customerKey,
			@RequestParam String code) {
		
		String url = "https://api.tosspayments.com/v1/brandpay/authorizations/access-token";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",encodeSecretKey(apiSecretKey));
		headers.set("Content-Type", "application/json");
		
		Map<String,String> requestBody = Map.of(
				"grantType", "AuthorizationCode",
				"customerKey", customerKey,
				"code", code
				); //Map.of : 가져온 값을 보호하기 위한 unModifiable Map 생성
		
		HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestBody,headers);
		//HTTP 요청의 본문과 요청 조건사항이 담긴 header를 한꺼번에 전달
		
		ResponseEntity<Map>response = restTemplate.exchange(url,HttpMethod.POST,entity,Map.class);
		// url : 요청을 보낼 toss 서버
		// HttpMethod.POST : 어떤 CRUD 작업을 수행할것인지 POST : 삽입
		// entity : 우리의 코드. 코드 작성 목적이 담긴 내용물, 제목, 요청 조건 사항이 담긴 내용
		// Map.class : 응답 받을 데이터 타입 지정. (key-value)
		
		// toss 서버로 보낸 요청에 대한 응답의 결과가 담겨있는 내용을 전달.
		return new ResponseEntity<>(response.getBody(),response.getStatusCode());
	}
}
