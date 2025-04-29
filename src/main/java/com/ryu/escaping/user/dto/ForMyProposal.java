package com.ryu.escaping.user.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForMyProposal {

	private String imagePath;
	private String themeName;
	private int recruitCount;
	private String state;
	private LocalDateTime createdAt;
	
}
