package com.ryu.escaping.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryu.escaping.user.domain.User;

import jakarta.transaction.Transactional;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService userService;

	// 회원가입, 로그인
	@Transactional
	@Test
	public void joinLoginTest() {
		// 직접 부여한 정보로 회원가입을 진행하고,
		// 회원가입된 id,password로 로그인 확인 
		
		// BDD (behavior Driven Development) 행위 주도 개발
		// given - 준비
		String loginId = "asdf";
		String password = "asdf";
		String userName = "asdf";
		String name = "name";
		int age = 12;
		String phoneNumber = "010-4562-7852";
		
		// when - 실행
		boolean success = userService.addUser(loginId, password, userName, name, age, phoneNumber);
		User user = userService.userLogin(loginId, password);
		
		// then - 검증
		// success 가 true
		// user null이 아니다
		// 테스트 통과 확인 메서드
		assertEquals(success, true);
		assertNotNull(user);
		
	}

}
