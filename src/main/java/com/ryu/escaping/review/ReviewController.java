package com.ryu.escaping.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

	@GetMapping("/create-view")
	public String createReview() {
		return "/review/create";
	}
	
}
