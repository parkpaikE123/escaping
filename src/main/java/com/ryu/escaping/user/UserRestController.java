package com.ryu.escaping.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.user.domain.User;
import com.ryu.escaping.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 API
	@PostMapping("/join")
	public Map<String, String> addUser(
									@RequestParam String loginId
									,@RequestParam String password
									,@RequestParam String userName
									,@RequestParam String name
									,@RequestParam int age
									,@RequestParam String phoneNumber) {
		Map<String, String>resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, userName, name, age, phoneNumber)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	// 중복 체크 API
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {
			
		Map<String, Boolean> resultMap = new HashMap<>();
			
		if(userService.isDuplicateId(loginId)) {
			// 중복
			resultMap.put("isDuplicate", true);
		} else {
			// 중복 안됨
			resultMap.put("isDuplicate", false);
		}
			
		return resultMap;
	}
	
	// 로그인 API
	@PostMapping("/login")
	public Map<String, String> login(@RequestParam String loginId
									,@RequestParam String password
									,HttpSession session) {
		
		Map<String, String>resultMap = new HashMap<>();
		
		User user = userService.userLogin(loginId, password);
		
		if(user != null) {
			// 세션에 필요한 사용자 정보 저장
			session.setAttribute("name", user.getName());
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
	// 카카오 로그인 API
	
	
}
