package com.ryu.escaping.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.admin.branch.domain.Branch;
import com.ryu.escaping.admin.branch.service.BranchService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final BranchService branchService;
	public AdminController(BranchService branchService) {
		this.branchService = branchService;
	}
	
	// 관리자 메인
	@GetMapping("/main-view")
	public String manager(Model model
						,@RequestParam int branchId) {
		
		Branch branch = branchService.setBranch(branchId);
		
		model.addAttribute(branch);
		
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
