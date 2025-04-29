package com.ryu.escaping.user.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForReceiveProposal {

	private int communityId;
	
	private String imagePath;
	private String themeName;
	private int recruitCount;
	private int sendCount;
	
	private String state;
	
	
	private String createdAt;
	
}
