package com.kh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * BCrypt >
 * Blowfish 암호화 알고리즘 
 */

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll() // 모든 HTTP 요청 허용, 비밀번호 필요 없음
            )
            .csrf(csrf -> csrf.disable()); //3000포트 등 외부 접근 보호 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //비밀번호 관련 객체 설정
}

