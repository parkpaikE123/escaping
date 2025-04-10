package com.ryu.escaping.admin.theme.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CountThemeView {

	private int branchId;
	
	private int themeCount;
	private String imagePath;
	private String branchName;
	private String location;
	
}
