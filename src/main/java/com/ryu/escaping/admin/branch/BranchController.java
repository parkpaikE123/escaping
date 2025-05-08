package com.ryu.escaping.admin.branch;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.service.BranchService;
import com.ryu.escaping.admin.theme.domain.Theme;
import com.ryu.escaping.admin.theme.service.ThemeService;

@Controller
@RequestMapping("/branch")
public class BranchController {
	
	private final BranchService branchService;
	private final ThemeService themeService;
	public BranchController(BranchService branchService, ThemeService themeService) {
		this.branchService = branchService;
		this.themeService = themeService;
	}
	
	@GetMapping("/search-view")
	public String searchBranch(@RequestParam String locationKey
							,Model model) {
		
		List<Branch> branchList = branchService.getListBySearch(locationKey);
		model.addAttribute("location",locationKey);
		model.addAttribute("branchList", branchList);		
		return "/branch/search";
	}
	
	@GetMapping("/detail-view")
	public String detailBranch(@RequestParam int id
							,Model model) {
		Branch branch = branchService.getBranchById(id);
		List<Theme> themeList = themeService.getThemeByBranchId(id);
		model.addAttribute("branch",branch);
		model.addAttribute("themeList", themeList);
		return "/branch/detail";
	}
	
}
