package com.ryu.escaping.admin.theme;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.service.BranchService;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

@Controller
@RequestMapping("/theme")
public class ThemeController {
	
	private final ThemeService themeService;
	private final BranchService branchService;
	public ThemeController(ThemeService themeService, BranchService branchService) {
		this.themeService = themeService;
		this.branchService = branchService;
	}

	@GetMapping("/main-view")
	public String mainTheme(Model model) {
		List<Theme>themeList = themeService.getThemeList();
		model.addAttribute("themeList", themeList);
		
		return "/theme/main";
	}
	
	@GetMapping("/search-view")
	public String searchTheme(@RequestParam String genre
							,Model model) {
		
		List<Theme> themeList =themeService.getThemeListBySearch(genre);
		model.addAttribute("themeList",themeList);
		return "/theme/search";
	}
	
	@GetMapping("/detail-view")
	public String detailTheme(@RequestParam int id
							,Model model) {
		Theme theme = themeService.getThemeById(id);
		model.addAttribute("theme", theme);
		return "/theme/detail";
	}
	
}
