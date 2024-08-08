package com.kh.service;

import java.util.List;

import com.kh.dto.NaverUser;

public interface NaverUserService {
	void insertNaverUser(NaverUser user);
	List<NaverUser> findAllNaverUser();
}
