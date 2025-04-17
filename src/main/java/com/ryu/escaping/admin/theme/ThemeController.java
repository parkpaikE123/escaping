package com.ryu.escaping.admin.theme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/theme")
public class ThemeController {

	@GetMapping("/main-view")
	public String mainTheme(@RequestParam String loginId
							,Model model
							,HttpSession session) {
		
		String userId = "" + session.getAttribute(loginId);
		
		return "/theme/main";
	}
	
	@GetMapping("/search-view")
	public String searchTheme(@RequestParam String keyword) {
		return "/theme/search";
	}
	
}
