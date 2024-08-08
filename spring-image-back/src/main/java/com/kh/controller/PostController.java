package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.service.PostService;

@RestController
public class PostController {
	@Autowired 
	PostService postService;
	
	@PostMapping("/gallery/upload")
	public ResponseEntity<String> uploadImages(@RequestParam("files") MultipartFile[] files, 
			@RequestParam("title") String title, 
			@RequestParam("content") String content)
	{
		postService.uploadImages(files, title, content);
		return ResponseEntity.ok("이미지 DB업로드 성공");
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAll(){ // 이중 꺽쇠 > 객체 구분자
		
		List<Post> posts = postService.findAll();
		
		return ResponseEntity.ok(posts);
	}
	
}
