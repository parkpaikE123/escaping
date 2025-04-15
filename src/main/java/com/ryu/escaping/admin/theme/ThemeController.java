package com.ryu.escaping.admin.theme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theme")
public class ThemeController {

	@GetMapping("/main-view")
	public String mainTheme() {
		return "/theme/main";
	}
	
}
