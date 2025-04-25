package com.ryu.escaping.admin.theme;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.branch.service.BranchService;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;
import com.ryu.escaping.community.domain.Community;
import com.ryu.escaping.community.sevice.CommunityService;
import com.ryu.escaping.review.domain.Review;
import com.ryu.escaping.review.sevice.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/theme")
public class ThemeController {
	
	private final ThemeService themeService;
	private final BranchService branchService;
	private final ReviewService reviewService;
	private final CommunityService communityService;
	public ThemeController(ThemeService themeService, BranchService branchService,ReviewService reviewService
						,CommunityService communityService) {
		this.themeService = themeService;
		this.branchService = branchService;
		this.reviewService = reviewService;
		this.communityService = communityService;
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
							,HttpSession session
							,Model model) {
		
		List<Review>reviewList = reviewService.getReviewListTop5(id);
		Theme theme = themeService.getThemeById(id);
		List<Community>communityList = communityService.getThreeCommunity(id);
		model.addAttribute("communityList",communityList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("theme", theme);
		return "/theme/detail";
	}
	
}
