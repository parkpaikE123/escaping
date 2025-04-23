package com.ryu.escaping.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.review.sevice.ReviewService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

	private final ReviewService reviewService;
	public ReviewRestController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createReview(@RequestParam int themeId
										,@RequestParam String userName
										,@RequestParam int point
										,@RequestParam String contents
										,@RequestParam String level
										,@RequestParam String success
										,HttpSession session) {
		Map<String,String> resultMap = new HashMap<>();
		int userId = (Integer)session.getAttribute("userId");
		if(reviewService.addReview(userId, themeId, userName, point, contents, level, success)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteReview(int id) {
		Map<String, String>resultMap = new HashMap<>();
		if(reviewService.deleteReivew(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
