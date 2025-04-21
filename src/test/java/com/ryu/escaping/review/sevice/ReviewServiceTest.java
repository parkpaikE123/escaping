package com.ryu.escaping.review.sevice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class ReviewServiceTest {

	@Autowired
	private ReviewService reviewService;
	
	// 리뷰 생성
	@Transactional
	@Test
	public void createReview() {
		int themeId = 1;
		String reviewUserName = "asdf";
		int point = 4;
		String contents = "asdf";
		String level ="asdf";
		String success = "asdf";
		
		boolean success1 = reviewService.addReview(themeId, reviewUserName, point, contents, level, success);
		
		assertEquals(success1,true);
	}

}
