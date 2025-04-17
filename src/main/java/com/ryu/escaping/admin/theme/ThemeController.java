package com.ryu.escaping.admin.theme;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/theme")
public class ThemeController {

	private final ThemeService themeService;
	public ThemeController(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	@GetMapping("/main-view")
	public String mainTheme(Model model
			, HttpSession session) {
		String loginId =  "" +session.getAttribute("loginId");
		String userName = "" + session.getAttribute("userName");
		List<Theme>themeList = themeService.getThemeList();
		
		model.addAttribute("themeList", themeList);
		model.addAttribute("loginId", loginId);
		model.addAttribute("name", userName);
		
		return "/theme/main";
	}
	
	@GetMapping("/search-view")
	public String searchTheme() {
		return "/theme/search";
	}
	
}
