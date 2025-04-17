package com.ryu.escaping.admin.theme;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/theme")
public class ThemeController {

	@GetMapping("/main-view")
	public String mainTheme(Model model
			, HttpSession session) {
		String loginId =  "" +session.getAttribute("loginId");
		String userName = "" + session.getAttribute("userName");
		model.addAttribute("loginId", loginId);
		model.addAttribute("name", userName);
		
		return "/theme/main";
	}
	
	@GetMapping("/search-view")
	public String searchTheme() {
		return "/theme/search";
	}
	
}
