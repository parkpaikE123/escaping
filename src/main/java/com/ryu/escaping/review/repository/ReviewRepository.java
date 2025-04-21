package com.ryu.escaping.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	
	
}
