package com.ryu.escaping.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ForReceiveProposalDetail {

	private String imagePath;
	private String themeName;
	private String proposalUserName;
	private String contents;
	
}
