package com.kh.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper postMapper;
	
	@Value("${file.upload-dir}")
	private String uploadDir; // "C:/Users/kulee/Pictures/saveImage"
	
	@Override
	public List<Post> findAll() {
		return postMapper.findAll();
	}

	@Override
	public void insertPost(Post post) {
		postMapper.insertPost(post);
	}

	@Override
	public void uploadImages(MultipartFile[] files, String title, String content) {
		//1. 바탕화면에 폴더가 ImagesSave 디렉토리 존재 확인 > 생성
		File uploadDirFile = new File(uploadDir);
		if(!uploadDirFile.exists()) {
			System.out.println("상위 폴더 생성됨");
			if(!uploadDirFile.mkdirs()) {
				throw new RuntimeException("폴더 생성 실패");
			}
		}
		List <String> fileNames=null;
		//files = Multipart 배열
		//모든 파일은 java 입장에서 stream 이다.ㅓ
		//List.of(files) => 파일 배열을 List 로 변환
		//.stream > 리스트, 배열의 데이터를 개별 원소로 처리하는 기능
		//.collect(Collectors.toList()) => stream으로 가져온 이미지 데이터를 리스트로 정렬
		// map -> 이미지를 stream 으로 가져오는 것
		// stream 으로 가져온 이미지를 collecto로 리스트를 모은후, 다시 한번 리스트로 목록 변환
		fileNames = List.of(files)
				.stream()
				.map(file->{
					String fileName1 = UUID.randomUUID().toString();
					//생성된 UUID
					String fileName2 = UUID.randomUUID().toString()+file.getOriginalFilename();
					//UUID + 파일명
					String fileName3 = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
					//언더바 구분(UUID_파일명)
					log.info("fileName1 :",fileName1);
					log.info("fileName2 :",fileName2);
					log.info("fileName3 :",fileName3);
					File DestinationFile = new File(uploadDir+File.separator+fileName3);
					try {
						file.transferTo(DestinationFile);
					} catch (IOException e) {
						throw new RuntimeException("파일 업로드 실패",e);
					}
					return fileName3; 
				})
				.collect(Collectors.toList());
		
		Post post = new Post();
		post.setTitle(title);
		post.setContent(content);
		post.setImageUrl(String.join(",", fileNames)); //파일 배열을 , 로 구분한 문자열로 저장
		insertPost(post);
		title.equals(fileNames);
	}
	//ㄷ데이터베이스에 이미지 이름, 이미지 경로 넣어주기
	@Override
	public void updatePost(Post post) {
		postMapper.updatePost(post);
		//이미지 저장된 곳에서 기존 이미지를 삭제해야함.
		
	}
}
