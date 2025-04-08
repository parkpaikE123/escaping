package com.ryu.escaping.admin.branch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryu.escaping.admin.branch.service.BranchService;

@RestController
@RequestMapping("/admin")
public class BranchRestController {

	private final BranchService branchService;
	
	public BranchRestController(BranchService branchService) {
		this.branchService = branchService;
	}
	
	// 지점 추가 API
	@PostMapping("/create-branch")
	public Map<String, String> addBranch(
					@RequestParam String branchName
					,@RequestParam MultipartFile imageFile
					,@RequestParam String location) {
		
		Map<String, String>resultMap = new HashMap<>();
		
		if(branchService.addBranch(branchName, imageFile, location)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
}
