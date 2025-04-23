package com.ryu.escaping.admin.branch.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BranchForAdmin {

	private int branchId;
	private String name;
	private String location;
	private String branchPath;
	private int themeCount;
	
}
