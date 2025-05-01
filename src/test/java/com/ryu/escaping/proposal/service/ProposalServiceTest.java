package com.ryu.escaping.proposal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@SpringBootTest
class ProposalServiceTest {

	@Autowired
	private ProposalService proposalService;
	
	@Test
	@Transactional
	public void testProposal() {
		// 준비 
		int id = 2;
		String state = "거절";
		
		// 진행
		boolean success = proposalService.updateProposal(id, state);
		
		// 결과 
		assertEquals(success, true);
	}
	
	
	
}
