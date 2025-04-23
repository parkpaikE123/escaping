package com.ryu.escaping.review.sevice;

import java.util.List;
import java.util.Optional;

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
	
	// 리뷰 삭제
	public boolean deleteReivew(int id) {
		Optional<Review>optionalReview = reviewRepository.findById(id);
		if(optionalReview.isPresent()) {
			Review review = optionalReview.get();
			if(review.getId() != id) {
				return false;
			}
			try {
				reviewRepository.delete(review);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	// 리뷰 리스트 불러오기
	public List<Review> getReviewList(int themeId) {
		return reviewRepository.findByThemeId(themeId);
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
