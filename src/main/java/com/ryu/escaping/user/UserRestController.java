package com.ryu.escaping.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.user.service.UserService;

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
	
	// 로그인 API
	
	// 카카오 로그인 API
	
	
}
