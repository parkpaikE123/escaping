package com.ryu.escaping.proposal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.escaping.proposal.service.ProposalService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/propoasl")
public class ProposalRestController {

	private final ProposalService proposalService;
	public ProposalRestController(ProposalService proposalService) {
		this.proposalService = proposalService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createProposal(@RequestParam int communityId
											,@RequestParam String contents
											,@RequestParam String state
											,HttpSession session) {
		Map<String, String> resultMap = new HashMap<>();
		int userId = (Integer)session.getAttribute("userId");
		if(proposalService.addProposal(userId, communityId, contents, state)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
	}
	
}
