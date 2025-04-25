package com.ryu.escaping.community;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;
import com.ryu.escaping.community.domain.Community;
import com.ryu.escaping.community.sevice.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {

	private final ThemeService themeService;
	private final CommunityService communityService;
	public CommunityController(ThemeService themeService, CommunityService communityService) {
		this.themeService = themeService;
		this.communityService = communityService;
	}
	
	@GetMapping("/main-view")
	public String mainCommunity(@RequestParam int themeId
								,Model model) {
		Theme theme = themeService.getThemeById(themeId);
		List<Community> communityList = communityService.getCommunity(themeId);
		model.addAttribute("communityList", communityList);
		model.addAttribute("theme", theme);
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
