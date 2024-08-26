package com.kh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 	외부 도메인에서 요청을 주고 받을 수 있도록 허용하는 것
 	설정을 통해 특정 도메인에서 오는 요청을 허용할 수 있고
 	허용할 httpmethod (GET POST PUT DELETE) 지정할 수 있다.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //모든 하위 주소값 허용
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS") //해당 주소에서 허용하는 HTTP 메서드
		.allowedHeaders("*");
		//.allowCredentials(true); //쿠키나 세션과 같은 자격을 허용
	}
	
}
