package com.ryu.escaping.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	// 첫 화면
	@GetMapping("/first-view")
	public String welcome() {
		return"/user/first";
		
	}
	
	// openAPI 화면
	@GetMapping("/open-login-view")
	public String openApi() {
		return "/user/open-api";
	}
	
	// 로그인 화면
	@GetMapping("/login-view")
	public String login() {
		return "/user/login";
	}
	
	// 회원가입 화면
	@GetMapping("/join-view")
	public String join() {
		return "/user/join";
	}
	
	
	
}
