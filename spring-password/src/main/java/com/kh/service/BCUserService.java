package com.kh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kh.dto.BCUser;
import com.kh.repository.BCUserRepository;

@Service
public class BCUserService {

	@Autowired
	private BCUserRepository bcUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void saveUser(BCUser bcUser) {
		bcUser.setPassword(passwordEncoder.encode(bcUser.getPassword()));
		bcUserRepository.save(bcUser);
	}
	
}
