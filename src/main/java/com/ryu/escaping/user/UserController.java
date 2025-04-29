package com.ryu.escaping.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.proposal.domain.Proposal;
import com.ryu.escaping.proposal.service.ProposalService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	private final ProposalService proposalService;
	
	public UserController(ProposalService proposalService) {
		this.proposalService = proposalService;
	}
	
	// 첫 화면
	@GetMapping("/first-view")
	public String welcome() {
		return"/user/first";
	}
	
	// openAPI 화면
	@GetMapping("/open-login-view")
	public String openApi() {
		return "/user/open-api";
	}
	
	// 로그인 화면
	@GetMapping("/login-view")
	public String login() {
		return "/user/login";
	}
	
	// 회원가입 화면
	@GetMapping("/join-view")
	public String join() {
		return "/user/join";
	}
	
	// 내가 작성한 제안서
	@GetMapping("/my-proposal-view")
	public String proposal(@RequestParam int userId
						, HttpSession session
						, Model model) {
		int userId1 = (Integer)session.getAttribute("userId");
		List<Proposal> proposalList = proposalService.getProposalList(userId1);
		model.addAttribute("proposalList",proposalList);
		return "/proposal/mine";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginId");
		
		return "redirect:/user/login-view";
	}
	
	
}
