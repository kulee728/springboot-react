package com.kh.service;

import java.util.List;

import com.kh.dto.User;

public interface UserService {
	List<User> findAllUser();
	void insertUser(User user);
	void deleteUser(int id);
	void updateUser(User user);
}
