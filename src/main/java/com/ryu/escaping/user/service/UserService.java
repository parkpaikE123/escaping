package com.ryu.escaping.user.service;

import org.springframework.stereotype.Service;

import com.ryu.escaping.common.MD5HashingEncoder;
import com.ryu.escaping.user.domain.User;
import com.ryu.escaping.user.repository.UserRepository;

import jakarta.persistence.PersistenceException;

@Service
public class UserService {

	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// 로그인 서비스
	public User userLogin(String loginId, String password) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);

		return userRepository.findByLoginIdAndPassword(loginId, encryptPassword);
	}
	
	// 아이디 중복체크 서비스
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.countByLoginId(loginId);
		
		if(count == 0) {
			// 중복 안됨
			return false;
		} else {
			// 중복됨
			return true;
		}
		
	}
	
	// 회원가입 서비스
	public boolean addUser(String loginId
							, String password
							, String userName
							, String name
							, int age
							, String phoneNumber) {
		
		String encryptPassword = MD5HashingEncoder.encode(password);
		
		User user = User.builder()
				.loginId(loginId)
				.password(encryptPassword)
				.userName(userName)
				.name(name)
				.age(age)
				.phoneNumber(phoneNumber)
				.build();
		
		try {
			userRepository.save(user);
		} catch (PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
}
