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
	
}
