package com.ryu.escaping.community;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommunityController {

	private final ThemeService themeService;
	public CommunityController(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	@GetMapping("/main-view")
	public String mainCommunity(@RequestParam int themeId
								,Model model
								,HttpSession session) {
		return "/community/main";
	}
	
	@GetMapping("/create-view")
	public String createCommunity(@RequestParam int themeId
								,Model model) {
		Theme theme = themeService.getThemeById(themeId);
		model.addAttribute("theme", theme);
		return "/community/create";
	}
	
}
