package com.ryu.escaping.community;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommunityController {

	@GetMapping("/create-view")
	public String createCommunity(@RequestParam int themeId
								,Model model
								,HttpSession session) {
		String userName = (String) session.getAttribute("name");
		model.addAttribute("userName", userName);
		return "/community/main";
	}
	
}
