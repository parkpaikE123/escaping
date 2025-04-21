package com.ryu.escaping.admin.theme.domain;

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

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="`theme`")
@Entity
public class Theme {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="branchId")
	private int branchId;
	
	@Column(name="themeStory")
	private String themeStory;
	
	@Column(name="themeName")
	private String themeName;
		
	@Column(name="imagePath")
	private String imagePath;
	
	private String genre;
	
	@Column(name="branchName")
	private String branchName;
	
	private int price;
	
	@Column(name="runningTime")
	private int runningTime;
	
}
