package com.ryu.escaping.proposal;

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
@RequestMapping("/proposal")
public class ProposalController {
	
	private final CommunityService communityService;
	private final ThemeService themeService;
	public ProposalController (CommunityService communityService, ThemeService themeService) {
		this.communityService = communityService;
		this.themeService = themeService;
	}

	@GetMapping("/create-view")
	public String createProposal(@RequestParam int communityId
								,Model model) {
		Community community = communityService.getCommunityEntity(communityId);
		int themeKey = community.getThemeId();
		Theme theme = themeService.getThemeById(themeKey);
		model.addAttribute("theme", theme);
		model.addAttribute("community", community);
		return "/proposal/create";
	}
	
}
