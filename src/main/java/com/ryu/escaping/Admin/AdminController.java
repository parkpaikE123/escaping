package com.ryu.escaping.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theme/admin")
public class AdminController {

	// 테마 관리
	@GetMapping("/theme/list-view")
	public String manager() {
		return "admin/main";
	}
	
	// 테마 추가
	@GetMapping("/theme/create-view")
	public String createTheme() {
		return "admin/create";
	}
	
}
