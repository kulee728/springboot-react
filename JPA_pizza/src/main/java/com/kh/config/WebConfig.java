package com.kh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //front 포트 api url 접근 모두 허용
		.allowedOrigins("http://localhost:3001")
		.allowedMethods("GET","POST","PUT","DELETE","OPTIONS") //허용 HTTP method
		.allowCredentials(true); //쿠키나 세션과 같은 자격 허용 "*" 사용 가능
		
	}
}
