package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.NaverUser;

@Mapper
public interface NaverUserMapper {
	void insertNaverUser(NaverUser user);
	List<NaverUser> findAllNaverUser();
}
