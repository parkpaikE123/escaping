package com.ryu.escaping.review.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Entity
@Table(name="`review`")
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="themeId")
	private int themeId;
	
	@Column(name="reviewUserName")
	private String reviewUserName;
	
	private int point;
	private String contents;
	
	private String level;
	private String success;
}
