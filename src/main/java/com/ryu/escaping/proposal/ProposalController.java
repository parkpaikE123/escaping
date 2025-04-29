package com.ryu.escaping.proposal;

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
import com.ryu.escaping.user.dto.ForReceiveProposalDetail;
import com.ryu.escaping.user.service.UserService;

@Controller
@RequestMapping("/proposal")
public class ProposalController {
	
	private final CommunityService communityService;
	private final ThemeService themeService;
	private final UserService userService;
	public ProposalController (UserService userService, CommunityService communityService, ThemeService themeService) {
		this.communityService = communityService;
		this.themeService = themeService;
		this.userService = userService;
	}
	
	@GetMapping("/detail-view")
	public String proposalDetail(@RequestParam int communityId
								,Model model) {
		List<ForReceiveProposalDetail> proposalDetailList = userService.getProposalDetail(communityId);
		Community community = communityService.getCommunityEntity(communityId);
		Theme theme = themeService.getThemeById(community.getThemeId());
		model.addAttribute("theme",theme);
		model.addAttribute("proposalDetailList",proposalDetailList);
		return "/proposal/detail";
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
