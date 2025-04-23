package com.ryu.escaping.admin.dto.branch;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CardView {

	private int branchId;
	private String name;
	private String location;
	private String branchPath;
	private int themeCount;
	
}
