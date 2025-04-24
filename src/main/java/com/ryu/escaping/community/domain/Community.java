package com.ryu.escaping.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table(name="`community`")
@Builder
@Getter
@Entity
public class Community {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="themeId")
	private int themeId;
	
	private String contents;
	
	@Column(name="recruitCount")
	private int recruitCount;
	
	private String state;
	
}
