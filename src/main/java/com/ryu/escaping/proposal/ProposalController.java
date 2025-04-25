package com.ryu.escaping.proposal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proposal")
public class ProposalController {

	@GetMapping("/create-view")
	public String createProposal() {
		return "/proposal/create";
	}
	
}
