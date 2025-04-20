package com.ryu.escaping.admin;

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
		List<Branch> branchList = branchService.getBranchList();
		model.addAttribute("branchList", branchList);
		return "admin/main";
	}
	
	// 지점 추가
	@GetMapping("/branch/create-view")
	public String addBranch() {
		return "admin/branch/create";
	}
	
	// 테마 관리
	@GetMapping("/theme/list-view")
	public String adminTheme(Model model
							,@RequestParam int branchId) {
		List<Theme> themeList = themeService.getTheme(branchId);
		model.addAttribute("themeList", themeList);
		model.addAttribute("branchId", branchId);
		return "admin/theme/list";
	}
	
	// 테마 추가
	@GetMapping("/theme/create-view")
	public String createTheme(@RequestParam int branchId
							, Model model) {
		Branch branch = branchService.getBranchById(branchId);
		model.addAttribute("branch", branch);
		return "admin/theme/create";
	}
	
	// 예약 관리
	@GetMapping("/reservation-view")
	public String adminReservation() {
		return "admin/reservation/list";
	}
	
}
