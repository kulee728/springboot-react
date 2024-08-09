package com.kh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.dto.UserProfile;
import com.kh.mapper.ProfileMapper;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	ProfileMapper mapper;
	
	@Value("${file.upload-dir}")
	private String profileDir;
	
	
	@Override
	public List<UserProfile> getProfiles() {
		return mapper.getProfiles();
	}

	@Override
	public void insertProfile(UserProfile userProfile) {
		mapper.insertProfile(userProfile);
	}

	@Override
	public void uploadProfile(MultipartFile[] files, String username) {
		File uploadDirFile = new File(profileDir);
		if(!uploadDirFile.exists()) {
			if(!uploadDirFile.mkdirs()) {
				throw new RuntimeException("폴더 생성 실패");
			}
		}
		List<String> fileNames = null;
		fileNames = List.of(files).stream().map(file->{
			String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
			File df = new File(profileDir+File.separator+fileName);
			try {
				file.transferTo(df);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return fileName;
		}).collect(Collectors.toList());
		
		UserProfile up = new UserProfile();
		up.setUsername(username);
		up.setProfileImageUrl(String.join(",",fileNames));
		mapper.insertProfile(up);
	}

}
