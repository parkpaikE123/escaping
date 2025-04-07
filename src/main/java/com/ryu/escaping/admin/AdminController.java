package com.ryu.escaping.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	// 관리자 메인
	@GetMapping("/theme/main-view")
	public String manager() {
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
