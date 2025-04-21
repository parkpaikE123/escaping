package com.ryu.escaping.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.review.sevice.ReviewService;

@RestController
public class ReviewRestController {

	private final ReviewService reviewService;
	public ReviewRestController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	public Map<String, String> createReview(@RequestParam int themeId
										,@RequestParam String userName
										,@RequestParam int point
										,@RequestParam String contents
										,@RequestParam String level
										,@RequestParam String success) {
		Map<String,String> resultMap = new HashMap<>();
		if(reviewService.addReview(themeId, userName, point, contents, level, success)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
