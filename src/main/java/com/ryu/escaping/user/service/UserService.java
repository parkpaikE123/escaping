package com.ryu.escaping.user.service;

import org.springframework.stereotype.Service;

import com.ryu.escaping.common.MD5HashingEncoder;
import com.ryu.escaping.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(String loginId
							, String password
							, String name
							, String eamail
							, int age
							, String phoneNumber) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
	}
	
}
