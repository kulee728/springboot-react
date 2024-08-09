package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;
import com.kh.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService service;
	
	
	@GetMapping("/watching")
	public ResponseEntity<List<UserProfile>> getProfile(){
		return ResponseEntity.ok(service.getProfiles());
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> insertProfile(@RequestParam("files") MultipartFile[] files,
			@RequestParam("username") String username
			) {
		
		service.uploadProfile(files,username);
		return ResponseEntity.ok("이미지 업로드 성공");
	}
	
}
