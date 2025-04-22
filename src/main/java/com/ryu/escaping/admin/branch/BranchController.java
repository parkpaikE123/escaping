package com.ryu.escaping.admin.branch;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.service.BranchService;

@Controller
@RequestMapping("/branch")
public class BranchController {
	
	private final BranchService branchService;
	public BranchController(BranchService branchService) {
		this.branchService = branchService;
	}
	
//	@GetMapping("/search-view")
//	public String searchBranch(@RequestParam String keyword
//							,Model model) {
//		
//		List<Branch> branchList = branchService.getListBySearch(keyword);
//		
//		model.addAttribute("branchList", branchList);
////		
//		return "/branch/search";
//	}
	
}
