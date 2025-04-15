package com.ryu.escaping.user;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

	// 회원가입 API
	@PostMapping("/join")
	public Map<String, String> addUser(
									@RequestParam String loginId
									,@RequestParam String password
									,@RequestParam String name
									,@RequestParam String email
									,@RequestParam int age
									,@RequestParam String phoneNumber) {
		
	}
	
	// 로그인 API
	
	// 카카오 로그인 API
	@GetMapping("/kakao")
	public Map<String, String> kakaoLogin() {
		
	}
	
}
