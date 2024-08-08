package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.NaverUser;
import com.kh.mapper.NaverUserMapper;

@Service
public class NaverUserServiceImpl implements NaverUserService {

	@Autowired
	NaverUserMapper naverUserMapper;
	
	@Override
	public void insertNaverUser(NaverUser user) {
		naverUserMapper.insertNaverUser(user);
	}

	@Override
	public List<NaverUser> findAllNaverUser() {
		return naverUserMapper.findAllNaverUser();
	}

	
}
