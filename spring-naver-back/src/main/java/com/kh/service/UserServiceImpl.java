package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.User;
import com.kh.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> findAllUser() {
		
		return userMapper.findAllUser();
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
		return;
	}

	@Override
	public void deleteUser(int id) {
		userMapper.deleteUser(id);
	}
	
	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
	
	
	
}
