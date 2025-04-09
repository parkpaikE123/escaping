package com.ryu.escaping.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.service.BranchService;
import com.ryu.escaping.admin.theme.service.ThemeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private BranchService branchService;
	private ThemeService themeService;
	public AdminController(BranchService branchService, ThemeService themeService) {
		this.branchService = branchService;
		this.themeService = themeService;
	}
	
	// 관리자 메인
	@GetMapping("/main-view")
	public String manager(Model model) {
		List<Branch> branchList = new ArrayList<>();
		
		branchList = branchService.getBranch();
		
//		int themeCount = themeService.countByTheme("");
		
		model.addAttribute("branch", branchList);
		
//		model.addAttribute("themeCount",themeCount);
		
		return "admin/main";
	}
	// 지점 추가
	@GetMapping("/branch/create-view")
	public String addBranch() {
		return "admin/branch/create";
	}
	
	// 테마 관리
	@GetMapping("/theme/list-view")
	public String adminTheme() {
		return "admin/theme/list";
	}
	
	// 테마 추가
	@GetMapping("/theme/create-view")
	public String createTheme() {
		return "admin/theme/create";
	}
	
	// 예약 관리
	@GetMapping("/reservation-view")
	public String adminReservation() {
		return "admin/reservation/list";
	}
	
}
