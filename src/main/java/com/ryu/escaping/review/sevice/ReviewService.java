package com.ryu.escaping.review.sevice;

import org.springframework.stereotype.Service;

import com.ryu.escaping.review.domain.Review;
import com.ryu.escaping.review.repository.ReviewRepository;

import jakarta.persistence.PersistenceException;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	public ReviewService (ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	// 리뷰 작성
	public boolean addReview(int themeId
			,String reviewUserName
			,int point
			,String contents
			,String level
			,String success) {
		Review review = Review.builder()
						.themeId(themeId)
						.reviewUserName(reviewUserName)
						.point(point)
						.contents(contents)
						.level(level)
						.success(success)
						.build();
		try{
		reviewRepository.save(review);
		} catch (PersistenceException e) {
			return false;
		}
		return true;
	}
	
}
