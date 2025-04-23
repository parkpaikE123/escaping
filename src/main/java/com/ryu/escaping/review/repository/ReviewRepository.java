package com.ryu.escaping.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByThemeId(int themeId);
	
}
