package com.ryu.escaping.admin.branch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.admin.branch.service.BranchService;

@RestController
@RequestMapping("/branch")
public class BranchRestController {

	public final BranchService branchService;
	
	public BranchRestController(BranchService branchService) {
		this.branchService = branchService;
	}
	
	// 지점 추가 API
	public Map<String, String> addBranch(@RequestParam String BranchName
					,@RequestParam MultipartFile branchImage
					,@RequestParam String location) {
		
	}
	
}
