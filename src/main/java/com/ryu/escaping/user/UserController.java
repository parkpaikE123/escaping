package com.ryu.escaping.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ryu.escaping.user.dto.ForMyProposal;
import com.ryu.escaping.user.dto.ForReceiveProposal;
import com.ryu.escaping.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
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
	public String proposal(HttpSession session
						, Model model) {
		int userId1 = (Integer)session.getAttribute("userId");
		List<ForMyProposal> proposalList = userService.getMyProposal(userId1);
		model.addAttribute("proposalList",proposalList);
		return "/proposal/mine";
	}
	
	// 내가 받은 제안서 보기 화면
	@GetMapping("/my-community/proposal-view")
	public String reciveProposal(HttpSession session
								, Model model) {	
		int userSessionId = (Integer)session.getAttribute("userId");
		List<ForReceiveProposal> receiveProposalList = userService.getReceiveProposal(userSessionId);
		model.addAttribute("receiveProposalList", receiveProposalList);
		
		return "/community/receive";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("loginId");
		
		return "redirect:/user/login-view";
	}
	
	
}
