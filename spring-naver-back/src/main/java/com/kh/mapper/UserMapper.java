package com.kh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.dto.User;

@Mapper
public interface UserMapper {
	List<User> findAllUser();
	void insertUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
}
