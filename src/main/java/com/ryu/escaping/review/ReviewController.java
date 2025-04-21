package com.ryu.escaping.review;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	private final ThemeService themeService;
	public ReviewController(ThemeService themeService) {
		this.themeService = themeService;
	}
	
	@GetMapping("/create-view")
	public String createReview(@RequestParam int id
							,Model model) {
		Theme theme = themeService.getThemeById(id);
		
		model.addAttribute("theme", theme);
		return "/review/create";
	}
	
}
