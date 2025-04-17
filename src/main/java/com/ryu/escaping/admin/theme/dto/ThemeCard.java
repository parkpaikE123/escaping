package com.ryu.escaping.admin.theme.dto;

import lombok.Builder;
import lombok.Getter;

//DTO
//테마 하나를 표현하는 카드 화면을 구성하기 위해 필요한 값들을 관리하기 위한 클래스 

@Builder
@Getter
public class ThemeCard {

	private int branchId;
	private String imagePath;
	private String branchName;
	private String location;
	
}
