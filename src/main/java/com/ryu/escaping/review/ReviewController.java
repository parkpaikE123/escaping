package com.ryu.escaping.review;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;
import com.ryu.escaping.review.domain.Review;
import com.ryu.escaping.review.sevice.ReviewService;

@Controller
@RequestMapping("/review")
public class ReviewController {

	private final ThemeService themeService;
	private final ReviewService reviewService;
	public ReviewController(ThemeService themeService,ReviewService reviewService) {
		this.themeService = themeService;
		this.reviewService = reviewService;
	}
	
	@GetMapping("/main-view")
	public String reviewMain(@RequestParam int themeId
							,Model model) {
		Theme theme = themeService.getThemeById(themeId);
		List<Review>reviewList = reviewService.getReviewList(themeId);
		model.addAttribute("theme",theme);
		model.addAttribute("reviewList",reviewList);
		return "/review/main";
	}
	
	@GetMapping("/create-view")
	public String createReview(@RequestParam int themeId
							,Model model) {
		Theme theme = themeService.getThemeById(themeId);
		
		model.addAttribute("theme", theme);
		return "/review/create";
	}
	
}
